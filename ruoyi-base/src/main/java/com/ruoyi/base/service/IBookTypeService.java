package com.ruoyi.base.service;

import java.util.List;
import com.ruoyi.base.domain.BookType;

/**
 * 图书类别Service接口
 * 
 * @author ljh
 * @date 2023-08-29
 */
public interface IBookTypeService 
{
    /**
     * 查询图书类别
     * 
     * @param typeId 图书类别主键
     * @return 图书类别
     */
    public BookType selectBookTypeByTypeId(Long typeId);

    /**
     * 查询图书类别列表
     * 
     * @param bookType 图书类别
     * @return 图书类别集合
     */
    public List<BookType> selectBookTypeList(BookType bookType);

    /**
     * 新增图书类别
     * 
     * @param bookType 图书类别
     * @return 结果
     */
    public int insertBookType(BookType bookType);

    /**
     * 修改图书类别
     * 
     * @param bookType 图书类别
     * @return 结果
     */
    public int updateBookType(BookType bookType);

    /**
     * 批量删除图书类别
     * 
     * @param typeIds 需要删除的图书类别主键集合
     * @return 结果
     */
    public int deleteBookTypeByTypeIds(Long[] typeIds);

    /**
     * 删除图书类别信息
     * 
     * @param typeId 图书类别主键
     * @return 结果
     */
    public int deleteBookTypeByTypeId(Long typeId);
}
