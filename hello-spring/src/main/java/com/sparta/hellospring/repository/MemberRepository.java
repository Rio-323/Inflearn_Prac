package com.sparta.hellospring.repository;

import com.sparta.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); // null값이 있을 수 있기 때문에 Optional사용
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
