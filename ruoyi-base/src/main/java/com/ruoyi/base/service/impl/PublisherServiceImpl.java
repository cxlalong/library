package com.ruoyi.base.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.PublisherMapper;
import com.ruoyi.base.domain.Publisher;
import com.ruoyi.base.service.IPublisherService;

/**
 * 出版社信息Service业务层处理
 * 
 * @author ljh
 * @date 2023-08-29
 */
@Service
public class PublisherServiceImpl implements IPublisherService 
{
    @Autowired
    private PublisherMapper publisherMapper;

    /**
     * 查询出版社信息
     * 
     * @param publisherId 出版社信息主键
     * @return 出版社信息
     */
    @Override
    public Publisher selectPublisherByPublisherId(Long publisherId)
    {
        return publisherMapper.selectPublisherByPublisherId(publisherId);
    }

    /**
     * 查询出版社信息列表
     * 
     * @param publisher 出版社信息
     * @return 出版社信息
     */
    @Override
    public List<Publisher> selectPublisherList(Publisher publisher)
    {
        return publisherMapper.selectPublisherList(publisher);
    }

    /**
     * 新增出版社信息
     * 
     * @param publisher 出版社信息
     * @return 结果
     */
    @Override
    public int insertPublisher(Publisher publisher)
    {
        return publisherMapper.insertPublisher(publisher);
    }

    /**
     * 修改出版社信息
     * 
     * @param publisher 出版社信息
     * @return 结果
     */
    @Override
    public int updatePublisher(Publisher publisher)
    {
        return publisherMapper.updatePublisher(publisher);
    }

    /**
     * 批量删除出版社信息
     * 
     * @param publisherIds 需要删除的出版社信息主键
     * @return 结果
     */
    @Override
    public int deletePublisherByPublisherIds(Long[] publisherIds)
    {
        return publisherMapper.deletePublisherByPublisherIds(publisherIds);
    }

    /**
     * 删除出版社信息信息
     * 
     * @param publisherId 出版社信息主键
     * @return 结果
     */
    @Override
    public int deletePublisherByPublisherId(Long publisherId)
    {
        return publisherMapper.deletePublisherByPublisherId(publisherId);
    }
}
