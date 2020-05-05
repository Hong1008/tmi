package com.hong.tmi.domain;

import com.hong.tmi.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 채팅 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @Lob
    private String chatContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_team_id")
    private ProTeam proTeam;
}
