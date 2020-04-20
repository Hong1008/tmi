package com.hong.tmi.domain.embed;

import com.hong.tmi.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
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
    private String info;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime realEndDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Member member;

    @Builder
    public TaskManagement(String name, String info,
                          LocalDateTime startDate, LocalDateTime endDate, LocalDateTime realEndDate,
                          Member member) {
        this.name = name;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
        this.realEndDate = realEndDate;
        this.taskStatus = calculateStatus();
        this.member = member;
    }

    public TaskStatus calculateStatus() {
        if (realEndDate == null) {
            if(startDate.isAfter(LocalDateTime.now())){
                return TaskStatus.READY;
            }
            return endDate.isAfter(LocalDateTime.now()) ? TaskStatus.START : TaskStatus.TIMEOUT;
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
