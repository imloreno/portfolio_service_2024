package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "skills")
public class Skills implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private UUID id;

   @Column(unique = true, length = 20, nullable = false)
   private String skill;
}
