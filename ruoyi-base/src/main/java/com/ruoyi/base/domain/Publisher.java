package com.ruoyi.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出版社信息对象 publisher
 * 
 * @author ljh
 * @date 2023-08-29
 */
public class Publisher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出版社编号 */
    private Long publisherId;

    /** 出版名称 */
    @Excel(name = "出版名称")
    private String publisherName;

    /** 出版社地址 */
    @Excel(name = "出版社地址")
    private String publisherAddress;

    public void setPublisherId(Long publisherId) 
    {
        this.publisherId = publisherId;
    }

    public Long getPublisherId() 
    {
        return publisherId;
    }
    public void setPublisherName(String publisherName) 
    {
        this.publisherName = publisherName;
    }

    public String getPublisherName() 
    {
        return publisherName;
    }
    public void setPublisherAddress(String publisherAddress) 
    {
        this.publisherAddress = publisherAddress;
    }

    public String getPublisherAddress() 
    {
        return publisherAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("publisherId", getPublisherId())
            .append("publisherName", getPublisherName())
            .append("publisherAddress", getPublisherAddress())
            .toString();
    }
}
