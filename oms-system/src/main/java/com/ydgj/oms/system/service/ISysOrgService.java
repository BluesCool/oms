package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysOrg;

/**
 * 组织机构Service接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface ISysOrgService 
{
    /**
     * 查询组织机构
     * 
     * @param orgId 组织机构主键
     * @return 组织机构
     */
    public SysOrg selectSysOrgByOrgId(Long orgId);

    /**
     * 查询组织机构列表
     * 
     * @param sysOrg 组织机构
     * @return 组织机构集合
     */
    public List<SysOrg> selectSysOrgList(SysOrg sysOrg);

    /**
     * 新增组织机构
     * 
     * @param sysOrg 组织机构
     * @return 结果
     */
    public int insertSysOrg(SysOrg sysOrg);

    /**
     * 修改组织机构
     * 
     * @param sysOrg 组织机构
     * @return 结果
     */
    public int updateSysOrg(SysOrg sysOrg);

    /**
     * 批量删除组织机构
     * 
     * @param orgIds 需要删除的组织机构主键集合
     * @return 结果
     */
    public int deleteSysOrgByOrgIds(Long[] orgIds);

    /**
     * 删除组织机构信息
     * 
     * @param orgId 组织机构主键
     * @return 结果
     */
    public int deleteSysOrgByOrgId(Long orgId);

    /**
     * 查询所有单位信息
     *
     * @return
     */
    List<SysOrg> selectOrgAll();
}
