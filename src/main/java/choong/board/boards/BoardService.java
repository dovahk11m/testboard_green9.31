package choong.board.boards;

import choong.board._core.exceptions.Exception403;
import choong.board._core.exceptions.Exception404;
import choong.board._core.paging.PageResponseDTO;
import choong.board.members.Member;
import choong.board.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //목록 조회
    public PageResponseDTO<BoardResponse.ListDTO> list(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAllWithDetails(pageable);
        return PageResponseDTO.from(boardPage, BoardResponse.ListDTO::new);
    }

    //상세보기
    public BoardResponse.DetailDTO detail(Long boardId, Long sessionUserId) {
        Board board = boardRepository.findByIdWithDetails(boardId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다"));
        return new BoardResponse.DetailDTO(board, sessionUserId);
    }

    //글 작성
    @Transactional
    public Board save(BoardRequest.SaveDTO saveDTO, Long sessionUserId){
        Member member = memberRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception403("존재하지 않는 사용자입니다."));
        Board board = saveDTO.toEntity(member);
        return boardRepository.save(board);
    }

    //수정폼 반환
    public BoardResponse.UpdateDTO getPost(Long boardId, Long sessionUserId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다."));
        if (!board.isOwner(sessionUserId)) {
            throw new Exception403("게시글을 수정/삭제할 권한이 없습니다.");
        }
        // 3. DTO로 변환하여 반환
        return new BoardResponse.UpdateDTO(board);
    }

    //글 수정
    @Transactional
    public void update(Long boardId, BoardRequest.UpdateDTO updateDTO, Long sessionUserId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다."));
        if (!board.isOwner(sessionUserId)) {
            throw new Exception403("게시글을 수정/삭제할 권한이 없습니다.");
        }
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    }

    //글 삭제
    @Transactional
    public void delete(Long boardId, Long sessionUserId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다."));
        if (!board.isOwner(sessionUserId)) {
            throw new Exception403("게시글을 수정/삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);
    }
}
