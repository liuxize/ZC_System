package com.system.po;

import java.util.Date;

/**
 *  分页信息 pojo类
 */
public class PagingVO {

    //当前页码,默认第一页
    private int curentPageNo = 1;
    //总页数
    private int totalCount;
    //页面容量
    private int pageSize=15;  //15
    //上一页
    private int upPageNo;
    //下一页
    private int nextPageNo;
    //要前往的页码,默认0
    private int toPageNo = 0;
    //用于存储整形变量
    private Integer intergertemp;
    private Integer intergertwo;
    private Integer intergerthree;
    //用于存储查询的开始日期；
    private Date startdate;
    //用于存储查询的结束日期
    private Date enddate;

    //用于存储string类型的变量
    private String stringtemp;
    private String stringtemp2;

    public int getToPageNo() {
        return toPageNo;
    }

    public void setToPageNo(int toPageNo) {
        this.toPageNo = toPageNo;
    }

    public Integer getIntergertwo() {
        return intergertwo;
    }

    public void setIntergertwo(Integer intergertwo) {
        this.intergertwo = intergertwo;
    }

    public Integer getIntergerthree() {
        return intergerthree;
    }

    public void setIntergerthree(Integer intergerthree) {
        this.intergerthree = intergerthree;
    }

    public String getStringtemp() {
        return stringtemp;
    }

    public void setStringtemp(String stringtemp) {
        this.stringtemp = stringtemp;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getIntergertemp() {
        return intergertemp;
    }

    public void setIntergertemp(Integer intergertemp) {
        this.intergertemp = intergertemp;
    }

    public String getStringtemp2() {
        return stringtemp2;
    }

    public void setStringtemp2(String stringtemp2) {
        this.stringtemp2 = stringtemp2;
    }

    public void setToPageNo(Integer toPageNo) {
        //新一页
        this.toPageNo = (toPageNo-1) * pageSize ;
        //设置跳转后当前的页码
        setCurentPageNo(toPageNo);
    }

    public Integer getTopageNo() {
        return toPageNo;
    }

    public int getCurentPageNo() {
        return curentPageNo;
    }

    //设置当前页码
    public void setCurentPageNo(int curentPageNo) {
        if (curentPageNo != 1) {
            this.upPageNo = curentPageNo - 1;
        }
        this.nextPageNo = curentPageNo + 1;

        this.curentPageNo = curentPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount%pageSize > 0) {
            this.totalCount = (totalCount/pageSize)+1;
        } else {
            this.totalCount = totalCount/pageSize;
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getUpPageNo() {
        return upPageNo;
    }

    public void setUpPageNo(int upPageNo) {
        this.upPageNo = upPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }
}
