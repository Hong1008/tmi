package com.hong.tmi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ScheduleTeam {
    @Id @GeneratedValue
    @Column(name = "sch_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sch_id")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private SchTeamLevel schTeamLevel;

    public enum SchTeamLevel{
        LEADER,MEMBER
    }
}
