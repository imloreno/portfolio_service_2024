package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.constants.ProjectConst;
import com.projects.portfolio.portfolio.models.Skills;
import com.projects.portfolio.portfolio.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

   @Autowired
   SkillsRepository skillsRepository;

   Logger logger = LoggerFactory.getLogger(SkillsService.class);

   public List<Skills> getSkills() {
      return skillsRepository.findAll();
   }

   public Skills addSkills(Skills skills) {

      if (!ProjectConst.containsSkill(skills.getName())) {
         logger.error("Skill not found: ".concat(skills.getName()));
         return null;
      }

      if (!ProjectConst.containsSkillCategory(skills.getCategory())) {
         logger.error("Skill category not found: ".concat(skills.getCategory()));
         return null;
      }

      return skillsRepository.save(skills);
   }

}
