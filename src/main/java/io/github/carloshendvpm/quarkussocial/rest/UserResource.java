package io.github.carloshendvpm.quarkussocial.rest;

import java.util.Set;

import io.github.carloshendvpm.quarkussocial.domain.model.User;
import io.github.carloshendvpm.quarkussocial.domain.repository.UserRepository;
import io.github.carloshendvpm.quarkussocial.rest.dto.CreateUserRequest;
import io.github.carloshendvpm.quarkussocial.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  
  private UserRepository repository;
  private Validator validator;

  @Inject
  public UserResource(UserRepository repository, Validator validatior){
    this.repository = repository;
    this.validator = validatior;
  }
  
  @POST
  @Transactional
  public Response createUser( CreateUserRequest userRequest ){

    Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);

    if(!violations.isEmpty()){ // se o set de violações não estiver vazio
      return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
    }
    User user = new User();
    user.setAge(userRequest.getAge());
    user.setName(userRequest.getName());

    repository.persist(user);

    return Response.status(Response.Status.CREATED.getStatusCode()).entity(user).build();
  }

  @GET
  public Response listAllUsers(){
    PanacheQuery<User> query = repository.findAll();
    return Response.ok(query.list()).build();
  }

  @DELETE
  @Path("{id}")
  @Transactional // quando é feita alguma alteração no banco o transactional tem que ser utilizado
  public Response deleteUser(@PathParam("id") Long id){
    User user = repository.findById(id);
    if(user != null ){
      repository.delete(user);
      return Response.noContent().build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @PUT
  @Path("{id}")
  @Transactional
  public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData ){
    User user = repository.findById(id);
    if( user != null){
      user.setName(userData.getName());
      user.setAge(userData.getAge());
      //repository.persist(user); isso não é necessário pois com o transactional qualquer alteração que fizer na entidade vai ser commitada quando fizer a transação  
      return Response.ok(user).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
