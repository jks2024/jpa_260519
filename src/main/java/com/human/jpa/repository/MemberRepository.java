package com.human.jpa.repository;
import com.human.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// DB와의 연결 통로
// 일반적으로 DB SQL 직접 작성 하지 않음
// 쿼리메서드의 작명 규칙에 따라 메서드 이름을 작성하면 hibernate 구현체가 실제 쿼리문을 생성 해줌
// JpaRepository를 상속 받았기 때문에 기본적인 CRUD는 만들 필요가 없음

// @Component를 상속 받아서 만들어 졌으며, Spring Container 에 Bean 등록
@Repository                                         // <엔티티, PK의 데이터 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 기본적인 CRUD는 만들지 않아도 동작 함
    // 이메일로 회원 정보 가져 오기
    // Optional은 Wrapper 클래스로 Null Pointer Exception 방지 목적으로 사용
    // SELECT * FROM member WHERE email = ?
    Optional<Member> findByEmail(String email);  // Query method 방식으로 설계 명세를 작성
    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);
    // 이메일과 비밀번호가 일치하면 엔티티 반환
    Optional<Member> findByEmailAndPwd(String email, String password);
}
