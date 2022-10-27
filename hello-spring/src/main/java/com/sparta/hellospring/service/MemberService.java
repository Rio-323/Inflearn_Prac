package com.sparta.hellospring.service;

import com.sparta.hellospring.domain.Member;
import com.sparta.hellospring.repository.MemberRepository;
import com.sparta.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // DI 의존성 주입이라고 함.

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember ( member );

        memberRepository.save ( member );

        return member.getId ();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName ( member.getName () )
                .ifPresent ( m -> {
            throw new IllegalStateException ("이미 존재하는 회원 입니다.");
             });
    }

    public List<Member> findMembers() {
        // 전체 회원 조회
        return memberRepository.findAll ();
    }

    public Optional<Member> findOne(Long memberId) {
        // 회원 한명 조회
        return memberRepository.findById ( memberId );
    }

}
