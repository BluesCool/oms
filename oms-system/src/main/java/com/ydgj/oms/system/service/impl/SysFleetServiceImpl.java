package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysFleetMapper;
import com.ydgj.oms.system.domain.SysFleet;
import com.ydgj.oms.system.service.ISysFleetService;

/**
 * 车队Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysFleetServiceImpl implements ISysFleetService 
{
    @Autowired
    private SysFleetMapper sysFleetMapper;

    /**
     * 查询车队
     * 
     * @param fleetId 车队主键
     * @return 车队
     */
    @Override
    public SysFleet selectSysFleetByFleetId(Long fleetId)
    {
        return sysFleetMapper.selectSysFleetByFleetId(fleetId);
    }

    /**
     * 查询车队列表
     * 
     * @param sysFleet 车队
     * @return 车队
     */
    @Override
    public List<SysFleet> selectSysFleetList(SysFleet sysFleet)
    {
        return sysFleetMapper.selectSysFleetList(sysFleet);
    }

    /**
     * 新增车队
     * 
     * @param sysFleet 车队
     * @return 结果
     */
    @Override
    public int insertSysFleet(SysFleet sysFleet)
    {
        sysFleet.setCreateTime(DateUtils.getNowDate());
        return sysFleetMapper.insertSysFleet(sysFleet);
    }

    /**
     * 修改车队
     * 
     * @param sysFleet 车队
     * @return 结果
     */
    @Override
    public int updateSysFleet(SysFleet sysFleet)
    {
        sysFleet.setUpdateTime(DateUtils.getNowDate());
        return sysFleetMapper.updateSysFleet(sysFleet);
    }

    /**
     * 批量删除车队
     * 
     * @param fleetIds 需要删除的车队主键
     * @return 结果
     */
    @Override
    public int deleteSysFleetByFleetIds(Long[] fleetIds)
    {
        return sysFleetMapper.deleteSysFleetByFleetIds(fleetIds);
    }

    /**
     * 删除车队信息
     * 
     * @param fleetId 车队主键
     * @return 结果
     */
    @Override
    public int deleteSysFleetByFleetId(Long fleetId)
    {
        return sysFleetMapper.deleteSysFleetByFleetId(fleetId);
    }

    @Override
    public List<SysFleet> selectFleetAll() {
        return sysFleetMapper.selectFleetAll();
    }
}
