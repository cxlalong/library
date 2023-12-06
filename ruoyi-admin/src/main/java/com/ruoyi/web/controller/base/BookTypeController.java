package com.ruoyi.web.controller.base;

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
import com.ruoyi.base.domain.BookType;
import com.ruoyi.base.service.IBookTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书类别Controller
 * 
 * @author ljh
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/base/booktype")
public class BookTypeController extends BaseController
{
    @Autowired
    private IBookTypeService bookTypeService;

    /**
     * 查询图书类别列表
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookType bookType)
    {
        startPage();
        List<BookType> list = bookTypeService.selectBookTypeList(bookType);
        return getDataTable(list);
    }

    /**
     * 导出图书类别列表
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:export')")
    @Log(title = "图书类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookType bookType)
    {
        List<BookType> list = bookTypeService.selectBookTypeList(bookType);
        ExcelUtil<BookType> util = new ExcelUtil<BookType>(BookType.class);
        util.exportExcel(response, list, "图书类别数据");
    }

    /**
     * 获取图书类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(bookTypeService.selectBookTypeByTypeId(typeId));
    }

    /**
     * 新增图书类别
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:add')")
    @Log(title = "图书类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookType bookType)
    {
        return toAjax(bookTypeService.insertBookType(bookType));
    }

    /**
     * 修改图书类别
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:edit')")
    @Log(title = "图书类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookType bookType)
    {
        return toAjax(bookTypeService.updateBookType(bookType));
    }

    /**
     * 删除图书类别
     */
    @PreAuthorize("@ss.hasPermi('base:booktype:remove')")
    @Log(title = "图书类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(bookTypeService.deleteBookTypeByTypeIds(typeIds));
    }
}
