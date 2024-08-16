package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = "projects")
@Table(name = "skills")
public class Skills implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   @Column(unique = true, length = 20, nullable = false)
   private String name;
   @Column(name = "years_of_experience", nullable = false, length = 2)
   private Integer yearsOfExperience;
   @Column(length = 20, nullable = false)
   private String category;

   @ManyToMany(mappedBy = "skills")
   Set<Project> projects = new HashSet<>();
}
