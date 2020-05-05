package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseEntity;
import com.hong.tmi.domain.embed.Schedules;
import com.hong.tmi.domain.embed.TaskManagement;
import com.hong.tmi.domain.embed.Todos;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 프로젝트 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="project_name")),
            @AttributeOverride(name = "description", column = @Column(name="project_dscr"))
    })
    @Embedded
    private TaskManagement taskManagement;

    private double projectRate;

    @Embedded
    private Schedules schedules;

    @Embedded
    private Todos todos;

    @Builder
    public Project(Long id, TaskManagement taskManagement) {
        this.id = id;
        this.taskManagement = taskManagement;
    }
}
