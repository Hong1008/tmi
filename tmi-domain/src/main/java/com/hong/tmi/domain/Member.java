package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

//    @Column(nullable = false)
    private String memberName;

//    @Column(nullable = false)
    private String email;

    private String picture;

    @Builder
    public Member(Long id, String memberName, String email, String picture) {
        this.id = id;
        this.memberName = memberName;
        this.picture = picture;
        this.email = email;
    }

    public Member update(String name, String picture){
        this.memberName = name;
        this.picture = picture;

        return this;
    }

}
