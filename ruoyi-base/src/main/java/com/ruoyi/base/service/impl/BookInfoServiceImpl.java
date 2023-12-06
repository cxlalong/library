package com.ruoyi.base.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BookInfoMapper;
import com.ruoyi.base.domain.BookInfo;
import com.ruoyi.base.service.IBookInfoService;

/**
 * 图书信息Service业务层处理
 * 
 * @author ljh
 * @date 2023-08-29
 */
@Service
public class BookInfoServiceImpl implements IBookInfoService 
{
    @Autowired
    private BookInfoMapper bookInfoMapper;

    /**
     * 查询图书信息
     * 
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    @Override
    public BookInfo selectBookInfoByBookId(Long bookId)
    {
        return bookInfoMapper.selectBookInfoByBookId(bookId);
    }

    /**
     * 查询图书信息列表
     * 
     * @param bookInfo 图书信息
     * @return 图书信息
     */
    @Override
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo)
    {
        return bookInfoMapper.selectBookInfoList(bookInfo);
    }

    /**
     * 新增图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int insertBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.insertBookInfo(bookInfo);
    }

    /**
     * 修改图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int updateBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.updateBookInfo(bookInfo);
    }

    /**
     * 批量删除图书信息
     * 
     * @param bookIds 需要删除的图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByBookIds(Long[] bookIds)
    {
        return bookInfoMapper.deleteBookInfoByBookIds(bookIds);
    }

    /**
     * 删除图书信息信息
     * 
     * @param bookId 图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByBookId(Long bookId)
    {
        return bookInfoMapper.deleteBookInfoByBookId(bookId);
    }
}
