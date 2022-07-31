package com.ydgj.oms.system.service.impl;

import com.ydgj.oms.common.utils.DateUtils;
import com.ydgj.oms.system.domain.SysImportPayment;
import com.ydgj.oms.system.mapper.SysImportPaymentMapper;
import com.ydgj.oms.system.service.ISysImportPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 付款Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
@Service
public class SysImportPaymentServiceImpl implements ISysImportPaymentService 
{
    @Autowired
    private SysImportPaymentMapper sysImportPaymentMapper;

    /**
     * 查询付款
     * 
     * @param importPaymentId 付款主键
     * @return 付款
     */
    @Override
    public SysImportPayment selectSysImportPaymentByImportPaymentId(Long importPaymentId)
    {
        return sysImportPaymentMapper.selectSysImportPaymentByImportPaymentId(importPaymentId);
    }

    /**
     * 查询付款列表
     * 
     * @param sysImportPayment 付款
     * @return 付款
     */
    @Override
    public List<SysImportPayment> selectSysImportPaymentList(SysImportPayment sysImportPayment)
    {
        return sysImportPaymentMapper.selectSysImportPaymentList(sysImportPayment);
    }

    /**
     * 新增付款
     * 
     * @param sysImportPayment 付款
     * @return 结果
     */
    @Override
    public int insertSysImportPayment(SysImportPayment sysImportPayment)
    {
        sysImportPayment.setCreateTime(DateUtils.getNowDate());
        return sysImportPaymentMapper.insertSysImportPayment(sysImportPayment);
    }

    /**
     * 修改付款
     * 
     * @param sysImportPayment 付款
     * @return 结果
     */
    @Override
    public int updateSysImportPayment(SysImportPayment sysImportPayment)
    {
        sysImportPayment.setUpdateTime(DateUtils.getNowDate());
        return sysImportPaymentMapper.updateSysImportPayment(sysImportPayment);
    }

    /**
     * 批量删除付款
     * 
     * @param importPaymentIds 需要删除的付款主键
     * @return 结果
     */
    @Override
    public int deleteSysImportPaymentByImportPaymentIds(Long[] importPaymentIds)
    {
        return sysImportPaymentMapper.deleteSysImportPaymentByImportPaymentIds(importPaymentIds);
    }

    /**
     * 删除付款信息
     * @param importReceivePaymentIds
     * @return
     */
    @Override
    public int deleteSysImportPaymentByImportReceivePaymentIds(Long[] importReceivePaymentIds) {
        return sysImportPaymentMapper.deleteSysImportPaymentByImportReceivePaymentIds(importReceivePaymentIds);
    }

    /**
     * 删除付款信息
     * 
     * @param importPaymentId 付款主键
     * @return 结果
     */
    @Override
    public int deleteSysImportPaymentByImportPaymentId(Long importPaymentId)
    {
        return sysImportPaymentMapper.deleteSysImportPaymentByImportPaymentId(importPaymentId);
    }

    /**
     * 修改状态
     *
     * @param sysImportPayment
     * @return
     */
    @Override
    public int updatePaymentStatus(SysImportPayment sysImportPayment) {
        return sysImportPaymentMapper.updateSysImportPayment(sysImportPayment);
    }
}
