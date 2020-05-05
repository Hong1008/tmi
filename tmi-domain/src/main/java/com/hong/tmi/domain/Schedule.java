package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseEntity;
import com.hong.tmi.domain.embed.TaskManagement;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 스케줄 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="schedule_name")),
            @AttributeOverride(name = "description", column = @Column(name="schedule_dscr"))
    })
    @Embedded
    private TaskManagement taskManagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "schedule")
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Schedule(TaskManagement taskManagement, Project project) {
        this.taskManagement = taskManagement;
        this.project = project;
    }
}
