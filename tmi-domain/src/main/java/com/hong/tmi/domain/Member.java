package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @Builder
    public Member(Long id, String name, String email, String picture, MemberRole memberRole) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.memberRole = memberRole;
    }

    public Member update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    @Getter
    @RequiredArgsConstructor
    public enum MemberRole{
        GUEST("ROLE_GUEST","게스트"),
        USER("ROLE_USER", "사용자");

        private final String key;
        private final String title;
    }
}
