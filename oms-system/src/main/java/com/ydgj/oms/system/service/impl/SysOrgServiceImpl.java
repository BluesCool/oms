package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysOrgMapper;
import com.ydgj.oms.system.domain.SysOrg;
import com.ydgj.oms.system.service.ISysOrgService;

/**
 * 组织机构Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysOrgServiceImpl implements ISysOrgService 
{
    @Autowired
    private SysOrgMapper sysOrgMapper;

    /**
     * 查询组织机构
     * 
     * @param orgId 组织机构主键
     * @return 组织机构
     */
    @Override
    public SysOrg selectSysOrgByOrgId(Long orgId)
    {
        return sysOrgMapper.selectSysOrgByOrgId(orgId);
    }

    /**
     * 查询组织机构列表
     * 
     * @param sysOrg 组织机构
     * @return 组织机构
     */
    @Override
    public List<SysOrg> selectSysOrgList(SysOrg sysOrg)
    {
        return sysOrgMapper.selectSysOrgList(sysOrg);
    }

    /**
     * 新增组织机构
     * 
     * @param sysOrg 组织机构
     * @return 结果
     */
    @Override
    public int insertSysOrg(SysOrg sysOrg)
    {
        sysOrg.setCreateTime(DateUtils.getNowDate());
        return sysOrgMapper.insertSysOrg(sysOrg);
    }

    /**
     * 修改组织机构
     * 
     * @param sysOrg 组织机构
     * @return 结果
     */
    @Override
    public int updateSysOrg(SysOrg sysOrg)
    {
        sysOrg.setUpdateTime(DateUtils.getNowDate());
        return sysOrgMapper.updateSysOrg(sysOrg);
    }

    /**
     * 批量删除组织机构
     * 
     * @param orgIds 需要删除的组织机构主键
     * @return 结果
     */
    @Override
    public int deleteSysOrgByOrgIds(Long[] orgIds)
    {
        return sysOrgMapper.deleteSysOrgByOrgIds(orgIds);
    }

    /**
     * 删除组织机构信息
     * 
     * @param orgId 组织机构主键
     * @return 结果
     */
    @Override
    public int deleteSysOrgByOrgId(Long orgId)
    {
        return sysOrgMapper.deleteSysOrgByOrgId(orgId);
    }

    /**
     * 查询所有单位信息
     *
     * @return
     */
    @Override
    public List<SysOrg> selectOrgAll() {
        return sysOrgMapper.selectOrgAll();
    }
}
