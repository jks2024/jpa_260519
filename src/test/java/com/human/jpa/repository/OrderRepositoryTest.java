package com.human.jpa.repository;

import com.human.jpa.constant.ItemSellStatus;
import com.human.jpa.entity.Item;
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

@SpringBootTest
@Slf4j
@Transactional
@TestPropertySource("classpath:application-test.properties")
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return item;
    }

    @Test
    @DisplayName("영속성 전의 테스트")
    public void cascadeTest() {
        Order order = new Order();
        for (int i =0; i < 3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(10);
            orderItem.setOrder(order);
            orderItem.setOrderPrice(1000);
            order.getOrderItems().add(orderItem);  // OneToMany로 추가된 List
        }
        orderRepository.saveAndFlush(order);  // 저장 및 강제 flush
        em.clear();
        Order findOrder = orderRepository.findById(order.getId()).orElseThrow(RuntimeException::new);
        log.error("findOrder: {}", findOrder.getOrderItems().size());
    }

}