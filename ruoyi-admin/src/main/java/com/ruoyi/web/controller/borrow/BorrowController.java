package com.ruoyi.web.controller.borrow;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.borrow.domain.Borrow;
import com.ruoyi.borrow.service.IBorrowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 借阅信息Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/borrow/borrow")
public class BorrowController extends BaseController
{
    @Autowired
    private IBorrowService borrowService;

    /**
     * 查询借阅信息列表
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(Borrow borrow)
    {
        startPage();
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        return getDataTable(list);
    }

    /**
     * 导出借阅信息列表
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:export')")
    @Log(title = "借阅信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Borrow borrow)
    {
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        ExcelUtil<Borrow> util = new ExcelUtil<Borrow>(Borrow.class);
        util.exportExcel(response, list, "借阅信息数据");
    }

    /**
     * 获取借阅信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:query')")
    @GetMapping(value = "/{borrowId}")
    public AjaxResult getInfo(@PathVariable("borrowId") Long borrowId)
    {
        return success(borrowService.selectBorrowByBorrowId(borrowId));
    }

    /**
     * 新增借阅信息
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:add')")
    @Log(title = "借阅信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Borrow borrow)
    {
        return toAjax(borrowService.insertBorrow(borrow));
    }

    /**
     * 修改借阅信息
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:edit')")
    @Log(title = "借阅信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Borrow borrow)
    {
        return toAjax(borrowService.updateBorrow(borrow));
    }

    /**
     * 删除借阅信息
     */
    @PreAuthorize("@ss.hasPermi('borrow:borrow:remove')")
    @Log(title = "借阅信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{borrowIds}")
    public AjaxResult remove(@PathVariable Long[] borrowIds)
    {
        return toAjax(borrowService.deleteBorrowByBorrowIds(borrowIds));
    }
}
