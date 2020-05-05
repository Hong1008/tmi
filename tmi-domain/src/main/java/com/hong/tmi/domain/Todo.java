package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseEntity;
import com.hong.tmi.domain.embed.TaskManagement;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 할일 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="todo_name")),
            @AttributeOverride(name = "description", column = @Column(name="todo_dscr"))
    })
    @Embedded
    private TaskManagement taskManagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sch_team_id")
    private ScheduleTeam scheduleTeam;

    @Builder
    public Todo(TaskManagement taskManagement,
                Project project,
                Schedule schedule,
                ScheduleTeam scheduleTeam) {
        this.taskManagement = taskManagement;
        this.project = project;
        this.schedule = schedule;
        this.scheduleTeam = scheduleTeam;
    }
}
