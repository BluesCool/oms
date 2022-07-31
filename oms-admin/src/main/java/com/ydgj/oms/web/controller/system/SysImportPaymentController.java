package com.ydgj.oms.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ydgj.oms.system.domain.SysImportReceivePayment;
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
import com.ydgj.oms.system.domain.SysImportPayment;
import com.ydgj.oms.system.service.ISysImportPaymentService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 付款Controller
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
@RestController
@RequestMapping("/system/payment")
public class SysImportPaymentController extends BaseController
{
    @Autowired
    private ISysImportPaymentService sysImportPaymentService;

    /**
     * 查询付款列表
     */
    @PreAuthorize("@ss.hasPermi('system:payment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysImportPayment sysImportPayment)
    {
        startPage();
        List<SysImportPayment> list = sysImportPaymentService.selectSysImportPaymentList(sysImportPayment);
        return getDataTable(list);
    }

    /**
     * 导出付款列表
     */
    @PreAuthorize("@ss.hasPermi('system:payment:export')")
    @Log(title = "付款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysImportPayment sysImportPayment)
    {
        List<SysImportPayment> list = sysImportPaymentService.selectSysImportPaymentList(sysImportPayment);
        ExcelUtil<SysImportPayment> util = new ExcelUtil<SysImportPayment>(SysImportPayment.class);
        util.exportExcel(response, list, "付款数据");
    }

    /**
     * 获取付款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:payment:query')")
    @GetMapping(value = "/{importPaymentId}")
    public AjaxResult getInfo(@PathVariable("importPaymentId") Long importPaymentId)
    {
        return AjaxResult.success(sysImportPaymentService.selectSysImportPaymentByImportPaymentId(importPaymentId));
    }

    /**
     * 新增付款
     */
    @PreAuthorize("@ss.hasPermi('system:payment:add')")
    @Log(title = "付款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImportPayment sysImportPayment)
    {
        return toAjax(sysImportPaymentService.insertSysImportPayment(sysImportPayment));
    }

    /**
     * 修改付款
     */
    @PreAuthorize("@ss.hasPermi('system:payment:edit')")
    @Log(title = "付款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImportPayment sysImportPayment)
    {
        return toAjax(sysImportPaymentService.updateSysImportPayment(sysImportPayment));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:payment:edit')")
    @Log(title = "进口收款管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysImportPayment sysImportPayment)
    {

        sysImportPayment.setUpdateBy(getUsername());
        return toAjax(sysImportPaymentService.updatePaymentStatus(sysImportPayment));
    }

    /**
     * 删除付款
     */
    @PreAuthorize("@ss.hasPermi('system:payment:remove')")
    @Log(title = "付款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{importPaymentIds}")
    public AjaxResult remove(@PathVariable Long[] importPaymentIds)
    {
        return toAjax(sysImportPaymentService.deleteSysImportPaymentByImportPaymentIds(importPaymentIds));
    }
}
