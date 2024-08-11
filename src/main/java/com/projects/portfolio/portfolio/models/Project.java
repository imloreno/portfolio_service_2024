package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "projects")
public class Project implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   @Column(unique = true, length = 100)
   private String name;
   @Column(length = 100)
   private String position;
   @Column(length = 20)
   private String type;
   @Column(name = "started_from", columnDefinition = "DATE")
   private Date from;
   @Column(name = "terminated_at", nullable = true, columnDefinition = "DATE")
   private Date to;
   @Column(length = 250)
   private String picture;
}
