package com.hong.tmi.project;

import com.hong.tmi.domain.Member;
import com.hong.tmi.domain.Project;
import com.hong.tmi.domain.Schedule;
import com.hong.tmi.domain.Todo;
import com.hong.tmi.domain.common.TaskManagement;
import com.hong.tmi.project.dto.ProIndexDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@SpringBootTest
class ProjectServiceTest {
    @Autowired ProjectService projectService;
    @Autowired EntityManager em;

    @BeforeEach
    public void before(){
        Member member = Member.builder()
                .memberName("member1").build();
        em.persist(member);

        //진행중
        TaskManagement start = TaskManagement.builder()
                .startDate(LocalDateTime.now().minusMonths(1L))
                .endDate(LocalDateTime.now().plusMonths(1L))
                .member(member)
                .build();

        //시작전
        TaskManagement ready = TaskManagement.builder()
                .startDate(LocalDateTime.now().plusMonths(1L))
                .endDate(LocalDateTime.now().plusMonths(2L))
                .member(member)
                .build();

        //종료
        TaskManagement end = TaskManagement.builder()
                .startDate(LocalDateTime.now().minusMonths(1L))
                .endDate(LocalDateTime.now().minusDays(2L))
                .realEndDate(LocalDateTime.now().minusDays(2L))
                .member(member)
                .build();

        Project project1 = Project.builder().taskManagement(start).build();
        Project project2 = Project.builder().taskManagement(start).build();
        Project project3 = Project.builder().taskManagement(start).build();
        em.persist(project1);
        em.persist(project2);
        em.persist(project3);

        Schedule schedule1 = Schedule.builder().taskManagement(start).project(project1).build();
        Schedule schedule2 = Schedule.builder().taskManagement(start).project(project1).build();
        Schedule schedule3 = Schedule.builder().taskManagement(end).project(project1).build();
        em.persist(schedule1);
        em.persist(schedule2);
        em.persist(schedule3);

        Todo todo1 = Todo.builder().taskManagement(end).project(project1).schedule(schedule1).build();
        Todo todo2 = Todo.builder().taskManagement(start).project(project1).schedule(schedule1).build();
        Todo todo3 = Todo.builder().taskManagement(start).project(project1).schedule(schedule2).build();
        Todo todo4 = Todo.builder().taskManagement(end).project(project1).schedule(schedule2).build();
        Todo todo5 = Todo.builder().taskManagement(end).project(project1).schedule(schedule2).build();
        em.persist(todo1);
        em.persist(todo2);
        em.persist(todo3);
        em.persist(todo4);
        em.persist(todo5);

        em.flush();
        em.clear();
    }

    @Test
    public void index(){
        List<ProIndexDto> index = projectService.index(1L);
        for (ProIndexDto proIndexDto : index) {
            System.out.println("proIndexDto = " + proIndexDto);
        }
    }
}