package com.projects.portfolio.portfolio.repository;

import com.projects.portfolio.portfolio.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, UUID>, JpaSpecificationExecutor<Skills> {

   Skills findByName(String name);

}
