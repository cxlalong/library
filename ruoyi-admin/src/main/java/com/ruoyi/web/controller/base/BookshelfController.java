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
import com.ruoyi.base.domain.Bookshelf;
import com.ruoyi.base.service.IBookshelfService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 书架信息Controller
 * 
 * @author ljh
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/base/bookshelf")
public class BookshelfController extends BaseController
{
    @Autowired
    private IBookshelfService bookshelfService;

    /**
     * 查询书架信息列表
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bookshelf bookshelf)
    {
        startPage();
        List<Bookshelf> list = bookshelfService.selectBookshelfList(bookshelf);
        return getDataTable(list);
    }

    /**
     * 导出书架信息列表
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:export')")
    @Log(title = "书架信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bookshelf bookshelf)
    {
        List<Bookshelf> list = bookshelfService.selectBookshelfList(bookshelf);
        ExcelUtil<Bookshelf> util = new ExcelUtil<Bookshelf>(Bookshelf.class);
        util.exportExcel(response, list, "书架信息数据");
    }

    /**
     * 获取书架信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:query')")
    @GetMapping(value = "/{bookshelfId}")
    public AjaxResult getInfo(@PathVariable("bookshelfId") Long bookshelfId)
    {
        return success(bookshelfService.selectBookshelfByBookshelfId(bookshelfId));
    }

    /**
     * 新增书架信息
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:add')")
    @Log(title = "书架信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bookshelf bookshelf)
    {
        return toAjax(bookshelfService.insertBookshelf(bookshelf));
    }

    /**
     * 修改书架信息
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:edit')")
    @Log(title = "书架信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bookshelf bookshelf)
    {
        return toAjax(bookshelfService.updateBookshelf(bookshelf));
    }

    /**
     * 删除书架信息
     */
    @PreAuthorize("@ss.hasPermi('base:bookshelf:remove')")
    @Log(title = "书架信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookshelfIds}")
    public AjaxResult remove(@PathVariable Long[] bookshelfIds)
    {
        return toAjax(bookshelfService.deleteBookshelfByBookshelfIds(bookshelfIds));
    }
}
