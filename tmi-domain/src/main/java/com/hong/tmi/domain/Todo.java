package com.hong.tmi.domain;

import com.hong.tmi.domain.common.TaskManagement;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="todo_name")),
            @AttributeOverride(name = "info", column = @Column(name="todo_info"))
    })
    @Embedded
    private TaskManagement taskManagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sch_id")
    private Schedule schedule;

    @Builder
    public Todo(TaskManagement taskManagement, Project project, Schedule schedule) {
        this.taskManagement = taskManagement;
        this.project = project;
        this.schedule = schedule;
    }
}
