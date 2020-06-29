package com.cloudmusic.entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {
    private int pageNum;
    private int pageSize;
    private int pages;
    private List<Object> list;

    public PageInfo(int pageNum, int pageSize, List<?> objects){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        double dbPages = Math.ceil( (double)objects.size() / (double)pageSize );
        this.pages = (int)dbPages;
        this.list = new ArrayList<>();
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize;
        for (int i = start; i < end; i++){
            if (i < objects.size()){
                this.list.add(objects.get(i));
            }
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}
