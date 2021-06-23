package com.example.demo.utils;

import com.example.demo.dto.PageDTO;

public class PageUtils {
    public static PageDTO caculatePage(int size, int page, long totalElement) {
        PageDTO pageDTO = new PageDTO();

        boolean isFirst = false;
        boolean isLast = false;
        long totalPage = 0;
        if (totalElement % size == 0) {
            totalPage = totalElement / size;
        } else {
            totalPage = totalElement / size + 1;
        }
        if (page == totalPage) {
            isLast = true;
        }
        if (page == 1) {
            isFirst = true;
        }

        pageDTO.setFirst(isFirst);
        pageDTO.setLast(isLast);
        pageDTO.setSize(size);
        pageDTO.setPage(page);
        pageDTO.setTotalPage(totalPage);
        pageDTO.setTotalElement(totalElement);

        return pageDTO;
    }
}
