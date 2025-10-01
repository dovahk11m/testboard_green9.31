package choong.board._core.paging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageNumberDto {

    private final int number;
    private final int display;
    private final boolean isCurrent;

    public PageNumberDto(int number, int currentPage) {
        this.number = number;
        this.display = number + 1;
        this.isCurrent = (number == currentPage);
    }

    public static PageNavigation createNavigation(Page<?> page) {
        int totalPages = page.getTotalPages();
        if (totalPages == 0) { // 페이지가 0개인 경우 예외 처리
            return new PageNavigation(new ArrayList<>(), false, false, 0, 0);
        }
        int currentPage = page.getNumber();
        int displayRange = 5;

        //중앙정렬, 범위조정
        int startPage = Math.max(0, currentPage - displayRange / 2);
        int endPage = Math.min(startPage + displayRange - 1, totalPages - 1);
        if (endPage - startPage < displayRange - 1) {
            startPage = Math.max(0, endPage - displayRange + 1);
        }

        List<PageNumberDto> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(new PageNumberDto(i, currentPage));
        }

        boolean hasPrev = currentPage > 0;
        boolean hasNext = currentPage < totalPages - 1;
        int prevPage = hasPrev ? currentPage - 1 : 0;
        int nextPage = hasNext ? currentPage + 1 : totalPages - 1;

        return new PageNavigation(pageNumbers, hasPrev, hasNext, prevPage, nextPage);
    }

    @Getter
    @RequiredArgsConstructor
    public static class PageNavigation {
        private final List<PageNumberDto> pageNumbers;
        private final boolean hasPrev;
        private final boolean hasNext;
        private final int prevPage;
        private final int nextPage;
    }

}

