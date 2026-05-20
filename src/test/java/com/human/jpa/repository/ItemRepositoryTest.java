package com.human.jpa.repository;

import com.human.jpa.constant.ItemSellStatus;
import com.human.jpa.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
@TestPropertySource("classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;  // 필드를 통한 의존성 주입

    @Test
    @DisplayName("상품저장 테스트")
    public void saveTest() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemName("테스트 상품" + i);
            item.setPrice(10000);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(i % 2 == 0 ? ItemSellStatus.SELL : ItemSellStatus.SOLD_OUT);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    // 상품 조회 테스트
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest() {
        saveTest();
        List<Item> list = itemRepository.findByItemName("테스트 상품5");
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 2. 상품명 OR 상세 설명
    @Test
    @DisplayName("예제 2 - 상품명 OR 상세설명으로 조회")
    public void findByItemNameOrItemDetailTest() {
        saveTest();
        List<Item> list = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "");
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 3. 가격이 특정 금액 미만인 상품 조회
    @Test
    @DisplayName("예제 3 - 가격 미만으로 조회")
    public void findByPriceLessThanTest() {
        saveTest();
        List<Item> list = itemRepository.findByPriceLessThan(50000);
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 4. 가격 미만 + 가격 내림차순 정렬
    @Test
    @DisplayName("예제 4 - 가격 미만 + 내림차순 정렬")
    public void findByPriceLessThanOrderByPriceDescTest() {
        saveTest();
        List<Item> list = itemRepository.findByPriceLessThanOrderByPriceDesc(50000);
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 5. 상세 설명에 키워드 포함 (LIKE)
    @Test
    @DisplayName("예제 5 - 상세 설명 키워드 검색")
    public void findByItemDetailContainingTest() {
        saveTest();
        List<Item> list = itemRepository.findByItemDetailContaining("상세 설명 1");

        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 6. 판매 상태로 조회
    @Test
    @DisplayName("판매 상태로 조회")
    public void findByItemSellStatusTest() {
        saveTest();
        List<Item> sellList = itemRepository.findByItemSellStatus(ItemSellStatus.SELL);
        List<Item> soldOutList = itemRepository.findByItemSellStatus(ItemSellStatus.SOLD_OUT);
        log.info("판매중: {}건 / 품절: {}건",
                sellList.size(), soldOutList.size());
    }

    // 7. 가격 범위로 조회 (Between)
    @Test
    @DisplayName("가격 범위로 조회")
    public void findByPriceBetweenTest() {
        saveTest();
        List<Item> list = itemRepository.findByPriceBetween(30000, 70000);
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 8. 판매 상태별 상품 수 조회
    @Test
    @DisplayName("판매 상태별 상품 수 조회")
    public void countByItemSellStatusTest() {
        saveTest();
        long sellCnt = itemRepository.countByItemSellStatus(ItemSellStatus.SELL);
        long soldOutCnt = itemRepository.countByItemSellStatus(ItemSellStatus.SOLD_OUT);

        log.info("판매중: {}건 / 품절: {}건", sellCnt, soldOutCnt);
    }

    // 9. 상품명 존재 여부 확인
    @Test
    @DisplayName("상품명 존재 여부 확인")
    public void existsByItemNameTest() {
        saveTest();
        boolean exists = itemRepository.existsByItemName("테스트 상품1");
        boolean notExists = itemRepository.existsByItemName("없는 상품");

        log.info("테스트 상품1 존재: {}", exists);
        log.info("없는 상품 존재: {}", notExists);
    }

    // 10. 재고 부족 상품 조회 + 재고 오름 차순 정렬
    @Test
    @DisplayName("재고 부족 상품 조회")
    public void findByStockNumberLessThanOrderByStockNumberAscTest() {
        saveTest();
        List<Item> list = itemRepository.findByStockNumberLessThanOrderByStockNumberAsc(50);
        for (Item item : list) {
            System.out.println(item);
        }
    }
}