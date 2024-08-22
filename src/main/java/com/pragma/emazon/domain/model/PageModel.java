package com.pragma.emazon.domain.model;

import java.util.List;

public class PageModel<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    private int numberOfElements;
    private int totalOfPages;
    private boolean first;
    private boolean last;
    private boolean empty;
    private boolean hasNext;
    private boolean hasPrevious;
    private String sort;

    private PageModel(Builder<T> builder) {
        this.content = builder.content;
        this.totalPages = builder.totalPages;
        this.totalElements = builder.totalElements;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.numberOfElements = builder.numberOfElements;
        this.totalOfPages = builder.totalOfPages;
        this.first = builder.first;
        this.last = builder.last;
        this.empty = builder.empty;
        this.hasNext = builder.hasNext;
        this.hasPrevious = builder.hasPrevious;
        this.sort = builder.sort;
    }

    public static class Builder<T> {
        private List<T> content;
        private int totalPages;
        private long totalElements;
        private int pageNumber;
        private int pageSize;
        private int numberOfElements;
        private int totalOfPages;
        private boolean first;
        private boolean last;
        private boolean empty;
        private boolean hasNext;
        private boolean hasPrevious;
        private String sort;

        public Builder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder<T> totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> numberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public Builder<T> totalOfPages(int totalOfPages) {
            this.totalOfPages = totalOfPages;
            return this;
        }

        public Builder<T> first(boolean first) {
            this.first = first;
            return this;
        }

        public Builder<T> last(boolean last) {
            this.last = last;
            return this;
        }

        public Builder<T> empty(boolean empty) {
            this.empty = empty;
            return this;
        }

        public Builder<T> hasNext(boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public Builder<T> hasPrevious(boolean hasPrevious) {
            this.hasPrevious = hasPrevious;
            return this;
        }

        public Builder<T> sort(String sort) {
            this.sort = sort;
            return this;
        }

        public PageModel<T> build() {
            return new PageModel<>(this);
        }
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getTotalOfPages() {
        return totalOfPages;
    }

    public void setTotalOfPages(int totalOfPages) {
        this.totalOfPages = totalOfPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
