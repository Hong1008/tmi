package com.hong.tmi.project;

import com.hong.tmi.domain.Member;
import com.hong.tmi.domain.ProTeam;
import com.hong.tmi.domain.ProTeam.ProTeamLevel;
import com.hong.tmi.domain.Project;
import com.hong.tmi.domain.embed.Schedules;
import com.hong.tmi.domain.embed.Todos;
import com.hong.tmi.domain.repository.ProTeamRepository;
import com.hong.tmi.domain.repository.ProjectRepository;
import com.hong.tmi.project.dto.ProIndexDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProTeamRepository proTeamRepository;

    public List<ProIndexDto> index(Long memberId) {
        List<Project> listByLeader = projectRepository.findBelongedProjects(memberId);
        return listByLeader.stream().map(project -> {
            double progressRate = 0.0;
            Schedules schedules = project.getSchedules();
            double schEndCnt = schedules.getEndedCount();
            double schCnt = schedules.getCount();
            Todos todos = project.getTodos();
            double tdEndCnt = todos.getEndedCount();
            double tdCnt = todos.getCount();
            if (schCnt != 0) {
                progressRate = tdCnt == 0 ? schEndCnt / schCnt * 100 : (schEndCnt / schCnt + tdEndCnt / tdCnt) / 2 * 100;
            }
            return ProIndexDto.builder()
                    .proId(project.getId())
                    .progressRate((int) progressRate)
                    .dDay(project.getTaskManagement().calculateDDay())
                    .endDate(project.getTaskManagement().getEndDate().toLocalDate())
                    .leaderName(project.getTaskManagement().getMember().getMemberName())
                    .build();
        }).collect(Collectors.toList());
    }

    public void saveProject(Project project, Member leader, List<Long> memberList) {
        projectRepository.save(project);
        proTeamRepository.save(ProTeam.builder()
                .member(leader)
                .project(project)
                .proTeamLevel(ProTeamLevel.LEADER).build());
        for (Long id : memberList) {
            Member member = Member.builder().id(id).build();
            ProTeam proTeam = ProTeam.builder()
                    .member(member)
                    .project(project)
                    .proTeamLevel(ProTeamLevel.MEMBER).build();
            proTeamRepository.save(proTeam);
        }
    }
}
