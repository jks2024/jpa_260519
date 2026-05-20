package com.human.jpa.repository;

import com.human.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 기본적인 CRUD는 포함되어 있음
    // save(entity) : 엔티티 저장 및 반환
    // findById(id) : ID로 엔티티 조회 SELECT * from item WHERE item_id = ?
    // findAll() : 모든 엔티티 조회 SELECT * from item
    // delete(id): ID로 엔티티 삭제
    // count() : 전체 엔티티 수 반환
}
