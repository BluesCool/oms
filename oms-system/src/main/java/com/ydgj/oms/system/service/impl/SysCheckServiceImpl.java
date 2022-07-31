package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysCheckMapper;
import com.ydgj.oms.system.domain.SysCheck;
import com.ydgj.oms.system.service.ISysCheckService;

/**
 * 查验Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysCheckServiceImpl implements ISysCheckService 
{
    @Autowired
    private SysCheckMapper sysCheckMapper;

    /**
     * 查询查验
     * 
     * @param checkId 查验主键
     * @return 查验
     */
    @Override
    public SysCheck selectSysCheckByCheckId(Long checkId)
    {
        return sysCheckMapper.selectSysCheckByCheckId(checkId);
    }

    /**
     * 查询查验列表
     * 
     * @param sysCheck 查验
     * @return 查验
     */
    @Override
    public List<SysCheck> selectSysCheckList(SysCheck sysCheck)
    {
        return sysCheckMapper.selectSysCheckList(sysCheck);
    }

    /**
     * 新增查验
     * 
     * @param sysCheck 查验
     * @return 结果
     */
    @Override
    public int insertSysCheck(SysCheck sysCheck)
    {
        sysCheck.setCreateTime(DateUtils.getNowDate());
        return sysCheckMapper.insertSysCheck(sysCheck);
    }

    /**
     * 修改查验
     * 
     * @param sysCheck 查验
     * @return 结果
     */
    @Override
    public int updateSysCheck(SysCheck sysCheck)
    {
        sysCheck.setUpdateTime(DateUtils.getNowDate());
        return sysCheckMapper.updateSysCheck(sysCheck);
    }

    /**
     * 批量删除查验
     * 
     * @param checkIds 需要删除的查验主键
     * @return 结果
     */
    @Override
    public int deleteSysCheckByCheckIds(Long[] checkIds)
    {
        return sysCheckMapper.deleteSysCheckByCheckIds(checkIds);
    }

    /**
     * 删除查验信息
     * 
     * @param checkId 查验主键
     * @return 结果
     */
    @Override
    public int deleteSysCheckByCheckId(Long checkId)
    {
        return sysCheckMapper.deleteSysCheckByCheckId(checkId);
    }

    /**
     * 查询所有查验
     *
     */
    @Override
    public List<SysCheck> selectCheckAll() {
        return sysCheckMapper.selectCheckAll();
    }
}
