package com.projects.portfolio.portfolio.models.dto;

import com.projects.portfolio.portfolio.models.Project;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectWithDetailsDTO extends Project {
   private UUID id;
   private String name;
   private String position;
   private String type;
   private Date from;
   private Date to;
   private String picture;

   private UUID detailsId;
   private String description;
   private String github;
   private String link;
}
