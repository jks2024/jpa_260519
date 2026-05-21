package com.human.jpa.entity;

import com.human.jpa.constant.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    // order_id PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // N:1 회원 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // enum 타입으로 주문 상태
    private OrderStatus orderStatus;

    // 등록일, 수정일
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
