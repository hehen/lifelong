package com.cit.lifelong.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaginationDTO
 * @Description: TODO
 * @Author hehen
 * @Date 2020/3/14
 * @Version V1.0
 **/
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private int total;
    private int page;
    private int totalPage;
    private boolean isHasPre;
    private boolean isHasNext;
    private boolean isShowBegin;
    private boolean isShowEnd;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(int total,int page,int size){
        this.total = total;
        if (size <= 0) {
        size=10;
        }
        if(total%size==0){
            this.totalPage = total/size;
        }else {
            this.totalPage = total/size+1;
        }
        if(page<1){
            this.page =1;
        }else if(page>totalPage){
            this.page =totalPage;
        }else {
            this.page = page;
        }
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if(this.page-i>0){
                pages.add(0,this.page-i);
            }
            if(this.page+i<=this.totalPage){
                pages.add(this.page+i);
            }
        }

        if(this.page>1){
            isHasPre = true;
        }else {
            isHasPre = false;
        }
        if(this.page<totalPage){
            isHasNext = true;
        }else {
            isHasNext = false;
        }
        if(pages.contains(1)){
            isShowBegin = false;
        }else {
            isShowBegin = true;
        }
        if(pages.contains(totalPage)){
            isShowEnd = false;
        }else {
            isShowEnd = true;
        }
    }


    public List<QuestionDTO> getQuestionDTOList() {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHasPre() {
        return isHasPre;
    }

    public void setHasPre(boolean hasPre) {
        isHasPre = hasPre;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isShowBegin() {
        return isShowBegin;
    }

    public void setShowBegin(boolean showBegin) {
        isShowBegin = showBegin;
    }

    public boolean isShowEnd() {
        return isShowEnd;
    }

    public void setShowEnd(boolean showEnd) {
        isShowEnd = showEnd;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }
}
