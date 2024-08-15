package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "projects")
public class Project implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private UUID id;
   @Column(unique = true, length = 100, nullable = false)
   private String name;
   @Column(length = 100, nullable = false)
   private String position;
   @Column(length = 20)
   private String type;
   @Column(name = "started_from", columnDefinition = "DATE", nullable = false)
   private Date from;
   @Column(name = "terminated_at", columnDefinition = "DATE")
   private Date to;
   @Column(length = 250)
   private String picture;

   @OneToOne(mappedBy = "projects", cascade = CascadeType.ALL)
   @PrimaryKeyJoinColumn
   private ProjectDetails projectDetails;

   @ManyToMany
   @JoinTable(
      name = "project_skills",
      joinColumns = @JoinColumn(name = "projects_id"),
      inverseJoinColumns = @JoinColumn(name = "skills_id"))
   Set<Skills> skills;
}
