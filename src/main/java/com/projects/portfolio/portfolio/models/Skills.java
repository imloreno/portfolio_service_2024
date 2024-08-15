package com.projects.portfolio.portfolio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "skills")
public class Skills implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   @Column(unique = true, length = 20, nullable = false)
   private String name;
   @Column(name = "started_from", columnDefinition = "DATE", nullable = false)
   private Date from;
   @Column(name = "terminated_at", columnDefinition = "DATE")
   private Date to;
   @Column(length = 20, nullable = false)
   private String category;
}
