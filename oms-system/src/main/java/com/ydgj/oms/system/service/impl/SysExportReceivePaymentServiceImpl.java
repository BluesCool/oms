package com.ydgj.oms.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysExportReceivePaymentMapper;
import com.ydgj.oms.system.domain.SysExportReceivePayment;
import com.ydgj.oms.system.service.ISysExportReceivePaymentService;

/**
 * 出口收款Service业务层处理
 * 
 * @author guwei
 * @date 2022-07-17
 */
@Service
public class SysExportReceivePaymentServiceImpl implements ISysExportReceivePaymentService 
{
    @Autowired
    private SysExportReceivePaymentMapper sysExportReceivePaymentMapper;

    /**
     * 查询出口收款
     * 
     * @param exportReceivePaymentId 出口收款主键
     * @return 出口收款
     */
    @Override
    public SysExportReceivePayment selectSysExportReceivePaymentByExportReceivePaymentId(Long exportReceivePaymentId)
    {
        return sysExportReceivePaymentMapper.selectSysExportReceivePaymentByExportReceivePaymentId(exportReceivePaymentId);
    }

    /**
     * 查询出口收款列表
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 出口收款
     */
    @Override
    public List<SysExportReceivePayment> selectSysExportReceivePaymentList(SysExportReceivePayment sysExportReceivePayment)
    {
        return sysExportReceivePaymentMapper.selectSysExportReceivePaymentList(sysExportReceivePayment);
    }

    /**
     * 新增出口收款
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 结果
     */
    @Override
    public int insertSysExportReceivePayment(SysExportReceivePayment sysExportReceivePayment)
    {
        sysExportReceivePayment.setCreateTime(DateUtils.getNowDate());
        return sysExportReceivePaymentMapper.insertSysExportReceivePayment(sysExportReceivePayment);
    }

    /**
     * 修改出口收款
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 结果
     */
    @Override
    public int updateSysExportReceivePayment(SysExportReceivePayment sysExportReceivePayment)
    {
        sysExportReceivePayment.setUpdateTime(DateUtils.getNowDate());
        BigDecimal invoiceAmount =sysExportReceivePayment.getFreightFee() ==null? new BigDecimal(0.00) : sysExportReceivePayment.getFreightFee()
                .add(sysExportReceivePayment.getCustomsFee() ==null? new BigDecimal(0.00) : sysExportReceivePayment.getCustomsFee())
                .add(sysExportReceivePayment.getAdvancePaymentFee() ==null? new BigDecimal(0.00) : sysExportReceivePayment.getAdvancePaymentFee());

        sysExportReceivePayment.setInvoiceAmount(invoiceAmount);

        return sysExportReceivePaymentMapper.updateSysExportReceivePayment(sysExportReceivePayment);
    }

    /**
     * 批量删除出口收款
     * 
     * @param exportReceivePaymentIds 需要删除的出口收款主键
     * @return 结果
     */
    @Override
    public int deleteSysExportReceivePaymentByExportReceivePaymentIds(Long[] exportReceivePaymentIds)
    {
        return sysExportReceivePaymentMapper.deleteSysExportReceivePaymentByExportReceivePaymentIds(exportReceivePaymentIds);
    }

    /**
     * 删除出口收款信息
     * 
     * @param exportReceivePaymentId 出口收款主键
     * @return 结果
     */
    @Override
    public int deleteSysExportReceivePaymentByExportReceivePaymentId(Long exportReceivePaymentId)
    {
        return sysExportReceivePaymentMapper.deleteSysExportReceivePaymentByExportReceivePaymentId(exportReceivePaymentId);
    }

    /**
     * 查询ExportReceivePaymentIds
     * @param exportOrderIds
     * @return
     */
    @Override
    public List<Long> selectExportReceivePaymentIdsByExportOrderIds(Long[] exportOrderIds) {
        return sysExportReceivePaymentMapper.selectExportReceivePaymentIdsByExportOrderIds(exportOrderIds);
    }
}
