package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysExportPaymentMapper;
import com.ydgj.oms.system.domain.SysExportPayment;
import com.ydgj.oms.system.service.ISysExportPaymentService;

/**
 * 出口付款Service业务层处理
 * 
 * @author guwei
 * @date 2022-07-17
 */
@Service
public class SysExportPaymentServiceImpl implements ISysExportPaymentService 
{
    @Autowired
    private SysExportPaymentMapper sysExportPaymentMapper;

    /**
     * 查询出口付款
     * 
     * @param exportPaymentId 出口付款主键
     * @return 出口付款
     */
    @Override
    public SysExportPayment selectSysExportPaymentByExportPaymentId(Long exportPaymentId)
    {
        return sysExportPaymentMapper.selectSysExportPaymentByExportPaymentId(exportPaymentId);
    }

    /**
     * 查询出口付款列表
     * 
     * @param sysExportPayment 出口付款
     * @return 出口付款
     */
    @Override
    public List<SysExportPayment> selectSysExportPaymentList(SysExportPayment sysExportPayment)
    {
        return sysExportPaymentMapper.selectSysExportPaymentList(sysExportPayment);
    }

    /**
     * 新增出口付款
     * 
     * @param sysExportPayment 出口付款
     * @return 结果
     */
    @Override
    public int insertSysExportPayment(SysExportPayment sysExportPayment)
    {
        sysExportPayment.setCreateTime(DateUtils.getNowDate());
        return sysExportPaymentMapper.insertSysExportPayment(sysExportPayment);
    }

    /**
     * 修改出口付款
     * 
     * @param sysExportPayment 出口付款
     * @return 结果
     */
    @Override
    public int updateSysExportPayment(SysExportPayment sysExportPayment)
    {
        sysExportPayment.setUpdateTime(DateUtils.getNowDate());
        return sysExportPaymentMapper.updateSysExportPayment(sysExportPayment);
    }

    /**
     * 批量删除出口付款
     * 
     * @param exportPaymentIds 需要删除的出口付款主键
     * @return 结果
     */
    @Override
    public int deleteSysExportPaymentByExportPaymentIds(Long[] exportPaymentIds)
    {
        return sysExportPaymentMapper.deleteSysExportPaymentByExportPaymentIds(exportPaymentIds);
    }

    /**
     * 删除出口付款信息
     * 
     * @param exportPaymentId 出口付款主键
     * @return 结果
     */
    @Override
    public int deleteSysExportPaymentByExportPaymentId(Long exportPaymentId)
    {
        return sysExportPaymentMapper.deleteSysExportPaymentByExportPaymentId(exportPaymentId);
    }

    /**
     * 删除出口付款信息
     *
     * @param exportReceivePaymentIds 出口付款主键
     * @return 结果
     */
    @Override
    public int deleteSysExportPaymentByExportReceivePaymentIds(Long[] exportReceivePaymentIds) {
        return sysExportPaymentMapper.deleteSysExportPaymentByExportReceivePaymentIds(exportReceivePaymentIds);
    }
}
