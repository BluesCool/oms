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
import com.ydgj.oms.system.domain.SysCurrency;
import com.ydgj.oms.system.service.ISysCurrencyService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 货币Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/currency")
public class SysCurrencyController extends BaseController
{
    @Autowired
    private ISysCurrencyService sysCurrencyService;

    /**
     * 查询货币列表
     */
    @PreAuthorize("@ss.hasPermi('system:currency:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCurrency sysCurrency)
    {
        startPage();
        List<SysCurrency> list = sysCurrencyService.selectSysCurrencyList(sysCurrency);
        return getDataTable(list);
    }

    /**
     * 导出货币列表
     */
    @PreAuthorize("@ss.hasPermi('system:currency:export')")
    @Log(title = "货币", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCurrency sysCurrency)
    {
        List<SysCurrency> list = sysCurrencyService.selectSysCurrencyList(sysCurrency);
        ExcelUtil<SysCurrency> util = new ExcelUtil<SysCurrency>(SysCurrency.class);
        util.exportExcel(response, list, "货币数据");
    }

    /**
     * 获取货币详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:currency:query')")
    @GetMapping(value = "/{currencyId}")
    public AjaxResult getInfo(@PathVariable("currencyId") Long currencyId)
    {
        return AjaxResult.success(sysCurrencyService.selectSysCurrencyByCurrencyId(currencyId));
    }

    /**
     * 新增货币
     */
    @PreAuthorize("@ss.hasPermi('system:currency:add')")
    @Log(title = "货币", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCurrency sysCurrency)
    {
        return toAjax(sysCurrencyService.insertSysCurrency(sysCurrency));
    }

    /**
     * 修改货币
     */
    @PreAuthorize("@ss.hasPermi('system:currency:edit')")
    @Log(title = "货币", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCurrency sysCurrency)
    {
        return toAjax(sysCurrencyService.updateSysCurrency(sysCurrency));
    }

    /**
     * 删除货币
     */
    @PreAuthorize("@ss.hasPermi('system:currency:remove')")
    @Log(title = "货币", businessType = BusinessType.DELETE)
	@DeleteMapping("/{currencyIds}")
    public AjaxResult remove(@PathVariable Long[] currencyIds)
    {
        return toAjax(sysCurrencyService.deleteSysCurrencyByCurrencyIds(currencyIds));
    }
}
