package com.ruoyi.base.mapper;

import java.util.List;
import com.ruoyi.base.domain.Bookshelf;

/**
 * 书架信息Mapper接口
 * 
 * @author ljh
 * @date 2023-08-29
 */
public interface BookshelfMapper 
{
    /**
     * 查询书架信息
     * 
     * @param bookshelfId 书架信息主键
     * @return 书架信息
     */
    public Bookshelf selectBookshelfByBookshelfId(Long bookshelfId);

    /**
     * 查询书架信息列表
     * 
     * @param bookshelf 书架信息
     * @return 书架信息集合
     */
    public List<Bookshelf> selectBookshelfList(Bookshelf bookshelf);

    /**
     * 新增书架信息
     * 
     * @param bookshelf 书架信息
     * @return 结果
     */
    public int insertBookshelf(Bookshelf bookshelf);

    /**
     * 修改书架信息
     * 
     * @param bookshelf 书架信息
     * @return 结果
     */
    public int updateBookshelf(Bookshelf bookshelf);

    /**
     * 删除书架信息
     * 
     * @param bookshelfId 书架信息主键
     * @return 结果
     */
    public int deleteBookshelfByBookshelfId(Long bookshelfId);

    /**
     * 批量删除书架信息
     * 
     * @param bookshelfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookshelfByBookshelfIds(Long[] bookshelfIds);
}
