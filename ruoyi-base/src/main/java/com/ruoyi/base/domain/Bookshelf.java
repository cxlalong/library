package com.ruoyi.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 书架信息对象 bookshelf
 * 
 * @author ljh
 * @date 2023-08-29
 */
public class Bookshelf extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书架编号 */
    private Long bookshelfId;

    /** 书架类别 */
    @Excel(name = "书架类别")
    private String bookshelfType;

    /** 书架地址 */
    @Excel(name = "书架地址")
    private String bookshelfAddress;

    /** 书架容量 */
    @Excel(name = "书架容量")
    private Long bookshelfCapacity;

    /** 报警容量 */
    @Excel(name = "报警容量")
    private Long bookshelfCordon;

    public void setBookshelfId(Long bookshelfId) 
    {
        this.bookshelfId = bookshelfId;
    }

    public Long getBookshelfId() 
    {
        return bookshelfId;
    }
    public void setBookshelfType(String bookshelfType) 
    {
        this.bookshelfType = bookshelfType;
    }

    public String getBookshelfType() 
    {
        return bookshelfType;
    }
    public void setBookshelfAddress(String bookshelfAddress) 
    {
        this.bookshelfAddress = bookshelfAddress;
    }

    public String getBookshelfAddress() 
    {
        return bookshelfAddress;
    }
    public void setBookshelfCapacity(Long bookshelfCapacity) 
    {
        this.bookshelfCapacity = bookshelfCapacity;
    }

    public Long getBookshelfCapacity() 
    {
        return bookshelfCapacity;
    }
    public void setBookshelfCordon(Long bookshelfCordon) 
    {
        this.bookshelfCordon = bookshelfCordon;
    }

    public Long getBookshelfCordon() 
    {
        return bookshelfCordon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookshelfId", getBookshelfId())
            .append("bookshelfType", getBookshelfType())
            .append("bookshelfAddress", getBookshelfAddress())
            .append("bookshelfCapacity", getBookshelfCapacity())
            .append("bookshelfCordon", getBookshelfCordon())
            .toString();
    }
}
