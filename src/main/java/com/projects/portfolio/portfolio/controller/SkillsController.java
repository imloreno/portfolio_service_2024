package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.models.Skills;
import com.projects.portfolio.portfolio.services.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Profile("DEV")
@RestController
@RequestMapping("api/v1/skills")
public class SkillsController {

   @Autowired
   SkillsService skillsService;

   @PostMapping
   public ResponseEntity<Skills> addSkill(@Validated @RequestBody Skills skills) {
      return ResponseEntity.ok(skillsService.addSkills(skills));
   }

}
