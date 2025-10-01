package choong.board.boards;

import choong.board._core.DateUtil;
import choong.board.members.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Transient
    private boolean isAuthor;

    @Transient
    private String formattedCreatedAt;

    //시간 포맷
    public String getFormattedCreatedAt() {
        return DateUtil.format(createdAt);
    }

    //소유권 확인
    public boolean isOwner(Long id) {
        return this.member != null && this.member.getId().equals(id);
    }

    //수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
