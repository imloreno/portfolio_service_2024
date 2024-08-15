package com.projects.portfolio.portfolio.models.dto;

import com.projects.portfolio.portfolio.models.Skills;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ProjectWithDetailsDTO {
   private UUID id;
   private String name;
   private String position;
   private String type;
   private Date from;
   private Date to;
   private String picture;

   private String description;
   private String github;
   private String link;

   private List<Skills> skills = new ArrayList<>();
}
