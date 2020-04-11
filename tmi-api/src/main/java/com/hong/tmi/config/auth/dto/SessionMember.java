package com.hong.tmi.config.auth.dto;

import com.hong.tmi.domain.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * Member엔티티를 세션 값으로 사용하기 위한 DTO
 */
@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionMember(Member member) {
        name = member.getName();
        email = member.getEmail();
        picture = member.getPicture();
    }
}
