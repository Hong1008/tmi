package com.hong.tmi.domain.repository;

import com.hong.tmi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailContaining(String searchWord);
}
