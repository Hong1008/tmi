package com.hong.tmi.domain.repository;

import com.hong.tmi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long>, ProjectRepositoryQuery {

    @Query("select p " +
            "from Project p " +
            "where p.taskManagement.member.id = :memberId")
    List<Project> findByLeader(@Param("memberId")Long leaderId);
}
