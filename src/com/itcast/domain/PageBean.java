package com.itcast.domain;

import java.util.ArrayList;
import java.util.List;

public class PageBean <T> {
    private int currentPage;//当前页码
    private int pageSize=2;//每页显示数据条数
    private int totalPage;//总页数
    private int totalcount;//总数据条数
    private List<T> list=new ArrayList<T>();


    public int gettotalPage(){
        //数据类型转换
        double tc=totalcount;
        //获取总页数
        Double tp= Math.ceil(tc/pageSize);
        //将包装类转化为int类型
        totalPage = tp.intValue();
        return tp.intValue();
    }
    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalcount=" + totalcount +
                ", list=" + list +
                '}';
    }

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageBean(int currentPage, int pageSize, int totalPage, int totalcount, List<T> list) {

        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalcount = totalcount;
        this.list = list;
    }

    public PageBean() {

    }
}
