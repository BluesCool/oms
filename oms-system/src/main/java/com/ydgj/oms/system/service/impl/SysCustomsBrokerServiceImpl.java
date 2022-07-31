package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysCustomsBrokerMapper;
import com.ydgj.oms.system.domain.SysCustomsBroker;
import com.ydgj.oms.system.service.ISysCustomsBrokerService;

/**
 * 报关行Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysCustomsBrokerServiceImpl implements ISysCustomsBrokerService 
{
    @Autowired
    private SysCustomsBrokerMapper sysCustomsBrokerMapper;

    /**
     * 查询报关行
     * 
     * @param customsBrokerId 报关行主键
     * @return 报关行
     */
    @Override
    public SysCustomsBroker selectSysCustomsBrokerByCustomsBrokerId(Long customsBrokerId)
    {
        return sysCustomsBrokerMapper.selectSysCustomsBrokerByCustomsBrokerId(customsBrokerId);
    }

    /**
     * 查询报关行列表
     * 
     * @param sysCustomsBroker 报关行
     * @return 报关行
     */
    @Override
    public List<SysCustomsBroker> selectSysCustomsBrokerList(SysCustomsBroker sysCustomsBroker)
    {
        return sysCustomsBrokerMapper.selectSysCustomsBrokerList(sysCustomsBroker);
    }

    /**
     * 新增报关行
     * 
     * @param sysCustomsBroker 报关行
     * @return 结果
     */
    @Override
    public int insertSysCustomsBroker(SysCustomsBroker sysCustomsBroker)
    {
        sysCustomsBroker.setCreateTime(DateUtils.getNowDate());
        return sysCustomsBrokerMapper.insertSysCustomsBroker(sysCustomsBroker);
    }

    /**
     * 修改报关行
     * 
     * @param sysCustomsBroker 报关行
     * @return 结果
     */
    @Override
    public int updateSysCustomsBroker(SysCustomsBroker sysCustomsBroker)
    {
        sysCustomsBroker.setUpdateTime(DateUtils.getNowDate());
        return sysCustomsBrokerMapper.updateSysCustomsBroker(sysCustomsBroker);
    }

    /**
     * 批量删除报关行
     * 
     * @param customsBrokerIds 需要删除的报关行主键
     * @return 结果
     */
    @Override
    public int deleteSysCustomsBrokerByCustomsBrokerIds(Long[] customsBrokerIds)
    {
        return sysCustomsBrokerMapper.deleteSysCustomsBrokerByCustomsBrokerIds(customsBrokerIds);
    }

    /**
     * 删除报关行信息
     * 
     * @param customsBrokerId 报关行主键
     * @return 结果
     */
    @Override
    public int deleteSysCustomsBrokerByCustomsBrokerId(Long customsBrokerId)
    {
        return sysCustomsBrokerMapper.deleteSysCustomsBrokerByCustomsBrokerId(customsBrokerId);
    }

    /**
     * 查询所有报关行信息
     *
     * @return
     */
    @Override
    public List<SysCustomsBroker> selectCustomsBrokerAll() {
        return sysCustomsBrokerMapper.selectCustomsBrokerAll();
    }
}
