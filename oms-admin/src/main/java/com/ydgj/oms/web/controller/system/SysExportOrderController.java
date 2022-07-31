package com.ydgj.oms.web.controller.system;

import com.ydgj.oms.common.annotation.Log;
import com.ydgj.oms.common.core.controller.BaseController;
import com.ydgj.oms.common.core.domain.AjaxResult;
import com.ydgj.oms.common.core.page.TableDataInfo;
import com.ydgj.oms.common.enums.BusinessType;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.system.domain.*;
import com.ydgj.oms.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 出口订单Controller
 * 
 * @author ruoyi
 * @date 2022-07-17
 */
@RestController
@RequestMapping("/system/exportOrder")
public class SysExportOrderController extends BaseController
{
    @Autowired
    private ISysExportOrderService sysExportOrderService;

    @Autowired
    private ISysCheckService sysCheckService;

    @Autowired
    private ISysCurrencyService sysCurrencyService;

    @Autowired
    private ISysCustomsBrokerService sysCustomsBrokerService;

    @Autowired
    private ISysOrgService sysOrgService;

    @Autowired
    private ISysTradeWayService sysTradeWayService;

    @Autowired
    private ISysExportReceivePaymentService exportReceivePaymentService;

    @Autowired
    private ISysExportPaymentService exportPaymentService;

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:baseData')")
    @GetMapping( "baseData")
    public AjaxResult getBaseData()
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysCheck> checks = sysCheckService.selectCheckAll();
        ajax.put("checks",checks);
        List<SysCurrency> currencys =  sysCurrencyService.selectCurrencyAll();
        ajax.put("currencys",currencys);
        List<SysCustomsBroker> customsBrokers = sysCustomsBrokerService.selectCustomsBrokerAll();
        ajax.put("customsBrokers",customsBrokers);
        List<SysOrg> orgs = sysOrgService.selectOrgAll();
        ajax.put("orgs",orgs);
        List<SysTradeWay> tradeWays = sysTradeWayService.selectTradeWayAll();
        ajax.put("tradeWays",tradeWays);
        return ajax;
    }

    /**
     * 查询出口订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExportOrder sysExportOrder)
    {
        startPage();
        List<SysExportOrder> list = sysExportOrderService.selectSysExportOrderList(sysExportOrder);
        return getDataTable(list);
    }

    /**
     * 导出出口订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:export')")
    @Log(title = "出口订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExportOrder sysExportOrder)
    {
        List<SysExportOrder> list = sysExportOrderService.selectSysExportOrderList(sysExportOrder);
        ExcelUtil<SysExportOrder> util = new ExcelUtil<SysExportOrder>(SysExportOrder.class);
        util.exportExcel(response, list, "出口订单数据");
    }

    /**
     * 导入出口订单
     *
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "订单管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:order:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        String operName = getUsername();
        String message = sysExportOrderService.importOrder(file.getInputStream(),operName,updateSupport);//userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<SysExportOrder> util = new ExcelUtil<SysExportOrder>(SysExportOrder.class);
        util.importTemplateExcel(response, "订单数据");
    }

    /**
     * 获取出口订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:query')")
    @GetMapping(value = "/{exportOrderId}")
    public AjaxResult getInfo(@PathVariable("exportOrderId") Long exportOrderId)
    {
        return AjaxResult.success(sysExportOrderService.selectSysExportOrderByExportOrderId(exportOrderId));
    }

    /**
     * 新增出口订单
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:add')")
    @Log(title = "出口订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExportOrder sysExportOrder)
    {
        String operName = getUsername();
        int exportOrderId = sysExportOrderService.insertSysExportOrder(sysExportOrder, operName);

        return toAjax(exportOrderId);
    }

    /**
     * 修改出口订单
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:edit')")
    @Log(title = "出口订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExportOrder sysExportOrder)
    {
        return toAjax(sysExportOrderService.updateSysExportOrder(sysExportOrder));
    }

    /**
     * 删除出口订单
     */
    @PreAuthorize("@ss.hasPermi('system:exportOrder:remove')")
    @Log(title = "出口订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{exportOrderIds}")
    public AjaxResult remove(@PathVariable Long[] exportOrderIds)
    {
        List<Long> exportReceivePaymentList = exportReceivePaymentService.selectExportReceivePaymentIdsByExportOrderIds(exportOrderIds);
        Long[] exportReceivePaymentIds = new Long[exportReceivePaymentList.size()];
        exportReceivePaymentList.toArray(exportReceivePaymentIds);
        exportPaymentService.deleteSysExportPaymentByExportReceivePaymentIds(exportReceivePaymentIds);
        exportReceivePaymentService.deleteSysExportReceivePaymentByExportReceivePaymentIds(exportReceivePaymentIds);
        return toAjax(sysExportOrderService.deleteSysExportOrderByExportOrderIds(exportOrderIds));
    }
}
