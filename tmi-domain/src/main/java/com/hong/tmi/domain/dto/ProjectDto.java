package com.hong.tmi.domain.dto;

import com.hong.tmi.domain.Member;
import com.hong.tmi.domain.Project;
import com.hong.tmi.domain.common.TaskManagement;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private Long proId;
    private String proName;
    private String proInfo;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime realEndDate;
    private Long leaderId;

    public ProjectDto(Project project) {
        this.proId = project.getId();
        this.proName = project.getTaskManagement().getName();
        this.proInfo = project.getTaskManagement().getInfo();
        this.startDate = project.getTaskManagement().getStartDate();
        this.endDate = project.getTaskManagement().getEndDate();
        this.realEndDate = project.getTaskManagement().getRealEndDate();
        this.leaderId = project.getTaskManagement().getMember().getId();
    }

    public Project toEntity(){
        TaskManagement taskManagement = TaskManagement.builder()
                .name(proName)
                .info(proInfo)
                .startDate(startDate)
                .endDate(endDate)
                .realEndDate(realEndDate)
                .member(Member.builder().id(leaderId).build()).build();
        return Project.builder()
                .id(proId)
                .taskManagement(taskManagement).build();
    }
}
