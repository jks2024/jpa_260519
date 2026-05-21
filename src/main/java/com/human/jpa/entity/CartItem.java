package com.human.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@ToString
public class CartItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne  // N:1, Cart에는 여러개의 CartItem 이 존재 할 수 있음 (하나의 장바구니에 여러 장바구니 상품이 담김)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne // N:1, 하나의 Item 이 여러 CartItem에 담길 수 있음 (하나의 삼풍미 여러 장자구니의 상품으로 담길 수 있음)
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;
}
