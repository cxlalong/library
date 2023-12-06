package com.ruoyi.base.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BookshelfMapper;
import com.ruoyi.base.domain.Bookshelf;
import com.ruoyi.base.service.IBookshelfService;

/**
 * 书架信息Service业务层处理
 * 
 * @author ljh
 * @date 2023-08-29
 */
@Service
public class BookshelfServiceImpl implements IBookshelfService 
{
    @Autowired
    private BookshelfMapper bookshelfMapper;

    /**
     * 查询书架信息
     * 
     * @param bookshelfId 书架信息主键
     * @return 书架信息
     */
    @Override
    public Bookshelf selectBookshelfByBookshelfId(Long bookshelfId)
    {
        return bookshelfMapper.selectBookshelfByBookshelfId(bookshelfId);
    }

    /**
     * 查询书架信息列表
     * 
     * @param bookshelf 书架信息
     * @return 书架信息
     */
    @Override
    public List<Bookshelf> selectBookshelfList(Bookshelf bookshelf)
    {
        return bookshelfMapper.selectBookshelfList(bookshelf);
    }

    /**
     * 新增书架信息
     * 
     * @param bookshelf 书架信息
     * @return 结果
     */
    @Override
    public int insertBookshelf(Bookshelf bookshelf)
    {
        return bookshelfMapper.insertBookshelf(bookshelf);
    }

    /**
     * 修改书架信息
     * 
     * @param bookshelf 书架信息
     * @return 结果
     */
    @Override
    public int updateBookshelf(Bookshelf bookshelf)
    {
        return bookshelfMapper.updateBookshelf(bookshelf);
    }

    /**
     * 批量删除书架信息
     * 
     * @param bookshelfIds 需要删除的书架信息主键
     * @return 结果
     */
    @Override
    public int deleteBookshelfByBookshelfIds(Long[] bookshelfIds)
    {
        return bookshelfMapper.deleteBookshelfByBookshelfIds(bookshelfIds);
    }

    /**
     * 删除书架信息信息
     * 
     * @param bookshelfId 书架信息主键
     * @return 结果
     */
    @Override
    public int deleteBookshelfByBookshelfId(Long bookshelfId)
    {
        return bookshelfMapper.deleteBookshelfByBookshelfId(bookshelfId);
    }
}
