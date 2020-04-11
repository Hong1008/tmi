package com.hong.tmi.domain;

import com.hong.tmi.domain.Member.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Rollback(false)
    @Test
    public void save() throws Exception{
        //given
        Member member = Member.builder()
                .name("member1")
                .email("email1")
                .memberRole(MemberRole.GUEST).build();

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Assertions.assertThat(saveMember.getId()).isEqualTo(member.getId());
    }
}