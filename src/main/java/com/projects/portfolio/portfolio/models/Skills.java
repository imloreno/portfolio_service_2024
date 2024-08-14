package com.projects.portfolio.portfolio.models;

import com.projects.portfolio.portfolio.constants.ProjectConst;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "skills")
public class Skills {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private UUID id;

   @Column(unique = true, length = 10, nullable = false)
   private ProjectConst.Skills name;
}
