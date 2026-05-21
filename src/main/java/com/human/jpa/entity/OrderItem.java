package com.human.jpa.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class OrderItem {
    // order_item_od PK

    // N:1 연관 관계 매핑, 한개의 아이템은 주문서 내에 여러 아이템으로 사용 될 수 있음

    // N:1, 한개의 주문서에 여러개의 OrderItem 이 있을 수 있음

    private int orderPrice;
    private int quantity;
    // 등록일과 수정일

}
