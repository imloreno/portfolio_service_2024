package com.projects.portfolio.portfolio.constants;

public final class ProjectConst {
   public enum Skills {
      JAVA,
      SPRING,
      SPRING_BOOT,
      API_REST,
      POSTGRESQL,
      MYSQL,
      MONGODB,
      MICROSERVICES,
      DOCKER,
      KUBERNETES,
      JENKINS,
      DJANGO,
      PYTHON,
      HTML5,
      CSS3,
      JAVASCRIPT,
      TYPESCRIPT,
      REACT,
      REDUX_ZUSTAND,
      RESPONSIVE_DESIGN,
      BOOTSTRAP,
      MATERIAL_UI,
      TAILWIND_CSS,
      TESTING,
      JUNIT,
      MOCKITO,
      PHOTOSHOP,
      ILLUSTRATOR,
      FIGMA,
      JIRA,
      AGILE,
      SCRUM,
      VS_CODE,
      GIT,
      GITHUB,
      BITBUCKET,
      MVC,
      BEM,
      REDUX,
      PHP
   }

   public enum SkillCategory {
      BACKEND,
      FRONTEND,
      DEVOPS,
      TOOLS,
      REPOSITORY,
      METHODOLOGIES
   }

   public static boolean containsSkill(String skill) {
      for (Skills s : Skills.values()) {
         if (s.toString().equals(skill)) {
            return true;
         }
      }
      return false;
   }

   public static boolean containsSkillCategory(String category) {
      for (SkillCategory s : SkillCategory.values()) {
         if (s.toString().equals(category)) {
            return true;
         }
      }
      return false;
   }
}

