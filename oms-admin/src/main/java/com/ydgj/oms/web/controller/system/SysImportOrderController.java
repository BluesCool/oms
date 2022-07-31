package com.ydgj.oms.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ydgj.oms.common.core.domain.entity.SysUser;
import com.ydgj.oms.common.utils.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2022-07-03
 */
@RestController
@RequestMapping("/system/order")
public class SysImportOrderController extends BaseController {
    @Autowired
    private ISysImportOrderService sysImportOrderService;

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
    private ISysImportReceivePaymentService sysImportReceivePaymentService;

    @Autowired
    private ISysImportPaymentService sysImportPaymentService;


    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysImportOrder sysImportOrder) {
        startPage();
        List<SysImportOrder> list = sysImportOrderService.selectSysImportOrderList(sysImportOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysImportOrder sysImportOrder) {
        List<SysImportOrder> list = sysImportOrderService.selectSysImportOrderList(sysImportOrder);
        ExcelUtil<SysImportOrder> util = new ExcelUtil<SysImportOrder>(SysImportOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    @Log(title = "订单管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:order:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        String operName = getUsername();
        String message = sysImportOrderService.importOrder(file.getInputStream(), operName, updateSupport);//userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<SysImportOrder> util = new ExcelUtil<SysImportOrder>(SysImportOrder.class);
        util.importTemplateExcel(response, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = {"", "/{importOrderId}"})
    public AjaxResult getInfo(@PathVariable(value = "importOrderId", required = false) Long importOrderId) {

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
        if (StringUtils.isNotNull(importOrderId)) {
            ajax.put(AjaxResult.DATA_TAG, sysImportOrderService.selectSysImportOrderByImportOrderId(importOrderId));
        }
        return ajax;
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:baseData')")
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

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysImportOrder sysImportOrder) {
        String operName = getUsername();
        return toAjax(sysImportOrderService.insertSysImportOrder(sysImportOrder, operName));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysImportOrder sysImportOrder) {
        return toAjax(sysImportOrderService.updateSysImportOrder(sysImportOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{importOrderIds}")
    public AjaxResult remove(@PathVariable Long[] importOrderIds) {
        List<Long> importReceivePaymentIdList = sysImportReceivePaymentService.selectIdsByImportOderIds(importOrderIds);
        Long[] importReceivePaymentIds = new Long[importReceivePaymentIdList.size()];
        importReceivePaymentIdList.toArray(importReceivePaymentIds);
        sysImportPaymentService.deleteSysImportPaymentByImportReceivePaymentIds(importReceivePaymentIds);
        sysImportReceivePaymentService.deleteSysImportReceivePaymentByImportOrders(importOrderIds);
        return toAjax(sysImportOrderService.deleteSysImportOrderByImportOrderIds(importOrderIds));
    }
}
