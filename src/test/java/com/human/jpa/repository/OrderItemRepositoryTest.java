package com.human.jpa.repository;

import com.human.jpa.constant.ItemSellStatus;
import com.human.jpa.constant.OrderStatus;
import com.human.jpa.entity.Item;
import com.human.jpa.entity.Member;
import com.human.jpa.entity.Order;
import com.human.jpa.entity.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@TestPropertySource("classpath:application-test.properties")
class OrderItemRepositoryTest {
    // 의존성 주입 : 회원, 아이템, 주문서, 주문목록
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @PersistenceContext
    EntityManager em;

    // 회원 정보 생성
    public Member createMember() {
        Member member = new Member();
        member.setName("곰돌이");
        member.setEmail("jks2024@gmail.co,");
        member.setPwd("sphb8250");
        member.setImage("http://google.co.kr");
        return memberRepository.save(member);
    }

    // 상품 정보 생성 : Item
    public Item createItem() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setItemDetail("테스트 상품 상세 설명");
        item.setPrice(10000);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    // 주문서 생성 : Order
    public Order createOrder() {
        Member member = createMember();
        Order order = new Order();
        order.setMember(member);
        order.setOrderStatus(OrderStatus.ORDER);
        order.setRegTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 테스트로직 작성
    // 1. 주문 아이템 생성 : OrderItem
    // 2. 주문 목록 검증
    @Test
    @DisplayName("삼품 주문 단위 테스트")
    public void saveOrderItemTest() {
        Order order = createOrder();
        Item item1 = createItem();
        Item item2 = createItem();
        Item item3 = createItem();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrder(order);
        orderItem1.setItem(item1);
        orderItem1.setQuantity(1);
        orderItemRepository.save(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrder(order);
        orderItem2.setItem(item2);
        orderItem2.setQuantity(2);
        orderItemRepository.save(orderItem2);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setOrder(order);
        orderItem3.setItem(item3);
        orderItem3.setQuantity(3);
        orderItemRepository.save(orderItem3);

        em.flush();
        em.clear();

        OrderItem findOrderItem1 = orderItemRepository.findById(orderItem1.getId())
                .orElseThrow(RuntimeException::new);
        OrderItem findOrderItem2 = orderItemRepository.findById(orderItem2.getId())
                .orElseThrow(RuntimeException::new);
        OrderItem findOrderItem3 = orderItemRepository.findById(orderItem3.getId())
                .orElseThrow(RuntimeException::new);

        log.error("findOrderItem1: {}", findOrderItem1);
        log.error("findOrderItem2: {}", findOrderItem2);
        log.error("findOrderItem3: {}", findOrderItem3);
    }
}