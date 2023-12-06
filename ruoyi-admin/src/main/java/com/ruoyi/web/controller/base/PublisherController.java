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
import com.ruoyi.base.domain.Publisher;
import com.ruoyi.base.service.IPublisherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出版社信息Controller
 * 
 * @author ljh
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/base/publisher")
public class PublisherController extends BaseController
{
    @Autowired
    private IPublisherService publisherService;

    /**
     * 查询出版社信息列表
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:list')")
    @GetMapping("/list")
    public TableDataInfo list(Publisher publisher)
    {
        startPage();
        List<Publisher> list = publisherService.selectPublisherList(publisher);
        return getDataTable(list);
    }

    /**
     * 导出出版社信息列表
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:export')")
    @Log(title = "出版社信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Publisher publisher)
    {
        List<Publisher> list = publisherService.selectPublisherList(publisher);
        ExcelUtil<Publisher> util = new ExcelUtil<Publisher>(Publisher.class);
        util.exportExcel(response, list, "出版社信息数据");
    }

    /**
     * 获取出版社信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:query')")
    @GetMapping(value = "/{publisherId}")
    public AjaxResult getInfo(@PathVariable("publisherId") Long publisherId)
    {
        return success(publisherService.selectPublisherByPublisherId(publisherId));
    }

    /**
     * 新增出版社信息
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:add')")
    @Log(title = "出版社信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Publisher publisher)
    {
        return toAjax(publisherService.insertPublisher(publisher));
    }

    /**
     * 修改出版社信息
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:edit')")
    @Log(title = "出版社信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Publisher publisher)
    {
        return toAjax(publisherService.updatePublisher(publisher));
    }

    /**
     * 删除出版社信息
     */
    @PreAuthorize("@ss.hasPermi('base:publisher:remove')")
    @Log(title = "出版社信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{publisherIds}")
    public AjaxResult remove(@PathVariable Long[] publisherIds)
    {
        return toAjax(publisherService.deletePublisherByPublisherIds(publisherIds));
    }
}
