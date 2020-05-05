package com.hong.tmi.domain.embed;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * 프로젝트, 스케줄, 할일 공통값타입
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TaskManagement {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime realEndDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Builder
    public TaskManagement(String name, String description,
                          LocalDate startDate, LocalDate endDate, LocalDateTime realEndDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.realEndDate = realEndDate;
        this.taskStatus = calculateStatus();
    }

    public TaskStatus calculateStatus() {
        if (realEndDate == null) {
            if(startDate.isAfter(LocalDate.now())){
                return TaskStatus.READY;
            }
            return endDate.isAfter(LocalDate.now()) ? TaskStatus.START : TaskStatus.TIMEOUT;
        } else {
            return TaskStatus.END;
        }
    }

    public long calculateDDay(){
        return taskStatus.dDayExpression.apply(this);
    }

    /**
     * 작업상태 코드
     */
    @RequiredArgsConstructor
    public enum TaskStatus {
        READY(task -> Duration.between(LocalDateTime.now(),task.startDate).toDays()),
        START(task -> Duration.between(task.startDate,LocalDateTime.now()).toDays()),
        TIMEOUT(task -> Duration.between(task.endDate,LocalDateTime.now()).toDays()),
        END(task -> Duration.between(task.endDate,LocalDateTime.now()).toDays());

        private final Function<TaskManagement, Long> dDayExpression;
    }
}
