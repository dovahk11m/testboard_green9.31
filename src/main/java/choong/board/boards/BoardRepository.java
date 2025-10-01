package choong.board.boards;

import choong.board.members.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //회원으로 찾기
    List<Board> findByMemberId(Long memberId);

    // 목록 조회: 페이징 유지. Member만 Fetch Join하여 N+1 방지.
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.member ORDER BY b.id DESC")
    Page<Board> findAllWithDetails(Pageable pageable);

    // 단일 조회: 페이징 필요 없음. Member만 Fetch Join하여 N+1 방지.
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.member m WHERE b.id = :id")
    Optional<Board> findByIdWithDetails(@Param("id") Long id);

}
