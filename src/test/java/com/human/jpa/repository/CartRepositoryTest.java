package com.human.jpa.repository;


import com.human.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest  // 테스트 환경 설정
@Slf4j  // log 메시지를 효율적으로 출력
@Transactional // 데이터베이스의 논리적인 작업 단위, 모두가 성공하지 않으면 자동 롤백
@TestPropertySource("classpath:application-test.properties")  // 테스트에 대한 별도의 데이터베이스 환경
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;  // 스프링 컨테이너에서 해당 빈에 해당하는 의존성을 주입 받음

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext // JPA의 EntityMananger를 주입
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMember() {
        Member member = new Member();
        member.setEmail("jks2024@gmail.com");;
        member.setName("곰돌이사육사");
        member.setPwd("sphb8250");
        member.setImage("");
        member.setCreatedDate(LocalDateTime.now());
        return member;
    }



}