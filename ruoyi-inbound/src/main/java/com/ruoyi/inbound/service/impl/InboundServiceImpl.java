package com.ruoyi.inbound.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.inbound.mapper.InboundMapper;
import com.ruoyi.inbound.domain.Inbound;
import com.ruoyi.inbound.service.IInboundService;

/**
 * 入库管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class InboundServiceImpl implements IInboundService 
{
    @Autowired
    private InboundMapper inboundMapper;

    /**
     * 查询入库管理
     * 
     * @param inboundId 入库管理主键
     * @return 入库管理
     */
    @Override
    public Inbound selectInboundByInboundId(Long inboundId)
    {
        return inboundMapper.selectInboundByInboundId(inboundId);
    }

    /**
     * 查询入库管理列表
     * 
     * @param inbound 入库管理
     * @return 入库管理
     */
    @Override
    public List<Inbound> selectInboundList(Inbound inbound)
    {
        return inboundMapper.selectInboundList(inbound);
    }

    /**
     * 新增入库管理
     * 
     * @param inbound 入库管理
     * @return 结果
     */
    @Override
    public int insertInbound(Inbound inbound)
    {
        return inboundMapper.insertInbound(inbound);
    }

    /**
     * 修改入库管理
     * 
     * @param inbound 入库管理
     * @return 结果
     */
    @Override
    public int updateInbound(Inbound inbound)
    {
        return inboundMapper.updateInbound(inbound);
    }

    /**
     * 批量删除入库管理
     * 
     * @param inboundIds 需要删除的入库管理主键
     * @return 结果
     */
    @Override
    public int deleteInboundByInboundIds(Long[] inboundIds)
    {
        return inboundMapper.deleteInboundByInboundIds(inboundIds);
    }

    /**
     * 删除入库管理信息
     * 
     * @param inboundId 入库管理主键
     * @return 结果
     */
    @Override
    public int deleteInboundByInboundId(Long inboundId)
    {
        return inboundMapper.deleteInboundByInboundId(inboundId);
    }
}
