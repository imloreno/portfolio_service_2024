package com.projects.portfolio.portfolio.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityDTO<T> {
   private String message;
   private Integer status;
   private T data;
}
