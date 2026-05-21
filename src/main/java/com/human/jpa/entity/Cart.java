package com.human.jpa.entity;

// Member <-> Cart를 1대1 연관관계 매핑

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cartName;

    @OneToOne // 1대1 연관 관계 매핑
    @JoinColumn(name = "member_id")  // 매핑할 외래키
    private Member member;  // 해당 객체의 주소를 대입
}
