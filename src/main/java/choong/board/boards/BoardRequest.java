package choong.board.boards;

import choong.board.members.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class BoardRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        @NotEmpty(message = "제목을 입력해주세요")
        @Size(max = 100, message = "제목은 100자 이내로 입력해주세요")
        private String title;
        @NotEmpty(message = "내용을 입력해주세요.")
        @Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
        private String content;
        public Board toEntity(Member member) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .member(member)
                    .build();
        }
    }//SaveDTO
    @Getter
    @Setter
    public static class UpdateDTO {
        @NotEmpty(message = "제목을 입력해주세요.")
        @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
        private String title;

        @NotEmpty(message = "내용을 입력해주세요.")
        @Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
        private String content;
    }//UpdateDTO

}//end of MemberRequest
