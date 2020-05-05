package com.hong.tmi.domain.repository;

import com.hong.tmi.domain.Project;

import java.util.List;

public interface ProjectRepositoryQuery {

    List<Project> findBelongedProjects(Long memberId);
}
