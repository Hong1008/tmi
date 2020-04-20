package com.hong.tmi.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 프로젝트 팀 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProTeam {
    @Id @GeneratedValue
    @Column(name = "pro_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private ProTeamLevel proTeamLevel;

    @Builder
    public ProTeam(Member member, Project project, ProTeamLevel proTeamLevel) {
        this.member = member;
        this.project = project;
        this.proTeamLevel = proTeamLevel;
    }

    public enum ProTeamLevel{
        LEADER,MEMBER
    }
}
