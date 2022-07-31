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
import com.ydgj.oms.system.domain.SysCheck;
import com.ydgj.oms.system.service.ISysCheckService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 查验Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/check")
public class SysCheckController extends BaseController
{
    @Autowired
    private ISysCheckService sysCheckService;

    /**
     * 查询查验列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCheck sysCheck)
    {
        startPage();
        List<SysCheck> list = sysCheckService.selectSysCheckList(sysCheck);
        return getDataTable(list);
    }

    /**
     * 导出查验列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "查验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCheck sysCheck)
    {
        List<SysCheck> list = sysCheckService.selectSysCheckList(sysCheck);
        ExcelUtil<SysCheck> util = new ExcelUtil<SysCheck>(SysCheck.class);
        util.exportExcel(response, list, "查验数据");
    }

    /**
     * 获取查验详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{checkId}")
    public AjaxResult getInfo(@PathVariable("checkId") Long checkId)
    {
        return AjaxResult.success(sysCheckService.selectSysCheckByCheckId(checkId));
    }

    /**
     * 新增查验
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "查验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCheck sysCheck)
    {
        return toAjax(sysCheckService.insertSysCheck(sysCheck));
    }

    /**
     * 修改查验
     */
    @PreAuthorize("@ss.hasPermi('system:check:edit')")
    @Log(title = "查验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCheck sysCheck)
    {
        return toAjax(sysCheckService.updateSysCheck(sysCheck));
    }

    /**
     * 删除查验
     */
    @PreAuthorize("@ss.hasPermi('system:check:remove')")
    @Log(title = "查验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkIds}")
    public AjaxResult remove(@PathVariable Long[] checkIds)
    {
        return toAjax(sysCheckService.deleteSysCheckByCheckIds(checkIds));
    }
}
