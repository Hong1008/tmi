package com.hong.tmi.domain.common;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * 작업상태 코드
 */
@RequiredArgsConstructor
public enum TaskStatus {
    READY(day -> Duration.between(LocalDateTime.now(),day).toDays()),
    START(day -> Duration.between(day,LocalDateTime.now()).toDays()),
    TIMEOUT(day -> Duration.between(day,LocalDateTime.now()).toDays()),
    END(day -> Duration.between(day,LocalDateTime.now()).toDays());

    private final Function<LocalDateTime, Long> dDayExpression;

    public long calculateDDay(LocalDateTime day){
        return dDayExpression.apply(day);
    }
}
