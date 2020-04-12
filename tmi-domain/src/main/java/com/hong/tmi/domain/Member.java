package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 사용자 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @Builder
    public Member(Long id, String memberName, String email, String picture, MemberRole memberRole) {
        this.id = id;
        this.memberName = memberName;
        this.picture = picture;
        this.email = email;
        this.memberRole = memberRole;
    }

    public Member update(String name, String picture){
        this.memberName = name;
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
