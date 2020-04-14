package com.hong.tmi.domain;

import com.hong.tmi.domain.common.TaskManagement;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 프로젝트 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project {
    @Id @GeneratedValue
    @Column(name = "pro_id")
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name="pro_name")),
            @AttributeOverride(name = "info", column = @Column(name="pro_info"))
    })
    @Embedded
    private TaskManagement taskManagement;

    @OneToMany(mappedBy = "project")
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Project(Long id, TaskManagement taskManagement) {
        this.id = id;
        this.taskManagement = taskManagement;
    }
}
