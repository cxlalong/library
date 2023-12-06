package com.ruoyi.inbound.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库管理对象 inbound
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class Inbound extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 入库编号 */
    private Long inboundId;

    /** 图书名 */
    @Excel(name = "图书名")
    private Long bookId;

    /** 入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inboundTime;

    /** 经手人 */
    @Excel(name = "经手人")
    private String inboundHandler;

    /** 在库状态 */
    @Excel(name = "在库状态")
    private String inboundBookStatu;

    /** 上架状态 */
    @Excel(name = "上架状态")
    private String inboundStatu;

    /** 书架状态 */
    @Excel(name = "书架状态")
    private Long bookshelfId;

    /** 备注 */
    @Excel(name = "备注")
    private String inboundRemarks;

    public void setInboundId(Long inboundId) 
    {
        this.inboundId = inboundId;
    }

    public Long getInboundId() 
    {
        return inboundId;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setInboundTime(Date inboundTime) 
    {
        this.inboundTime = inboundTime;
    }

    public Date getInboundTime() 
    {
        return inboundTime;
    }
    public void setInboundHandler(String inboundHandler) 
    {
        this.inboundHandler = inboundHandler;
    }

    public String getInboundHandler() 
    {
        return inboundHandler;
    }
    public void setInboundBookStatu(String inboundBookStatu) 
    {
        this.inboundBookStatu = inboundBookStatu;
    }

    public String getInboundBookStatu() 
    {
        return inboundBookStatu;
    }
    public void setInboundStatu(String inboundStatu) 
    {
        this.inboundStatu = inboundStatu;
    }

    public String getInboundStatu() 
    {
        return inboundStatu;
    }
    public void setBookshelfId(Long bookshelfId) 
    {
        this.bookshelfId = bookshelfId;
    }

    public Long getBookshelfId() 
    {
        return bookshelfId;
    }
    public void setInboundRemarks(String inboundRemarks) 
    {
        this.inboundRemarks = inboundRemarks;
    }

    public String getInboundRemarks() 
    {
        return inboundRemarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inboundId", getInboundId())
            .append("bookId", getBookId())
            .append("inboundTime", getInboundTime())
            .append("inboundHandler", getInboundHandler())
            .append("inboundBookStatu", getInboundBookStatu())
            .append("inboundStatu", getInboundStatu())
            .append("bookshelfId", getBookshelfId())
            .append("inboundRemarks", getInboundRemarks())
            .toString();
    }
}
