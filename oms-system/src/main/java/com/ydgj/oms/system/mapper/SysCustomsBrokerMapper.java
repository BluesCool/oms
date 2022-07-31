package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysCustomsBroker;

/**
 * 报关行Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface SysCustomsBrokerMapper 
{
    /**
     * 查询报关行
     * 
     * @param customsBrokerId 报关行主键
     * @return 报关行
     */
    public SysCustomsBroker selectSysCustomsBrokerByCustomsBrokerId(Long customsBrokerId);

    /**
     * 查询报关行列表
     * 
     * @param sysCustomsBroker 报关行
     * @return 报关行集合
     */
    public List<SysCustomsBroker> selectSysCustomsBrokerList(SysCustomsBroker sysCustomsBroker);

    /**
     * 新增报关行
     * 
     * @param sysCustomsBroker 报关行
     * @return 结果
     */
    public int insertSysCustomsBroker(SysCustomsBroker sysCustomsBroker);

    /**
     * 修改报关行
     * 
     * @param sysCustomsBroker 报关行
     * @return 结果
     */
    public int updateSysCustomsBroker(SysCustomsBroker sysCustomsBroker);

    /**
     * 删除报关行
     * 
     * @param customsBrokerId 报关行主键
     * @return 结果
     */
    public int deleteSysCustomsBrokerByCustomsBrokerId(Long customsBrokerId);

    /**
     * 批量删除报关行
     * 
     * @param customsBrokerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCustomsBrokerByCustomsBrokerIds(Long[] customsBrokerIds);

    List<SysCustomsBroker> selectCustomsBrokerAll();


    /**
     * 获取报关行by name
     * @param customsBrokerName
     * @return
     */
    SysCustomsBroker selectSysCustomsBrokerByCustomsBrokerName(String customsBrokerName);
}
