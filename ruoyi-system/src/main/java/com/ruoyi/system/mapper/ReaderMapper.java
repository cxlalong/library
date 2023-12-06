package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Reader;

/**
 * 读者信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface ReaderMapper 
{
    /**
     * 查询读者信息
     * 
     * @param readerId 读者信息主键
     * @return 读者信息
     */
    public Reader selectReaderByReaderId(Long readerId);

    /**
     * 查询读者信息列表
     * 
     * @param reader 读者信息
     * @return 读者信息集合
     */
    public List<Reader> selectReaderList(Reader reader);

    /**
     * 新增读者信息
     * 
     * @param reader 读者信息
     * @return 结果
     */
    public int insertReader(Reader reader);

    /**
     * 修改读者信息
     * 
     * @param reader 读者信息
     * @return 结果
     */
    public int updateReader(Reader reader);

    /**
     * 删除读者信息
     * 
     * @param readerId 读者信息主键
     * @return 结果
     */
    public int deleteReaderByReaderId(Long readerId);

    /**
     * 批量删除读者信息
     * 
     * @param readerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReaderByReaderIds(Long[] readerIds);
}
