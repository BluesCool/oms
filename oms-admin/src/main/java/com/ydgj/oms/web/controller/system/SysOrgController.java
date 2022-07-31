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
import com.ydgj.oms.system.domain.SysOrg;
import com.ydgj.oms.system.service.ISysOrgService;
import com.ydgj.oms.common.utils.poi.ExcelUtil;
import com.ydgj.oms.common.core.page.TableDataInfo;

/**
 * 组织机构Controller
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@RestController
@RequestMapping("/system/org")
public class SysOrgController extends BaseController
{
    @Autowired
    private ISysOrgService sysOrgService;

    /**
     * 查询组织机构列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrg sysOrg)
    {
        startPage();
        List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
        return getDataTable(list);
    }

    /**
     * 导出组织机构列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:export')")
    @Log(title = "组织机构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrg sysOrg)
    {
        List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
        ExcelUtil<SysOrg> util = new ExcelUtil<SysOrg>(SysOrg.class);
        util.exportExcel(response, list, "组织机构数据");
    }

    /**
     * 获取组织机构详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:org:query')")
    @GetMapping(value = "/{orgId}")
    public AjaxResult getInfo(@PathVariable("orgId") Long orgId)
    {
        return AjaxResult.success(sysOrgService.selectSysOrgByOrgId(orgId));
    }

    /**
     * 新增组织机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:add')")
    @Log(title = "组织机构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrg sysOrg)
    {
        return toAjax(sysOrgService.insertSysOrg(sysOrg));
    }

    /**
     * 修改组织机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:edit')")
    @Log(title = "组织机构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrg sysOrg)
    {
        return toAjax(sysOrgService.updateSysOrg(sysOrg));
    }

    /**
     * 删除组织机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:remove')")
    @Log(title = "组织机构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orgIds}")
    public AjaxResult remove(@PathVariable Long[] orgIds)
    {
        return toAjax(sysOrgService.deleteSysOrgByOrgIds(orgIds));
    }
}
