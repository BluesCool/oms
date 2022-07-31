package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysTradeWayMapper;
import com.ydgj.oms.system.domain.SysTradeWay;
import com.ydgj.oms.system.service.ISysTradeWayService;

/**
 * 贸易方式Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysTradeWayServiceImpl implements ISysTradeWayService 
{
    @Autowired
    private SysTradeWayMapper sysTradeWayMapper;

    /**
     * 查询贸易方式
     * 
     * @param tradeWayId 贸易方式主键
     * @return 贸易方式
     */
    @Override
    public SysTradeWay selectSysTradeWayByTradeWayId(Long tradeWayId)
    {
        return sysTradeWayMapper.selectSysTradeWayByTradeWayId(tradeWayId);
    }

    /**
     * 查询贸易方式列表
     * 
     * @param sysTradeWay 贸易方式
     * @return 贸易方式
     */
    @Override
    public List<SysTradeWay> selectSysTradeWayList(SysTradeWay sysTradeWay)
    {
        return sysTradeWayMapper.selectSysTradeWayList(sysTradeWay);
    }

    /**
     * 新增贸易方式
     * 
     * @param sysTradeWay 贸易方式
     * @return 结果
     */
    @Override
    public int insertSysTradeWay(SysTradeWay sysTradeWay)
    {
        sysTradeWay.setCreateTime(DateUtils.getNowDate());
        return sysTradeWayMapper.insertSysTradeWay(sysTradeWay);
    }

    /**
     * 修改贸易方式
     * 
     * @param sysTradeWay 贸易方式
     * @return 结果
     */
    @Override
    public int updateSysTradeWay(SysTradeWay sysTradeWay)
    {
        sysTradeWay.setUpdateTime(DateUtils.getNowDate());
        return sysTradeWayMapper.updateSysTradeWay(sysTradeWay);
    }

    /**
     * 批量删除贸易方式
     * 
     * @param tradeWayIds 需要删除的贸易方式主键
     * @return 结果
     */
    @Override
    public int deleteSysTradeWayByTradeWayIds(Long[] tradeWayIds)
    {
        return sysTradeWayMapper.deleteSysTradeWayByTradeWayIds(tradeWayIds);
    }

    /**
     * 删除贸易方式信息
     * 
     * @param tradeWayId 贸易方式主键
     * @return 结果
     */
    @Override
    public int deleteSysTradeWayByTradeWayId(Long tradeWayId)
    {
        return sysTradeWayMapper.deleteSysTradeWayByTradeWayId(tradeWayId);
    }

    /**
     * 查询所有贸易方式
     *
     * @return
     */
    @Override
    public List<SysTradeWay> selectTradeWayAll() {
        return sysTradeWayMapper.selectTradeWayAll();
    }
}
