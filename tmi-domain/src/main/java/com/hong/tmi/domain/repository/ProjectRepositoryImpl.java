package com.hong.tmi.domain.repository;


import com.hong.tmi.domain.Project;
import com.hong.tmi.domain.common.QuerydslSupport;
import com.querydsl.jpa.JPAExpressions;

import java.util.List;

import static com.hong.tmi.domain.QProTeam.proTeam;
import static com.hong.tmi.domain.QProject.project;

public class ProjectRepositoryImpl extends QuerydslSupport implements ProjectRepositoryQuery {

    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<Project> findBelongedProjects(Long memberId) {
        return getQueryFactory()
                .selectFrom(project)
                .where(project.id.in(
                        JPAExpressions.select(proTeam.project.id)
                                .from(proTeam)
                                .where(proTeam.member.id.eq(memberId))
                )).fetch();
    }
}
