package com.ruoyi.base.service;

import java.util.List;
import com.ruoyi.base.domain.Publisher;

/**
 * 出版社信息Service接口
 * 
 * @author ljh
 * @date 2023-08-29
 */
public interface IPublisherService 
{
    /**
     * 查询出版社信息
     * 
     * @param publisherId 出版社信息主键
     * @return 出版社信息
     */
    public Publisher selectPublisherByPublisherId(Long publisherId);

    /**
     * 查询出版社信息列表
     * 
     * @param publisher 出版社信息
     * @return 出版社信息集合
     */
    public List<Publisher> selectPublisherList(Publisher publisher);

    /**
     * 新增出版社信息
     * 
     * @param publisher 出版社信息
     * @return 结果
     */
    public int insertPublisher(Publisher publisher);

    /**
     * 修改出版社信息
     * 
     * @param publisher 出版社信息
     * @return 结果
     */
    public int updatePublisher(Publisher publisher);

    /**
     * 批量删除出版社信息
     * 
     * @param publisherIds 需要删除的出版社信息主键集合
     * @return 结果
     */
    public int deletePublisherByPublisherIds(Long[] publisherIds);

    /**
     * 删除出版社信息信息
     * 
     * @param publisherId 出版社信息主键
     * @return 结果
     */
    public int deletePublisherByPublisherId(Long publisherId);
}
