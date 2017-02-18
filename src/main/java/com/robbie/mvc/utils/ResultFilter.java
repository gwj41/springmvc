package com.robbie.mvc.utils;

import java.io.Serializable;
import java.util.List;

public class ResultFilter<T> implements Serializable{
    public ResultFilter(){

    }
    private final static int DEFAULT_NAVIGATOR_SIZE = 5;
    // current page no
    private int currentPage = 1;
    // page size
    private int pageSize = 5;

    private String sort = "age";

    // total count no
    private int totalCount;

    private boolean hasNextPage;

    private boolean hasPrePage;

    private int totalPages;

    private int navigatorSize;

    private List<T> items;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public int getNavigatorSize() {
        return navigatorSize;
    }

    public void setNavigatorSize(int navigatorSize) {
        this.navigatorSize = navigatorSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages - 1 >= 0 ? totalPages - 1 : 0;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
