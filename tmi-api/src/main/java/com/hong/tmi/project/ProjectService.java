package com.hong.tmi.project;

import com.hong.tmi.domain.Project;
import com.hong.tmi.domain.ProjectRepository;
import com.hong.tmi.domain.Schedule;
import com.hong.tmi.domain.Todo;
import com.hong.tmi.project.dto.ProIndexDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hong.tmi.domain.common.TaskManagement.TaskStatus;

//@RequiredArgsConstructor
@Transactional
@Service
public class ProjectService {

    @Autowired private ProjectRepository projectRepository;

    public List<ProIndexDto> index(Long memberId){
        List<Project> listByLeader = projectRepository.findByLeader(memberId);
        return listByLeader.stream().map(project -> {
            double progressRate = 0.0;
            List<Schedule> scheduleList = project.getSchedules();
            double schEndCnt = scheduleList.stream().filter(schedule -> schedule.getTaskManagement().getTaskStatus().equals(TaskStatus.END)).count();
            double schCnt = scheduleList.size();
            List<Todo> todoList = project.getTodos();
            double tdEndCnt = todoList.stream().filter(todo -> todo.getTaskManagement().getTaskStatus().equals(TaskStatus.END)).count();
            double tdCnt = todoList.size();
            if(schCnt!=0){
                progressRate = tdCnt== 0 ? schEndCnt/schCnt*100 :  (schEndCnt/schCnt + tdEndCnt/tdCnt)/2*100;
            }
            return ProIndexDto.builder()
                    .progressRate((int)progressRate)
                    .dDay(project.getTaskManagement().calculateDDay())
                    .endDate(project.getTaskManagement().getEndDate().toLocalDate())
                    .leaderName(project.getTaskManagement().getMember().getMemberName())
                    .build();
        }).collect(Collectors.toList());
    }
}
