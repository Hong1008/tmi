package com.hong.tmi.domain;

import com.hong.tmi.domain.common.TaskManagement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


}
