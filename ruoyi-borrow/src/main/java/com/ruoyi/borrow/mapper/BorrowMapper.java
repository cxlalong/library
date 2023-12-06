package com.ruoyi.borrow.mapper;

import java.util.List;
import com.ruoyi.borrow.domain.Borrow;

/**
 * 借阅信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface BorrowMapper 
{
    /**
     * 查询借阅信息
     * 
     * @param borrowId 借阅信息主键
     * @return 借阅信息
     */
    public Borrow selectBorrowByBorrowId(Long borrowId);

    /**
     * 查询借阅信息列表
     * 
     * @param borrow 借阅信息
     * @return 借阅信息集合
     */
    public List<Borrow> selectBorrowList(Borrow borrow);

    /**
     * 新增借阅信息
     * 
     * @param borrow 借阅信息
     * @return 结果
     */
    public int insertBorrow(Borrow borrow);

    /**
     * 修改借阅信息
     * 
     * @param borrow 借阅信息
     * @return 结果
     */
    public int updateBorrow(Borrow borrow);

    /**
     * 删除借阅信息
     * 
     * @param borrowId 借阅信息主键
     * @return 结果
     */
    public int deleteBorrowByBorrowId(Long borrowId);

    /**
     * 批量删除借阅信息
     * 
     * @param borrowIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBorrowByBorrowIds(Long[] borrowIds);
}
