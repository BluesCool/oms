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
import com.ydgj.oms.system.domain.SysTradeWay;
import com.ydgj.oms.system.service.ISysTradeWayService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 贸易方式Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/way")
public class SysTradeWayController extends BaseController
{
    @Autowired
    private ISysTradeWayService sysTradeWayService;

    /**
     * 查询贸易方式列表
     */
    @PreAuthorize("@ss.hasPermi('system:way:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTradeWay sysTradeWay)
    {
        startPage();
        List<SysTradeWay> list = sysTradeWayService.selectSysTradeWayList(sysTradeWay);
        return getDataTable(list);
    }

    /**
     * 导出贸易方式列表
     */
    @PreAuthorize("@ss.hasPermi('system:way:export')")
    @Log(title = "贸易方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTradeWay sysTradeWay)
    {
        List<SysTradeWay> list = sysTradeWayService.selectSysTradeWayList(sysTradeWay);
        ExcelUtil<SysTradeWay> util = new ExcelUtil<SysTradeWay>(SysTradeWay.class);
        util.exportExcel(response, list, "贸易方式数据");
    }

    /**
     * 获取贸易方式详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:way:query')")
    @GetMapping(value = "/{tradeWayId}")
    public AjaxResult getInfo(@PathVariable("tradeWayId") Long tradeWayId)
    {
        return AjaxResult.success(sysTradeWayService.selectSysTradeWayByTradeWayId(tradeWayId));
    }

    /**
     * 新增贸易方式
     */
    @PreAuthorize("@ss.hasPermi('system:way:add')")
    @Log(title = "贸易方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTradeWay sysTradeWay)
    {
        return toAjax(sysTradeWayService.insertSysTradeWay(sysTradeWay));
    }

    /**
     * 修改贸易方式
     */
    @PreAuthorize("@ss.hasPermi('system:way:edit')")
    @Log(title = "贸易方式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTradeWay sysTradeWay)
    {
        return toAjax(sysTradeWayService.updateSysTradeWay(sysTradeWay));
    }

    /**
     * 删除贸易方式
     */
    @PreAuthorize("@ss.hasPermi('system:way:remove')")
    @Log(title = "贸易方式", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tradeWayIds}")
    public AjaxResult remove(@PathVariable Long[] tradeWayIds)
    {
        return toAjax(sysTradeWayService.deleteSysTradeWayByTradeWayIds(tradeWayIds));
    }
}
