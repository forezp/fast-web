package io.github.forezp.fastwebcommon.dto;

import java.util.ArrayList;
import java.util.List;

public class PageResultsDTO<T> {
    public List<T> list = new ArrayList<>();
    public int page;
    public int pageSize;
    public long totalCount;
    public int offset;

    public int totalPage;


    public PageResultsDTO() {
    }

    public PageResultsDTO(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.offset = (page - 1) * pageSize;
    }



    public int getTotalPage(int listSize) {
        int totalPage = listSize / this.pageSize;
        if (totalPage == 0 || totalCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }


    /**
     * 对传入的list 进行分页 by cjn 2017/08/10
     *
     * @param page
     * @param pageSize
     * @param list
     */
    public PageResultsDTO(int page, int pageSize, List<T> list) {
        this(page, pageSize);
        if (null != list && list.size() > 0) {
            int listSize = list.size();
            this.totalCount = listSize;

            if (pageSize > listSize) {
                this.pageSize = listSize;
                this.list = list;
            }

            adjustPageNo(listSize);

            int endIndex = this.page * this.pageSize;
            if (endIndex > list.size()) {
                endIndex = list.size();
            }
            this.list = list.subList(this.offset, endIndex);
        } else {
            this.totalCount = 0;
        }
    }


    public void adjustPageNo(int listSize) {
        if (page == 1) {
            return;
        }
        this.totalPage = getTotalPage(listSize);
        if (page > totalPage) {
            page = totalPage;
            this.offset = (page - 1) * pageSize;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        adjustPageNo((int) totalCount);
    }

    public void setTotalPage(int totalSize, int pageSize) {
        this.totalPage = (int) Math.ceil(((double) totalSize) / pageSize);
    }




}
