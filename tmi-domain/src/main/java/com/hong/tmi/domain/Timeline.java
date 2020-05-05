package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * 타임라인 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Timeline extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "timeline_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TlTypeStatus tlTypeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Getter
    @RequiredArgsConstructor
    private enum Type {
        SCHEDULE(Schedule.class), TODO(Todo.class), ISSUE(Issue.class);

        private final Class<?> type;
    }

    // TODO: 2020-05-05 주석 작성
    public enum TlTypeStatus {
        START, END
    }
}
