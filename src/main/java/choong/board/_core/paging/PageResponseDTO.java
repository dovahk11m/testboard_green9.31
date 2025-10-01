package choong.board._core.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {

    private List<T> content;
    private int page;
    private int size;
    private int totalPage;
    private long totalElements;
    private boolean last;

    private PageNumberDto.PageNavigation navigation;

    public static <T, E> PageResponseDTO<T> from(Page<E> page, Function<E, T> converter) {
        List<T> dtoList = page.getContent().stream()
                .map(converter)
                .collect(Collectors.toList());

        PageNumberDto.PageNavigation navigation = PageNumberDto.createNavigation(page);

        return new PageResponseDTO<>(
                dtoList,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isLast(),
                navigation
        );
    }
}
