package choong.board.members;

import choong.board._core.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 폼 호출
    @GetMapping("/members/login-form")
    public String loginForm() {

        return "mmembers/login-form";
    }

    //회원가입 폼 호출
    @GetMapping("/members/join-form")
    public String joinForm() {

        return "mmembers/join-form";
    }

    //로그인
    @PostMapping("/members/login")
    public String login(@Valid MemberRequest.LoginDTO loginDTO, HttpSession session) {
        Member member = memberService.login(loginDTO);
        SessionUser sessionUser = SessionUser.fromMember(member);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/boards";
    }//

    //로그아웃
    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/boards";
    }

    //회원가입
    @PostMapping("/members/join")
    public String join(@Valid MemberRequest.JoinDTO joinDTO, HttpSession session) {
        Member sessionUser = memberService.join(joinDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/boards";
    }

}
