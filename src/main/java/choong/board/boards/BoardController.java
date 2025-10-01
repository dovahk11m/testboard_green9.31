package choong.board.boards;

import choong.board._core.SessionUser;
import choong.board._core.paging.PageResponseDTO;
import choong.board.members.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    //전체보기 폼 요청
    @GetMapping("/boards")
    public String list(Model model, @PageableDefault() Pageable pageable, HttpSession session) {
        PageResponseDTO<BoardResponse.ListDTO> responseDTO = boardService.list(pageable);
        model.addAttribute("pageData", responseDTO);
        model.addAttribute("sessionUser", session.getAttribute("sessionUser"));
        return "mboards/index";
    }

    @GetMapping({"/", ""})
    public String homeRedirect() {
        return "redirect:/boards";
    }

    //상세보기 폼 요청
    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        Long sessionUserId = sessionUser != null ? sessionUser.getId() : null;
        BoardResponse.DetailDTO detailDTO = boardService.detail(id, sessionUserId);
        model.addAttribute("boards", detailDTO);
        return "mboards/detail";
    }

    //작성 폼 요청
    @GetMapping("boards/save-form")
    public String saveForm(Model model, HttpSession session) {
        if (session.getAttribute("sessionUser") == null) return "redirect:/login-form";
        return "mboards/save-form";
    }

    //수정 폼 요청
    @GetMapping("boards/update-form/{id}")
    public String updateForm(@PathVariable Long id, Model model,
                             HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login-form";
        BoardResponse.UpdateDTO updateDTO = boardService.getPost(id, sessionUser.getId());
        model.addAttribute("boards", updateDTO);
        return "mboards/update-form";
    }

    //글 작성
    @PostMapping("boards/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login-form";
        Board board = boardService.save(saveDTO, sessionUser.getId());
        return "redirect:/boards/" + board.getId();
    }

    //글 수정
    @PostMapping("/boards/update/{id}")
    public String update(@PathVariable Long id, @Valid BoardRequest.UpdateDTO updateDTO,
                         HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login-form";
        boardService.update(id, updateDTO, sessionUser.getId());
        return "redirect:/boards/" + id;
    }

    //글 삭제
    @PostMapping("/posts/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login-form";

        boardService.delete(id, sessionUser.getId());
        return "redirect:/boards";
    }

}
