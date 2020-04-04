package com.classManage.tusdt.base.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
@SuppressWarnings("unchecked")
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 6802947397597357809L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    @JSONField(serialize=false)
    private int size;
    //总记录数
    private long total;
    //总页数
    @JSONField(serialize=false)
    private int pages;
    //结果集
    private List<T> data;

    public Pagination() {
    }

    public Pagination(List<T> list) {
        this(list, 5);
    }


    public Pagination(List<T> list, int pageSize) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.data = page;
            this.size = page.size();
            this.total = page.getTotal();
        } else if (list != null) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = this.pageSize > 0 ? 1 : 0;
            this.data = list;
            this.size = list.size();
            this.total = list.size();
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pagination{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
