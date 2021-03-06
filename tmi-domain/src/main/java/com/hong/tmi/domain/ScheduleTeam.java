package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 스케줄 팀 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ScheduleTeam extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "sch_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_team_id")
    private ProTeam proTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private SchTeamLevel schTeamLevel;

    public enum SchTeamLevel{
        LEADER,MEMBER
    }
}
