package com.ruoyi.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书类别对象 book_type
 * 
 * @author ljh
 * @date 2023-08-29
 */
public class BookType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别编号 */
    private Long typeId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date typeCreatetime;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setTypeCreatetime(Date typeCreatetime) 
    {
        this.typeCreatetime = typeCreatetime;
    }

    public Date getTypeCreatetime() 
    {
        return typeCreatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("typeCreatetime", getTypeCreatetime())
            .toString();
    }
}
