package com.ruoyi.web.controller.inbound;

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
import com.ruoyi.inbound.domain.Inbound;
import com.ruoyi.inbound.service.IInboundService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库管理Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/inbound/inbound")
public class InboundController extends BaseController
{
    @Autowired
    private IInboundService inboundService;

    /**
     * 查询入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:list')")
    @GetMapping("/list")
    public TableDataInfo list(Inbound inbound)
    {
        startPage();
        List<Inbound> list = inboundService.selectInboundList(inbound);
        return getDataTable(list);
    }

    /**
     * 导出入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:export')")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inbound inbound)
    {
        List<Inbound> list = inboundService.selectInboundList(inbound);
        ExcelUtil<Inbound> util = new ExcelUtil<Inbound>(Inbound.class);
        util.exportExcel(response, list, "入库管理数据");
    }

    /**
     * 获取入库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:query')")
    @GetMapping(value = "/{inboundId}")
    public AjaxResult getInfo(@PathVariable("inboundId") Long inboundId)
    {
        return success(inboundService.selectInboundByInboundId(inboundId));
    }

    /**
     * 新增入库管理
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:add')")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inbound inbound)
    {
        return toAjax(inboundService.insertInbound(inbound));
    }

    /**
     * 修改入库管理
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:edit')")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inbound inbound)
    {
        return toAjax(inboundService.updateInbound(inbound));
    }

    /**
     * 删除入库管理
     */
    @PreAuthorize("@ss.hasPermi('inbound:inbound:remove')")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inboundIds}")
    public AjaxResult remove(@PathVariable Long[] inboundIds)
    {
        return toAjax(inboundService.deleteInboundByInboundIds(inboundIds));
    }
}
