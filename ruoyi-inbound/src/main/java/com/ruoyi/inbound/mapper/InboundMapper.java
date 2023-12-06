package com.ruoyi.inbound.mapper;

import java.util.List;
import com.ruoyi.inbound.domain.Inbound;

/**
 * 入库管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface InboundMapper 
{
    /**
     * 查询入库管理
     * 
     * @param inboundId 入库管理主键
     * @return 入库管理
     */
    public Inbound selectInboundByInboundId(Long inboundId);

    /**
     * 查询入库管理列表
     * 
     * @param inbound 入库管理
     * @return 入库管理集合
     */
    public List<Inbound> selectInboundList(Inbound inbound);

    /**
     * 新增入库管理
     * 
     * @param inbound 入库管理
     * @return 结果
     */
    public int insertInbound(Inbound inbound);

    /**
     * 修改入库管理
     * 
     * @param inbound 入库管理
     * @return 结果
     */
    public int updateInbound(Inbound inbound);

    /**
     * 删除入库管理
     * 
     * @param inboundId 入库管理主键
     * @return 结果
     */
    public int deleteInboundByInboundId(Long inboundId);

    /**
     * 批量删除入库管理
     * 
     * @param inboundIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInboundByInboundIds(Long[] inboundIds);
}
