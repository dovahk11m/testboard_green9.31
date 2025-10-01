package choong.board._core;

import choong.board.members.Member;
import lombok.Value;

@Value
public class SessionUser {

    Long id;
    String name;
    String email;
    String role;

    public static SessionUser fromMember(Member member) {
        return new SessionUser(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getRole().toString());
    }
}


