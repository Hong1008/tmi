package com.hong.tmi.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * 프로젝트, 스케줄, 할일 공통값타입
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TaskManagement {
    private String name;
    private String info;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime realEndDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public TaskManagement(String name, String info,
                          LocalDateTime startDate, LocalDateTime endDate, LocalDateTime realEndDate,
                          TaskStatus taskStatus) {
        this.name = name;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
        this.realEndDate = realEndDate;
        this.taskStatus = taskStatus;
    }

    public TaskStatus calculateStatus() {
        if (realEndDate == null) {
            if(startDate.isAfter(LocalDateTime.now())){
                return TaskStatus.READY;
            }
            if (endDate.isBefore(LocalDateTime.now())) {
                return TaskStatus.START;
            } else {
                return TaskStatus.TIMEOUT;
            }
        } else {
            return TaskStatus.END;
        }
    }
}
