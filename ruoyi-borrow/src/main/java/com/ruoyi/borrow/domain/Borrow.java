package com.ruoyi.borrow.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 借阅信息对象 borrow
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class Borrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借书id */
    private Long borrowId;

    /** 读者编号 */
    @Excel(name = "读者编号")
    private Long readerNo;

    /** 书籍编号 */
    @Excel(name = "书籍编号")
    private Long inboundId;

    /** 借书时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借书时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date borrowTime;

    /**  归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = " 归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 是否归还 */
    @Excel(name = "是否归还")
    private String borrowStatu;

    public void setBorrowId(Long borrowId) 
    {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() 
    {
        return borrowId;
    }
    public void setReaderNo(Long readerNo) 
    {
        this.readerNo = readerNo;
    }

    public Long getReaderNo() 
    {
        return readerNo;
    }
    public void setInboundId(Long inboundId) 
    {
        this.inboundId = inboundId;
    }

    public Long getInboundId() 
    {
        return inboundId;
    }
    public void setBorrowTime(Date borrowTime) 
    {
        this.borrowTime = borrowTime;
    }

    public Date getBorrowTime() 
    {
        return borrowTime;
    }
    public void setReturnTime(Date returnTime) 
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() 
    {
        return returnTime;
    }
    public void setBorrowStatu(String borrowStatu) 
    {
        this.borrowStatu = borrowStatu;
    }

    public String getBorrowStatu() 
    {
        return borrowStatu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("borrowId", getBorrowId())
            .append("readerNo", getReaderNo())
            .append("inboundId", getInboundId())
            .append("borrowTime", getBorrowTime())
            .append("returnTime", getReturnTime())
            .append("borrowStatu", getBorrowStatu())
            .toString();
    }
}
