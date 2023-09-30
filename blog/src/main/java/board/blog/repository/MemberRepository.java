package board.blog.repository;

import board.blog.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByCode(Long code);
    Optional<Member> findByName(String name);
    Optional<Member> findById(String Id);
    Optional<Member> findByPasswd(String passwd);


    List<Member> findAll(); // 모든 회원 리스트 반환
}
