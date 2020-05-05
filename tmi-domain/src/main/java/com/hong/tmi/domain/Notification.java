package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 알림 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "noti_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotiStatus notiStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    // TODO: 2020-05-05 주석작성
    public enum NotiStatus{
        READ, UNREAD
    }


}
