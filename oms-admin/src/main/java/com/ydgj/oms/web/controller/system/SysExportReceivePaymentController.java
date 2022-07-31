package com.ydgj.oms.web.controller.system;

import com.ydgj.oms.common.annotation.Log;
import com.ydgj.oms.common.core.controller.BaseController;
import com.ydgj.oms.common.core.domain.AjaxResult;
import com.ydgj.oms.common.core.page.TableDataInfo;
import com.ydgj.oms.common.enums.BusinessType;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.system.domain.SysExportReceivePayment;
import com.ydgj.oms.system.service.ISysExportReceivePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 出口收款Controller
 * 
 * @author guwei
 * @date 2022-07-17
 */
@RestController
@RequestMapping("/system/exportReceive")
public class SysExportReceivePaymentController extends BaseController
{
    @Autowired
    private ISysExportReceivePaymentService sysExportReceivePaymentService;

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:edit')")
    @Log(title = "出口收款管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysExportReceivePayment sysExportReceivePayment) {

        sysExportReceivePayment.setUpdateBy(getUsername());
        return toAjax(sysExportReceivePaymentService.updateSysExportReceivePayment(sysExportReceivePayment));
    }

    /**
     * 查询出口收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExportReceivePayment sysExportReceivePayment)
    {
        startPage();
        List<SysExportReceivePayment> list = sysExportReceivePaymentService.selectSysExportReceivePaymentList(sysExportReceivePayment);
        return getDataTable(list);
    }

    /**
     * 导出出口收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:export')")
    @Log(title = "出口收款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExportReceivePayment sysExportReceivePayment)
    {
        List<SysExportReceivePayment> list = sysExportReceivePaymentService.selectSysExportReceivePaymentList(sysExportReceivePayment);
        ExcelUtil<SysExportReceivePayment> util = new ExcelUtil<SysExportReceivePayment>(SysExportReceivePayment.class);
        util.exportExcel(response, list, "出口收款数据");
    }

    /**
     * 获取出口收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:query')")
    @GetMapping(value = "/{exportReceivePaymentId}")
    public AjaxResult getInfo(@PathVariable("exportReceivePaymentId") Long exportReceivePaymentId)
    {
        return AjaxResult.success(sysExportReceivePaymentService.selectSysExportReceivePaymentByExportReceivePaymentId(exportReceivePaymentId));
    }

    /**
     * 新增出口收款
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:add')")
    @Log(title = "出口收款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExportReceivePayment sysExportReceivePayment)
    {
        return toAjax(sysExportReceivePaymentService.insertSysExportReceivePayment(sysExportReceivePayment));
    }

    /**
     * 修改出口收款
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:edit')")
    @Log(title = "出口收款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExportReceivePayment sysExportReceivePayment)
    {
        return toAjax(sysExportReceivePaymentService.updateSysExportReceivePayment(sysExportReceivePayment));
    }

    /**
     * 删除出口收款
     */
    @PreAuthorize("@ss.hasPermi('system:exportReceive:remove')")
    @Log(title = "出口收款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{exportReceivePaymentIds}")
    public AjaxResult remove(@PathVariable Long[] exportReceivePaymentIds)
    {
        return toAjax(sysExportReceivePaymentService.deleteSysExportReceivePaymentByExportReceivePaymentIds(exportReceivePaymentIds));
    }
}
