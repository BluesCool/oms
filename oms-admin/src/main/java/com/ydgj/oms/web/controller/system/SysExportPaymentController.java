package com.ydgj.oms.web.controller.system;

import com.ydgj.oms.common.annotation.Log;
import com.ydgj.oms.common.core.controller.BaseController;
import com.ydgj.oms.common.core.domain.AjaxResult;
import com.ydgj.oms.common.core.page.TableDataInfo;
import com.ydgj.oms.common.enums.BusinessType;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.system.domain.SysExportPayment;
import com.ydgj.oms.system.service.ISysExportPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 出口付款Controller
 * 
 * @author guwei
 * @date 2022-07-17
 */
@RestController
@RequestMapping("/system/exportPayment")
public class SysExportPaymentController extends BaseController
{
    @Autowired
    private ISysExportPaymentService sysExportPaymentService;


    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:edit')")
    @Log(title = "出口收款管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysExportPayment sysExportPayment)
    {

        sysExportPayment.setUpdateBy(getUsername());
        return toAjax(sysExportPaymentService.updateSysExportPayment(sysExportPayment));
    }

    /**
     * 查询出口付款列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExportPayment sysExportPayment)
    {
        startPage();
        List<SysExportPayment> list = sysExportPaymentService.selectSysExportPaymentList(sysExportPayment);
        return getDataTable(list);
    }

    /**
     * 导出出口付款列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:export')")
    @Log(title = "出口付款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExportPayment sysExportPayment)
    {
        List<SysExportPayment> list = sysExportPaymentService.selectSysExportPaymentList(sysExportPayment);
        ExcelUtil<SysExportPayment> util = new ExcelUtil<SysExportPayment>(SysExportPayment.class);
        util.exportExcel(response, list, "出口付款数据");
    }

    /**
     * 获取出口付款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:query')")
    @GetMapping(value = "/{exportPaymentId}")
    public AjaxResult getInfo(@PathVariable("exportPaymentId") Long exportPaymentId)
    {
        return AjaxResult.success(sysExportPaymentService.selectSysExportPaymentByExportPaymentId(exportPaymentId));
    }

    /**
     * 新增出口付款
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:add')")
    @Log(title = "出口付款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExportPayment sysExportPayment)
    {
        return toAjax(sysExportPaymentService.insertSysExportPayment(sysExportPayment));
    }

    /**
     * 修改出口付款
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:edit')")
    @Log(title = "出口付款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExportPayment sysExportPayment)
    {
        return toAjax(sysExportPaymentService.updateSysExportPayment(sysExportPayment));
    }

    /**
     * 删除出口付款
     */
    @PreAuthorize("@ss.hasPermi('system:exportPayment:remove')")
    @Log(title = "出口付款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{exportPaymentIds}")
    public AjaxResult remove(@PathVariable Long[] exportPaymentIds)
    {
        return toAjax(sysExportPaymentService.deleteSysExportPaymentByExportPaymentIds(exportPaymentIds));
    }
}
