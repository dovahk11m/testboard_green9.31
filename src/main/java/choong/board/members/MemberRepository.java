package choong.board.members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional; // [추가]

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원찾기
    Optional<Member> findByEmail(String email);

    // 이메일 중복체크
    boolean existsByEmail(String email);

}
