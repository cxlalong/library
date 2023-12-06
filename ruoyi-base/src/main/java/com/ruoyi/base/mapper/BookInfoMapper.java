package com.ruoyi.base.mapper;

import java.util.List;
import com.ruoyi.base.domain.BookInfo;

/**
 * 图书信息Mapper接口
 * 
 * @author ljh
 * @date 2023-08-29
 */
public interface BookInfoMapper 
{
    /**
     * 查询图书信息
     * 
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    public BookInfo selectBookInfoByBookId(Long bookId);

    /**
     * 查询图书信息列表
     * 
     * @param bookInfo 图书信息
     * @return 图书信息集合
     */
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo);

    /**
     * 新增图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int insertBookInfo(BookInfo bookInfo);

    /**
     * 修改图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int updateBookInfo(BookInfo bookInfo);

    /**
     * 删除图书信息
     * 
     * @param bookId 图书信息主键
     * @return 结果
     */
    public int deleteBookInfoByBookId(Long bookId);

    /**
     * 批量删除图书信息
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookInfoByBookIds(Long[] bookIds);
}
