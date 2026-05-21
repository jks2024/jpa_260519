package com.human.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    // order_id PK

    // N:1 회원 연관관계 매핑

    // 주문일

    // enum 타입으로 주문 상태

    // 등록일, 수정일
}
