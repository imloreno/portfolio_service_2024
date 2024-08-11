package com.projects.portfolio.portfolio.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityDAO<T> {
   private String message;
   private Integer status;
   private T data;
}
