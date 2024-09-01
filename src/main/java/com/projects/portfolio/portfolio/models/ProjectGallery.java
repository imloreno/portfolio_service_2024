package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "project_gallery")
public class ProjectGallery implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public UUID id;

   @ManyToOne
   @JoinColumn(name="project_id", nullable=false)
   private Project project;
}
