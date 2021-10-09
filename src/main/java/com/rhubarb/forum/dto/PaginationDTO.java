package com.rhubarb.forum.dto;

import com.rhubarb.forum.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sunxun
 * @date: 10/9/21 12:47 AM
 * @description: 问题分页。方便前端调用的 DTO
 */

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private Boolean showFirst = true;
    private Boolean showLast = true;
    private Boolean showPrev = true;
    private Boolean showNext = true;
    private Integer pageNo;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setAll(Integer count, Integer pageNo, Integer size) {

        this.totalPage = count / size;
        if (count % size != 0) {
            totalPage ++;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        this.pageNo = pageNo;
        pages.add(pageNo);
        for (int i = 1; i <= 3; i++) {
            if (pageNo - i > 0) {
                pages.add(0, pageNo - i);
            }
            if (pageNo + i <= totalPage) {
                pages.add(pageNo + i);
            }
        }

        if (pageNo == 1) {
            showPrev = false;
        }
        if (pageNo == totalPage) {
            showNext = false;
        }
        if (pages.contains(1)) {
            showFirst = false;
        }
        if (pages.contains(totalPage)) {
            showLast = false;
        }


    }
}
