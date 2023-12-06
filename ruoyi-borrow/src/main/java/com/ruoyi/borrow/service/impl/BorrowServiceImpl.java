package com.ruoyi.borrow.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.borrow.mapper.BorrowMapper;
import com.ruoyi.borrow.domain.Borrow;
import com.ruoyi.borrow.service.IBorrowService;

/**
 * 借阅信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class BorrowServiceImpl implements IBorrowService 
{
    @Autowired
    private BorrowMapper borrowMapper;

    /**
     * 查询借阅信息
     * 
     * @param borrowId 借阅信息主键
     * @return 借阅信息
     */
    @Override
    public Borrow selectBorrowByBorrowId(Long borrowId)
    {
        return borrowMapper.selectBorrowByBorrowId(borrowId);
    }

    /**
     * 查询借阅信息列表
     * 
     * @param borrow 借阅信息
     * @return 借阅信息
     */
    @Override
    public List<Borrow> selectBorrowList(Borrow borrow)
    {
        return borrowMapper.selectBorrowList(borrow);
    }

    /**
     * 新增借阅信息
     * 
     * @param borrow 借阅信息
     * @return 结果
     */
    @Override
    public int insertBorrow(Borrow borrow)
    {
        return borrowMapper.insertBorrow(borrow);
    }

    /**
     * 修改借阅信息
     * 
     * @param borrow 借阅信息
     * @return 结果
     */
    @Override
    public int updateBorrow(Borrow borrow)
    {
        return borrowMapper.updateBorrow(borrow);
    }

    /**
     * 批量删除借阅信息
     * 
     * @param borrowIds 需要删除的借阅信息主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByBorrowIds(Long[] borrowIds)
    {
        return borrowMapper.deleteBorrowByBorrowIds(borrowIds);
    }

    /**
     * 删除借阅信息信息
     * 
     * @param borrowId 借阅信息主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByBorrowId(Long borrowId)
    {
        return borrowMapper.deleteBorrowByBorrowId(borrowId);
    }
}
