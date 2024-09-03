package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project_gallery")
public class ProjectGallery implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public UUID id;

   @ManyToOne
   @JoinColumn(name="project_id", nullable=false)
   private Project project;
}
