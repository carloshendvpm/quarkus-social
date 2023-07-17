package io.github.carloshendvpm.quarkussocial.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "users") //usar o table não é obrigatório
@Data
public class User {

  @Id //indica o campo do pk
  @GeneratedValue(strategy = GenerationType.IDENTITY) // delega que é um campo de autoincrimento
  private Long id;

  @Column(name= "age")
  private Integer age;

  @Column(name= "name") //usar o column não é obrigatório
  private String name;

}
