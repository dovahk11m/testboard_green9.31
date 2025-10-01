package choong.board.members;

import choong.board._core.exceptions.Exception400;
import choong.board._core.exceptions.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //로그인
    public Member login(MemberRequest.LoginDTO loginDTO) {
        Member member = memberRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new Exception400("아이디 또는 비밀번호가 틀립니다."));
        if (!loginDTO.getPassword().equals(member.getPassword())) throw new Exception400("아이디 또는 비밀번호가 틀립니다.");
        return member;
    }//login

    //회원가입
    @Transactional
    public Member join(MemberRequest.JoinDTO joinDTO) {
        if (memberRepository.existsByEmail(joinDTO.getEmail()))
            throw new Exception400("이미 사용 중인 이메일입니다.");
        Member member = joinDTO.toEntity();
        return memberRepository.save(member);
    }

    //이메일중복체크
    public boolean validateEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    //회원정보수정
    public void updatePassword(Long id, MemberRequest.UpdateDTO updateDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다"));
    }
}