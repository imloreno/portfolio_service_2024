package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "project_details")
public class ProjectDetails {

   @Id
   @Column(name = "project_id")
   private UUID id;
   @Column(columnDefinition = "TEXT")
   private String description;
   @Column(length = 50)
   private String github;
   @Column(length = 70)
   private String link;

   @OneToOne
   @MapsId
   @JoinColumn(name = "project_id")
   private Project projects;
}
