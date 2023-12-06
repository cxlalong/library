package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.Reader;
import com.ruoyi.system.service.IReaderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 读者信息Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/system/reader")
public class ReaderController extends BaseController
{
    @Autowired
    private IReaderService readerService;

    /**
     * 查询读者信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:reader:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reader reader)
    {
        startPage();
        List<Reader> list = readerService.selectReaderList(reader);
        return getDataTable(list);
    }

    /**
     * 导出读者信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:reader:export')")
    @Log(title = "读者信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reader reader)
    {
        List<Reader> list = readerService.selectReaderList(reader);
        ExcelUtil<Reader> util = new ExcelUtil<Reader>(Reader.class);
        util.exportExcel(response, list, "读者信息数据");
    }

    /**
     * 获取读者信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reader:query')")
    @GetMapping(value = "/{readerId}")
    public AjaxResult getInfo(@PathVariable("readerId") Long readerId)
    {
        return success(readerService.selectReaderByReaderId(readerId));
    }

    /**
     * 新增读者信息
     */
    @PreAuthorize("@ss.hasPermi('system:reader:add')")
    @Log(title = "读者信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reader reader)
    {
        return toAjax(readerService.insertReader(reader));
    }

    /**
     * 修改读者信息
     */
    @PreAuthorize("@ss.hasPermi('system:reader:edit')")
    @Log(title = "读者信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reader reader)
    {
        return toAjax(readerService.updateReader(reader));
    }

    /**
     * 删除读者信息
     */
    @PreAuthorize("@ss.hasPermi('system:reader:remove')")
    @Log(title = "读者信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{readerIds}")
    public AjaxResult remove(@PathVariable Long[] readerIds)
    {
        return toAjax(readerService.deleteReaderByReaderIds(readerIds));
    }
}
