package com.hong.tmi.domain;

import com.hong.tmi.domain.Member.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Rollback(false)
    @Test
    public void save() throws Exception {
        //given
        Member member = Member.builder()
                .memberName("member1")
                .email("email1")
                .memberRole(MemberRole.GUEST).build();

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Assertions.assertThat(saveMember.getId()).isEqualTo(member.getId());
    }

    @Test
    public void note() throws Exception {
        List<Project> arr = new ArrayList<>();
        Project project = new Project();
        Project project2 = new Project();
        arr.add(project);
        arr.add(project2);
        List<Project> result = arr.stream().filter(
                s -> s.equals(project) && s.getTaskManagement() == null).collect(Collectors.toList());

        for (Project project1 : result) {
            System.out.println("project1.toString() = " + project1.toString());
        }
    }
}