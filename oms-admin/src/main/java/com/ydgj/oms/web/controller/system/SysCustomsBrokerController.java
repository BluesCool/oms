package com.ydgj.oms.web.controller.system;

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
import com.ydgj.oms.common.annotation.Log;
import com.ydgj.oms.common.core.controller.BaseController;
import com.ydgj.oms.common.core.domain.AjaxResult;
import com.ydgj.oms.common.enums.BusinessType;
import com.ydgj.oms.system.domain.SysCustomsBroker;
import com.ydgj.oms.system.service.ISysCustomsBrokerService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 报关行Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/broker")
public class SysCustomsBrokerController extends BaseController
{
    @Autowired
    private ISysCustomsBrokerService sysCustomsBrokerService;

    /**
     * 查询报关行列表
     */
    @PreAuthorize("@ss.hasPermi('system:broker:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCustomsBroker sysCustomsBroker)
    {
        startPage();
        List<SysCustomsBroker> list = sysCustomsBrokerService.selectSysCustomsBrokerList(sysCustomsBroker);
        return getDataTable(list);
    }

    /**
     * 导出报关行列表
     */
    @PreAuthorize("@ss.hasPermi('system:broker:export')")
    @Log(title = "报关行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCustomsBroker sysCustomsBroker)
    {
        List<SysCustomsBroker> list = sysCustomsBrokerService.selectSysCustomsBrokerList(sysCustomsBroker);
        ExcelUtil<SysCustomsBroker> util = new ExcelUtil<SysCustomsBroker>(SysCustomsBroker.class);
        util.exportExcel(response, list, "报关行数据");
    }

    /**
     * 获取报关行详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:broker:query')")
    @GetMapping(value = "/{customsBrokerId}")
    public AjaxResult getInfo(@PathVariable("customsBrokerId") Long customsBrokerId)
    {
        return AjaxResult.success(sysCustomsBrokerService.selectSysCustomsBrokerByCustomsBrokerId(customsBrokerId));
    }

    /**
     * 新增报关行
     */
    @PreAuthorize("@ss.hasPermi('system:broker:add')")
    @Log(title = "报关行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCustomsBroker sysCustomsBroker)
    {
        return toAjax(sysCustomsBrokerService.insertSysCustomsBroker(sysCustomsBroker));
    }

    /**
     * 修改报关行
     */
    @PreAuthorize("@ss.hasPermi('system:broker:edit')")
    @Log(title = "报关行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCustomsBroker sysCustomsBroker)
    {
        return toAjax(sysCustomsBrokerService.updateSysCustomsBroker(sysCustomsBroker));
    }

    /**
     * 删除报关行
     */
    @PreAuthorize("@ss.hasPermi('system:broker:remove')")
    @Log(title = "报关行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customsBrokerIds}")
    public AjaxResult remove(@PathVariable Long[] customsBrokerIds)
    {
        return toAjax(sysCustomsBrokerService.deleteSysCustomsBrokerByCustomsBrokerIds(customsBrokerIds));
    }
}
