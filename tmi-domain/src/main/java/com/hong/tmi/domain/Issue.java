package com.hong.tmi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 이슈 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Issue {
    @Id @GeneratedValue
    @Column(name = "issue_id")
    private Long id;

    private double issueImportant;

    @Column(name = "issue_dscr")
    private String issueDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_team_id")
    private ProTeam proTeam;

    // TODO: 2020-05-05 주석작성
    public enum IssueStatus{
        OCCUR, SOLVE
    }

}
