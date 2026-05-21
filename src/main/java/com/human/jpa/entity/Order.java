package com.human.jpa.entity;

import com.human.jpa.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
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

    @OneToMany(mappedBy = "order")  // 내가 연관관계의 주인이 아님을 표시, OrderItem.order가 필드의 주인
    private List<OrderItem> orderItems = new ArrayList<>();

    // enum 타입으로 주문 상태
    private OrderStatus orderStatus;

    // 등록일, 수정일
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
