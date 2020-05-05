package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
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
public class ProTeam extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "pro_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private ProTeamLevel ptLevel;

    private String proTeamNickname;

    private double proTeamContribution;

    @Builder
    public ProTeam(Member member,
                   Project project,
                   ProTeamLevel proTeamLevel,
                   String proTeamNickname,
                   double proTeamContribution) {
        this.member = member;
        this.project = project;
        this.ptLevel = proTeamLevel;
        this.proTeamNickname = proTeamNickname;
        this.proTeamContribution = proTeamContribution;
    }

    public enum ProTeamLevel {
        LEADER, MEMBER
    }
}
