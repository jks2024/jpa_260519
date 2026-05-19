package com.human.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity   // 이 클래스가 JPA 엔티티임을 나타냄. Class -> DB Table
@Table(name = "member") // Member 클래스 이름이 DB Table로 만들어 질 때 이름을 지정, 지정하지 않으면 Camel case -> snake case
@Getter
@Setter
@NoArgsConstructor  // 매개변수가 없는 생성자 생성
@ToString(exclude = "pwd")
public class Member {
    @Id   // PK 역할을 하며, JPA에서는 반드시 있어야 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 생성 전략을 DB에 위임
    @Column(name = "member_id")  // db의 컬럼 이름을 member_id로 지정
    private Long id;

    @Column(unique = true, length = 150)  // eamil은 Unique 제약 조건을 가짐
    private String email;

    @Column(nullable = false)  // 비밀번호는 생략할 수 없음
    private String pwd;

    @Column(length = 30)  // 길이에 대한 제약 조건만 있음
    private String name;

    @Column(length = 255)
    private String image;  // 이미지의 졍로 저장

    private LocalDateTime createdDate;

    @PrePersist  // DB에 INSERT 되기 직전에 자동 호출 되는 메서드
    public void prePersist() {
        createdDate = LocalDateTime.now();  // 현재 날짜와 시간을 자동으로 기록
    }

}
