package com.human.jpa.repository;

import com.human.jpa.constant.ItemSellStatus;
import com.human.jpa.entity.Cart;
import com.human.jpa.entity.CartItem;
import com.human.jpa.entity.Item;
import com.human.jpa.entity.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Transactional
@Slf4j
@TestPropertySource("classpath:application-test.properties")  // 테스트에 대한 별도의 데이터베이스 환경
class CartItemRepositoryTest {
    // 회원, 아이템, 카트, 카트아이템 의존성 주입
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository  cartItemRepository;

    // 회원 정보 메서드 구현 : 반환이 회원 엔티티
    public Member createMember() {
        Member member = new Member();
        member.setName("곰돌이");
        member.setEmail("jks2024@gmail.com");
        member.setPwd("sphb8250");
        member.setImage("http://google.co.kr");
        return memberRepository.save(member);
    }

    // 상품 생성 메서드 구현 : 반환이 상품 엔티티
    public Item createItem() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    // 장바구니 생성 및 장바구니 담기
    // 1. 회원 정보 메서드 호출
    // 2. 상품 생성 메서드 호출
    // 3. 장바구니 생성
    // 4. 장바구니에 담을 상품 정보 생성
    // 5. 저장된 장바구니 상품 조회
    @Test
    @DisplayName("장바구니 담기 테스트")
    public void saveCartItemTest() {
        Member member = createMember();
        Item item = createItem();

        Cart cart = new Cart();
        cart.setCartName("테스트 장바구니");
        cart.setMember(member);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setQuantity(1);
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        // 저장된 장바구니 상품 조회
        CartItem findCartItem = cartItemRepository.findById(cartItem.getId())
                .orElseThrow(RuntimeException::new);

        // 검증
        log.error("savedCartItem: {}", savedCartItem);
        log.error("findCartItem: {}", findCartItem);

    }




}