package choong.board.boards;

import lombok.Getter;
import lombok.Value;

public class BoardResponse {

    @Value
    public static class ListDTO {
        Long id;
        String title;
        String author;
        String createdAt;

        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.author = board.getMember().getName();
            this.createdAt = board.getFormattedCreatedAt();
        }

    }//ListDTO

    @Value
    public static class DetailDTO {
        Long id;
        String title;
        String content;
        String author;
        String createdAt;
        boolean isOwner; // 수정, 삭제 버튼 노출 여부

        public DetailDTO(Board board, Long sessionUserId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.author = board.getMember().getName();
            this.createdAt = board.getFormattedCreatedAt();
            this.isOwner = board.isOwner(sessionUserId);
        }

    }//DetailDTO

    @Value
    public static class UpdateDTO {
         Long id;
         String title;
         String content;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }
}
