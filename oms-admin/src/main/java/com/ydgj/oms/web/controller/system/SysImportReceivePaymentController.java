package com.ydgj.oms.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ydgj.oms.common.core.domain.entity.SysUser;
import com.ydgj.oms.system.domain.*;
import com.ydgj.oms.system.service.*;
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
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 收款Controller
 *
 * @author gw
 * @date 2022-07-03
 */
@RestController
@RequestMapping("/system/receive")
public class SysImportReceivePaymentController extends BaseController {
    @Autowired
    private ISysImportReceivePaymentService sysImportReceivePaymentService;

    @Autowired
    private ISysCheckService sysCheckService;

    @Autowired
    private ISysCurrencyService sysCurrencyService;

    @Autowired
    private ISysCustomsBrokerService sysCustomsBrokerService;

    @Autowired
    private ISysFleetService sysFleetService;

    @Autowired
    private ISysOrgService sysOrgService;

    @Autowired
    private ISysTradeWayService sysTradeWayService;

    @Autowired
    private ISysImportPaymentService sysImportPaymentService;

    @Autowired
    private ISysImportOrderService importOrderService;

    /**
     * 查询收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:receive:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysImportReceivePayment sysImportReceivePayment) {
        startPage();
        List<SysImportReceivePayment> list = sysImportReceivePaymentService.selectSysImportReceivePaymentList(sysImportReceivePayment);
        return getDataTable(list);
    }

    /**
     * 导出收款列表
     */
    @PreAuthorize("@ss.hasPermi('system:receive:export')")
    @Log(title = "收款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysImportReceivePayment sysImportReceivePayment) {
        List<SysImportReceivePayment> list = sysImportReceivePaymentService.selectSysImportReceivePaymentList(sysImportReceivePayment);
        ExcelUtil<SysImportReceivePayment> util = new ExcelUtil<SysImportReceivePayment>(SysImportReceivePayment.class);
        util.exportExcel(response, list, "收款数据");
    }

    /**
     * 获取收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:receive:query')")
    @GetMapping(value = "/{importReceivePaymentId}")
    public AjaxResult getInfo(@PathVariable("importReceivePaymentId") Long importReceivePaymentId) {
        AjaxResult ajax = AjaxResult.success();
        SysImportReceivePayment importReceivePayment = sysImportReceivePaymentService.selectSysImportReceivePaymentByImportReceivePaymentId(importReceivePaymentId);
        ajax.put(AjaxResult.DATA_TAG, importReceivePayment);
        SysImportOrder importOrder = importOrderService.selectSysImportOrderByImportOrderId(importReceivePayment.getImportOrderId());
        ajax.put("importOrder", importOrder);
        return ajax;
    }

    /**
     * 新增收款
     */
    @PreAuthorize("@ss.hasPermi('system:receive:add')")
    @Log(title = "收款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImportReceivePayment sysImportReceivePayment) {
        return toAjax(sysImportReceivePaymentService.insertSysImportReceivePayment(sysImportReceivePayment));
    }

    /**
     * 修改收款
     */
    @PreAuthorize("@ss.hasPermi('system:receive:edit')")
    @Log(title = "收款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImportReceivePayment sysImportReceivePayment) {
        return toAjax(sysImportReceivePaymentService.updateSysImportReceivePayment(sysImportReceivePayment));
    }

    /**
     * 删除收款
     */
    @PreAuthorize("@ss.hasPermi('system:receive:remove')")
    @Log(title = "收款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{importReceivePaymentIds}")
    public AjaxResult remove(@PathVariable Long[] importReceivePaymentIds) {
        return toAjax(sysImportReceivePaymentService.deleteSysImportReceivePaymentByImportReceivePaymentIds(importReceivePaymentIds));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:receive:edit')")
    @Log(title = "进口收款管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysImportReceivePayment sysImportReceivePayment) {

        sysImportReceivePayment.setUpdateBy(getUsername());
        return toAjax(sysImportReceivePaymentService.updateReceiveStatus(sysImportReceivePayment));
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:receive:baseData')")
    @GetMapping("baseData")
    public AjaxResult getBaseData() {
        AjaxResult ajax = AjaxResult.success();
        List<SysCheck> checks = sysCheckService.selectCheckAll();
        ajax.put("checks", checks);
        List<SysCurrency> currencys = sysCurrencyService.selectCurrencyAll();
        ajax.put("currencys", currencys);
        List<SysCustomsBroker> customsBrokers = sysCustomsBrokerService.selectCustomsBrokerAll();
        ajax.put("customsBrokers", customsBrokers);
        List<SysFleet> fleets = sysFleetService.selectFleetAll();
        ajax.put("fleets", fleets);
        List<SysOrg> orgs = sysOrgService.selectOrgAll();
        ajax.put("orgs", orgs);
        List<SysTradeWay> tradeWays = sysTradeWayService.selectTradeWayAll();
        ajax.put("tradeWays", tradeWays);
        return ajax;
    }
}
