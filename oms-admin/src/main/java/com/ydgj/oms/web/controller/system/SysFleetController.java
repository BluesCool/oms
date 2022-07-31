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
import com.ydgj.oms.system.domain.SysFleet;
import com.ydgj.oms.system.service.ISysFleetService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 车队Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/fleet")
public class SysFleetController extends BaseController
{
    @Autowired
    private ISysFleetService sysFleetService;

    /**
     * 查询车队列表
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFleet sysFleet)
    {
        startPage();
        List<SysFleet> list = sysFleetService.selectSysFleetList(sysFleet);
        return getDataTable(list);
    }

    /**
     * 导出车队列表
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:export')")
    @Log(title = "车队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFleet sysFleet)
    {
        List<SysFleet> list = sysFleetService.selectSysFleetList(sysFleet);
        ExcelUtil<SysFleet> util = new ExcelUtil<SysFleet>(SysFleet.class);
        util.exportExcel(response, list, "车队数据");
    }

    /**
     * 获取车队详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:query')")
    @GetMapping(value = "/{fleetId}")
    public AjaxResult getInfo(@PathVariable("fleetId") Long fleetId)
    {
        return AjaxResult.success(sysFleetService.selectSysFleetByFleetId(fleetId));
    }

    /**
     * 新增车队
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:add')")
    @Log(title = "车队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFleet sysFleet)
    {
        return toAjax(sysFleetService.insertSysFleet(sysFleet));
    }

    /**
     * 修改车队
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:edit')")
    @Log(title = "车队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFleet sysFleet)
    {
        return toAjax(sysFleetService.updateSysFleet(sysFleet));
    }

    /**
     * 删除车队
     */
    @PreAuthorize("@ss.hasPermi('system:fleet:remove')")
    @Log(title = "车队", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fleetIds}")
    public AjaxResult remove(@PathVariable Long[] fleetIds)
    {
        return toAjax(sysFleetService.deleteSysFleetByFleetIds(fleetIds));
    }
}
