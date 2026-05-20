package com.human.jpa.repository;

import com.human.jpa.constant.ItemSellStatus;
import com.human.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 기본적인 CRUD는 포함되어 있음
    // save(entity) : 엔티티 저장 및 반환
    // findById(id) : ID로 엔티티 조회 SELECT * from item WHERE item_id = ?
    // findAll() : 모든 엔티티 조회 SELECT * from item
    // delete(id): ID로 엔티티 삭제
    // count() : 전체 엔티티 수 반환
    // 상품 이름으로 조회

    // 쿼리메서드 : Spring Data JPA에서는 메서드 이름을 통해 쿼리를 자동으로 생성할 수 있음
    // 형식 : [메서드 키워드] + By + [조건 필드] + [추가 조건]
    // 1. findBy : 특정 조건에 따라 데이터를 조회
    // 2. countBy : 특정 조건에 해당하는 데이터의 개수를 조회할 때 사용
    // 3. existsBy : 특정 조건에 해당하는 데이터가 존재하는 지 여부를 조회할 때 사용
    // 4. deleteBy : 특정 조건에 따라 데이터를 삭제 할 때 사용

    // 주요키워드
    // And : 여러 조건을 AND로 연결
    // Or : 여러 조건을 OR로 연결
    // Between : 특정 범위 내에 있는 데이터를 조회
    // LessThan, GreaterThan** : 크거나 작은 값을 비교하여 조회
    // Like : SQL의 LIKE 연산자와 동일하게 패턴을 사용하여 조회
    // OrderBy : 정렬 조건을 지정

    // 문제 1. 상품명이 "테스트 상품5"인 상품을 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByItemName(String itemName);
    // 문제 2. 상품명이 "테스트 상품1" 이거나 상세설명이 "테스트 상품 상세 설명2"인 상품을 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);
    // 문제 3. 가격이 50000원 미만인 상품을 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByPriceLessThan(int price);
    // 문제 4. 가격이 50000원 미만인 상품을 가격 내림차순으로 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);
    // 문제 5. 상세설명에 "상세 설명1"이라는 키워드가 포함된 상품을 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByItemDetailContaining(String itemDetail);
    // 문제 6. 판매 상태가 SELL인 상품 목록과 SOLD_OUT인 상품 목록을 각각 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByItemSellStatus(ItemSellStatus itemSellStatus);
    // 문제 7. 가격이 30000원 이상 70000원 이하인 상품을 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByPriceBetween(int startPrice, int endPrice);
    // 문제 8. 판매 상태가 SELL인 상품의 개수와 SOLD_OUT인 상품의 개수를 각각 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    long countByItemSellStatus(ItemSellStatus itemSellStatus);
    // 문제 9. 상품명 "테스트 상품1"이 존재하는지 여부와 "없는 상품"이 존재하는지 여부를 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    boolean existsByItemName(String itemName);
    // 문제 10. 재고가 50 미만인 상품을 재고 오름차순으로 조회하는 쿼리 메서드를 작성하고 테스트하시오.
    List<Item> findByStockNumberLessThanOrderByStockNumberAsc(int stockNumber);
}
