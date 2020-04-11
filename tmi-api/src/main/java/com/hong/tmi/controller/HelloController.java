package com.hong.tmi.controller;

import com.hong.tmi.config.auth.dto.SessionMember;
import com.hong.tmi.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class HelloController {
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String hello(HttpSession session){
        SessionMember member = (SessionMember) session.getAttribute("user");
        return member != null ? member.getName() : "로그인하세요";
    }


}
