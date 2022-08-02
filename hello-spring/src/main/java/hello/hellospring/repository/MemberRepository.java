package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findMemberByMemberIdAndMemberPassword(String loginId, String password);
}
