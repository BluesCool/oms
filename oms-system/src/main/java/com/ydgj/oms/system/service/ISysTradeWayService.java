package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysTradeWay;

/**
 * 贸易方式Service接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface ISysTradeWayService 
{
    /**
     * 查询贸易方式
     * 
     * @param tradeWayId 贸易方式主键
     * @return 贸易方式
     */
    public SysTradeWay selectSysTradeWayByTradeWayId(Long tradeWayId);

    /**
     * 查询贸易方式列表
     * 
     * @param sysTradeWay 贸易方式
     * @return 贸易方式集合
     */
    public List<SysTradeWay> selectSysTradeWayList(SysTradeWay sysTradeWay);

    /**
     * 新增贸易方式
     * 
     * @param sysTradeWay 贸易方式
     * @return 结果
     */
    public int insertSysTradeWay(SysTradeWay sysTradeWay);

    /**
     * 修改贸易方式
     * 
     * @param sysTradeWay 贸易方式
     * @return 结果
     */
    public int updateSysTradeWay(SysTradeWay sysTradeWay);

    /**
     * 批量删除贸易方式
     * 
     * @param tradeWayIds 需要删除的贸易方式主键集合
     * @return 结果
     */
    public int deleteSysTradeWayByTradeWayIds(Long[] tradeWayIds);

    /**
     * 删除贸易方式信息
     * 
     * @param tradeWayId 贸易方式主键
     * @return 结果
     */
    public int deleteSysTradeWayByTradeWayId(Long tradeWayId);

    /**
     * 查询所有贸易方式
     *
     * @return
     */
    List<SysTradeWay> selectTradeWayAll();
}
