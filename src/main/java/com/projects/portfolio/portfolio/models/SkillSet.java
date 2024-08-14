package com.projects.portfolio.portfolio.models;

import com.projects.portfolio.portfolio.constants.ProjectConst.SkillCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "skills")
public class SkillSet {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private UUID id;
   @Column(columnDefinition = "DATE", nullable = false)
   private Date from;
   @Column(columnDefinition = "DATE")
   private Date to;
   @Column(length = 15, nullable = false)
   private SkillCategory category;
}
