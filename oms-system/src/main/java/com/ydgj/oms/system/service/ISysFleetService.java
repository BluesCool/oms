package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysFleet;

/**
 * 车队Service接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface ISysFleetService 
{
    /**
     * 查询车队
     * 
     * @param fleetId 车队主键
     * @return 车队
     */
    public SysFleet selectSysFleetByFleetId(Long fleetId);

    /**
     * 查询车队列表
     * 
     * @param sysFleet 车队
     * @return 车队集合
     */
    public List<SysFleet> selectSysFleetList(SysFleet sysFleet);

    /**
     * 新增车队
     * 
     * @param sysFleet 车队
     * @return 结果
     */
    public int insertSysFleet(SysFleet sysFleet);

    /**
     * 修改车队
     * 
     * @param sysFleet 车队
     * @return 结果
     */
    public int updateSysFleet(SysFleet sysFleet);

    /**
     * 批量删除车队
     * 
     * @param fleetIds 需要删除的车队主键集合
     * @return 结果
     */
    public int deleteSysFleetByFleetIds(Long[] fleetIds);

    /**
     * 删除车队信息
     * 
     * @param fleetId 车队主键
     * @return 结果
     */
    public int deleteSysFleetByFleetId(Long fleetId);

    /**
     * 查询所有车队信息
     *
     * @return
     */
    List<SysFleet> selectFleetAll();
}
