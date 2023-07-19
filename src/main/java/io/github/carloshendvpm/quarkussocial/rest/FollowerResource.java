package io.github.carloshendvpm.quarkussocial.rest;

import io.github.carloshendvpm.quarkussocial.domain.model.Follower;
import io.github.carloshendvpm.quarkussocial.domain.repository.FollowerRepository;
import io.github.carloshendvpm.quarkussocial.domain.repository.UserRepository;
import io.github.carloshendvpm.quarkussocial.rest.dto.CreateFollowerRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {
  
  private FollowerRepository repository;
  private UserRepository userRepository;

  @Inject
  public FollowerResource(FollowerRepository repository, UserRepository userRepository){
    this.repository = repository;
    this.userRepository = userRepository;
  }

  @PUT
  @Transactional
  public Response followUser(@PathParam("userId") Long userId, CreateFollowerRequest request){

    if (userId.equals(request.getFollowerId())){
      return Response.status(Response.Status.CONFLICT).entity("You can't follow yourself").build();
    }

    var user = userRepository.findById(userId);
    if(user == null){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    var follower = userRepository.findById(request.getFollowerId());

    boolean follows = repository.follows(follower, user);

    if(!follows){
      var entity = new Follower();
      entity.setUser(user);
      entity.setFollower(follower);

      repository.persist(entity);
    }

    return Response.status(Response.Status.NO_CONTENT).build();

  }

}
