package com.sparta.hellospring.repository;

import com.sparta.hellospring.domain.Member;
import com.sparta.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository ();

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository ();
        memberService = new MemberService (memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore ();
    }

    @Test
    public void save() {
        Member member = new Member ();
        member.setName ( "Spring" );

        memoryMemberRepository.save ( member );

        Member result = memoryMemberRepository.findById ( member.getId () ).get ();

        // Assertions.assertEquals ( member, result );
        assertThat ( member ).isEqualTo ( result );
    }

    @Test
    public void findByName() {
        Member member1 = new Member ();
        member1.setName ( "Spring1" );
        memoryMemberRepository.save ( member1 );

        Member member2 = new Member ();
        member2.setName ( "Spring2" );
        memoryMemberRepository.save ( member2 );

        Member result = memoryMemberRepository.findByName ( "Spring1" ).get ();

        assertThat ( result ).isEqualTo ( member1 );
    }

    @Test
    public void findAll() {
        Member member1 = new Member ();
        member1.setName ( "Spring1" );
        memoryMemberRepository.save ( member1 );

        Member member2 = new Member ();
        member2.setName ( "Spring2" );
        memoryMemberRepository.save ( member2 );

        List<Member> result = memoryMemberRepository.findAll ();

        assertThat ( result.size () ).isEqualTo ( 2 );
    }
}
