package com.commerce.backendserver.domain.member;

import com.commerce.backendserver.domain.common.entity.BaseEntity;
import lombok.*;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user_tb")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;

    public static Member of(
            String nickName
    ) {
        return Member.builder()
                .nickName(nickName)
                .build();
    }
}
