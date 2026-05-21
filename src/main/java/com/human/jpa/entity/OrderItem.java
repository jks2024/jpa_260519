package com.human.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class OrderItem {
    // order_item_od PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // N:1 연관 관계 매핑, 한개의 아이템은 주문서 내에 여러 아이템으로 사용 될 수 있음
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // N:1, 한개의 주문서에 여러개의 OrderItem 이 있을 수 있음
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;
    private int quantity;
    // 등록일과 수정일
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}
