package com.hong.tmi.controller;

import com.hong.tmi.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {
    private final MemberRepository memberRepository;

    @GetMapping("/hello")
    public String hello(){
        return memberRepository.count()+"";
    }

}
