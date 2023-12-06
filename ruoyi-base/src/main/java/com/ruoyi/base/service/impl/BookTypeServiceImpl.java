package com.ruoyi.base.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BookTypeMapper;
import com.ruoyi.base.domain.BookType;
import com.ruoyi.base.service.IBookTypeService;

/**
 * 图书类别Service业务层处理
 * 
 * @author ljh
 * @date 2023-08-29
 */
@Service
public class BookTypeServiceImpl implements IBookTypeService 
{
    @Autowired
    private BookTypeMapper bookTypeMapper;

    /**
     * 查询图书类别
     * 
     * @param typeId 图书类别主键
     * @return 图书类别
     */
    @Override
    public BookType selectBookTypeByTypeId(Long typeId)
    {
        return bookTypeMapper.selectBookTypeByTypeId(typeId);
    }

    /**
     * 查询图书类别列表
     * 
     * @param bookType 图书类别
     * @return 图书类别
     */
    @Override
    public List<BookType> selectBookTypeList(BookType bookType)
    {
        return bookTypeMapper.selectBookTypeList(bookType);
    }

    /**
     * 新增图书类别
     * 
     * @param bookType 图书类别
     * @return 结果
     */
    @Override
    public int insertBookType(BookType bookType)
    {
        return bookTypeMapper.insertBookType(bookType);
    }

    /**
     * 修改图书类别
     * 
     * @param bookType 图书类别
     * @return 结果
     */
    @Override
    public int updateBookType(BookType bookType)
    {
        return bookTypeMapper.updateBookType(bookType);
    }

    /**
     * 批量删除图书类别
     * 
     * @param typeIds 需要删除的图书类别主键
     * @return 结果
     */
    @Override
    public int deleteBookTypeByTypeIds(Long[] typeIds)
    {
        return bookTypeMapper.deleteBookTypeByTypeIds(typeIds);
    }

    /**
     * 删除图书类别信息
     * 
     * @param typeId 图书类别主键
     * @return 结果
     */
    @Override
    public int deleteBookTypeByTypeId(Long typeId)
    {
        return bookTypeMapper.deleteBookTypeByTypeId(typeId);
    }
}
