package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReaderMapper;
import com.ruoyi.system.domain.Reader;
import com.ruoyi.system.service.IReaderService;

/**
 * 读者信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class ReaderServiceImpl implements IReaderService 
{
    @Autowired
    private ReaderMapper readerMapper;

    /**
     * 查询读者信息
     * 
     * @param readerId 读者信息主键
     * @return 读者信息
     */
    @Override
    public Reader selectReaderByReaderId(Long readerId)
    {
        return readerMapper.selectReaderByReaderId(readerId);
    }

    /**
     * 查询读者信息列表
     * 
     * @param reader 读者信息
     * @return 读者信息
     */
    @Override
    public List<Reader> selectReaderList(Reader reader)
    {
        return readerMapper.selectReaderList(reader);
    }

    /**
     * 新增读者信息
     * 
     * @param reader 读者信息
     * @return 结果
     */
    @Override
    public int insertReader(Reader reader)
    {
        return readerMapper.insertReader(reader);
    }

    /**
     * 修改读者信息
     * 
     * @param reader 读者信息
     * @return 结果
     */
    @Override
    public int updateReader(Reader reader)
    {
        return readerMapper.updateReader(reader);
    }

    /**
     * 批量删除读者信息
     * 
     * @param readerIds 需要删除的读者信息主键
     * @return 结果
     */
    @Override
    public int deleteReaderByReaderIds(Long[] readerIds)
    {
        return readerMapper.deleteReaderByReaderIds(readerIds);
    }

    /**
     * 删除读者信息信息
     * 
     * @param readerId 读者信息主键
     * @return 结果
     */
    @Override
    public int deleteReaderByReaderId(Long readerId)
    {
        return readerMapper.deleteReaderByReaderId(readerId);
    }
}
