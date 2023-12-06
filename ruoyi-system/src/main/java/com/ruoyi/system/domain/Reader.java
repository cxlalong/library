package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 读者信息对象 reader
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class Reader extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 读者编号 */
    private Long readerId;

    /** 读者证号 */
    @Excel(name = "读者证号")
    private Long readerNo;

    /** 读者名称 */
    @Excel(name = "读者名称")
    private String readerName;

    /** 读者电话 */
    @Excel(name = "读者电话")
    private String readerPhone;

    /** 读者邮箱 */
    @Excel(name = "读者邮箱")
    private String readerEmail;

    /** 读者身份 */
    @Excel(name = "读者身份")
    private String identity;

    public void setReaderId(Long readerId) 
    {
        this.readerId = readerId;
    }

    public Long getReaderId() 
    {
        return readerId;
    }
    public void setReaderNo(Long readerNo) 
    {
        this.readerNo = readerNo;
    }

    public Long getReaderNo() 
    {
        return readerNo;
    }
    public void setReaderName(String readerName) 
    {
        this.readerName = readerName;
    }

    public String getReaderName() 
    {
        return readerName;
    }
    public void setReaderPhone(String readerPhone) 
    {
        this.readerPhone = readerPhone;
    }

    public String getReaderPhone() 
    {
        return readerPhone;
    }
    public void setReaderEmail(String readerEmail) 
    {
        this.readerEmail = readerEmail;
    }

    public String getReaderEmail() 
    {
        return readerEmail;
    }
    public void setIdentity(String identity) 
    {
        this.identity = identity;
    }

    public String getIdentity() 
    {
        return identity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("readerId", getReaderId())
            .append("readerNo", getReaderNo())
            .append("readerName", getReaderName())
            .append("readerPhone", getReaderPhone())
            .append("readerEmail", getReaderEmail())
            .append("identity", getIdentity())
            .toString();
    }
}
