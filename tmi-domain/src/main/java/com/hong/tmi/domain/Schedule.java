package com.hong.tmi.domain;

import com.hong.tmi.domain.common.TaskManagement;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule {
    @Id @GeneratedValue
    @Column(name = "sch_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="sch_name")),
            @AttributeOverride(name = "info", column = @Column(name="sch_info"))
    })
    @Embedded
    private TaskManagement taskManagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Project project;

    @OneToMany(mappedBy = "schedule")
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Schedule(TaskManagement taskManagement, Project project) {
        this.taskManagement = taskManagement;
        this.project = project;
    }
}
