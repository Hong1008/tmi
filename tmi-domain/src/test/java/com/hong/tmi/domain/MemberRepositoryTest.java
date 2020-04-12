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
    @Autowired MemberRepository memberRepository;

    @Rollback(false)
    @Test
    public void save() throws Exception{
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
    public void note() throws Exception{
        List<String> arr = new ArrayList<>();
        arr.add("가나");
        arr.add("너냐");
        arr.add("나다");

        List<String> result = arr.stream().filter(s -> s.equals("나다")).collect(Collectors.toList());

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}