package com.hong.tmi.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class ProIndexDto {
    private Long proId;
    private String leaderName;
    private int progressRate;
    private LocalDate endDate;
    private long dDay;


}
