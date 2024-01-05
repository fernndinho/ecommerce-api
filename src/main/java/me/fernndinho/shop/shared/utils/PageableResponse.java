package me.fernndinho.shop.shared.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Data

public class PageableResponse<T> {
    private List<T> content;

    private boolean hasNext;
    private boolean hasPrev;
    private int currentPage;
    private int pagesSize;
    private int numberOfPages;

    public static <T> PageableResponse<T> of(Page<T> page) {
        return new PageableResponse<>(page.toList(), page.hasNext(), page.hasPrevious(),
                page.getNumber(), page.getSize(),
                page.getNumberOfElements()
        );
    }
}
