package com.hong.tmi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select distinct p from Project p left join fetch p.schedules where p.taskManagement.member.id = :memberId")
    List<Project> findByLeader(@Param("memberId")Long leaderId);
}
