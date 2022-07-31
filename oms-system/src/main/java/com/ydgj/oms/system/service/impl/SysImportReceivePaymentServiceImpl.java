package com.ydgj.oms.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysImportReceivePaymentMapper;
import com.ydgj.oms.system.domain.SysImportReceivePayment;
import com.ydgj.oms.system.service.ISysImportReceivePaymentService;

/**
 * 收款Service业务层处理
 *
 * @author gw
 * @date 2022-07-03
 */
@Service
public class SysImportReceivePaymentServiceImpl implements ISysImportReceivePaymentService {
    @Autowired
    private SysImportReceivePaymentMapper sysImportReceivePaymentMapper;

    /**
     * 查询收款
     *
     * @param importReceivePaymentId 收款主键
     * @return 收款
     */
    @Override
    public SysImportReceivePayment selectSysImportReceivePaymentByImportReceivePaymentId(Long importReceivePaymentId) {
        return sysImportReceivePaymentMapper.selectSysImportReceivePaymentByImportReceivePaymentId(importReceivePaymentId);
    }

    /**
     * 查询收款列表
     *
     * @param sysImportReceivePayment 收款
     * @return 收款
     */
    @Override
    public List<SysImportReceivePayment> selectSysImportReceivePaymentList(SysImportReceivePayment sysImportReceivePayment) {
        return sysImportReceivePaymentMapper.selectSysImportReceivePaymentList(sysImportReceivePayment);
    }

    /**
     * 新增收款
     *
     * @param sysImportReceivePayment 收款
     * @return 结果
     */
    @Override
    public int insertSysImportReceivePayment(SysImportReceivePayment sysImportReceivePayment) {
        sysImportReceivePayment.setCreateTime(DateUtils.getNowDate());
        return sysImportReceivePaymentMapper.insertSysImportReceivePayment(sysImportReceivePayment);
    }

    /**
     * 修改收款
     *
     * @param sysImportReceivePayment 收款
     * @return 结果
     */
    @Override
    public int updateSysImportReceivePayment(SysImportReceivePayment sysImportReceivePayment) {

        sysImportReceivePayment.setUpdateTime(DateUtils.getNowDate());
//        BigDecimal invoiceAmount = sysImportReceivePayment.getAgencyCustomsFee()== null ? new BigDecimal(0.00) : sysImportReceivePayment.getAgencyCustomsFee()
//                .add(sysImportReceivePayment.getAgentInspectionFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getAgentInspectionFee())
//                .add(sysImportReceivePayment.getReplacementFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getReplacementFee())
//                .add(sysImportReceivePayment.getCustomsClearanceFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getCustomsClearanceFee())
//                .add(sysImportReceivePayment.getStorageFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getStorageFee())
//                .add(sysImportReceivePayment.getAgency3cFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getAgency3cFee())
//                .add(sysImportReceivePayment.getConsolidationFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getConsolidationFee())
//                .add(sysImportReceivePayment.getCustomsInspectionFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getCustomsInspectionFee())
//                .add(sysImportReceivePayment.getInspectionQuarantineFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getInspectionQuarantineFee())
//                .add(sysImportReceivePayment.getInlandDeliverFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getInlandDeliverFee())
//                .add(sysImportReceivePayment.getThreeInspectionFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getThreeInspectionFee())
//                .add(sysImportReceivePayment.getHandlingFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getHandlingFee());
//        sysImportReceivePayment.setInvoiceAmount(invoiceAmount);
//
//        BigDecimal totalPayment = invoiceAmount.add(sysImportReceivePayment.getAdvanceFee() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getAdvanceFee())
//                .add(sysImportReceivePayment.getVat() == null ? new BigDecimal(0.00) : sysImportReceivePayment.getVat());
//        sysImportReceivePayment.setTotalPayment(totalPayment);
        return sysImportReceivePaymentMapper.updateSysImportReceivePayment(sysImportReceivePayment);
    }

    /**
     * 批量删除收款
     *
     * @param importReceivePaymentIds 需要删除的收款主键
     * @return 结果
     */
    @Override
    public int deleteSysImportReceivePaymentByImportReceivePaymentIds(Long[] importReceivePaymentIds) {
        return sysImportReceivePaymentMapper.deleteSysImportReceivePaymentByImportReceivePaymentIds(importReceivePaymentIds);
    }

    /**
     * 删除收款信息
     *
     * @param importReceivePaymentId 收款主键
     * @return 结果
     */
    @Override
    public int deleteSysImportReceivePaymentByImportReceivePaymentId(Long importReceivePaymentId) {
        return sysImportReceivePaymentMapper.deleteSysImportReceivePaymentByImportReceivePaymentId(importReceivePaymentId);
    }

    /**
     * 修改收款状态
     *
     * @param sysImportReceivePayment
     * @return
     */
    @Override
    public int updateReceiveStatus(SysImportReceivePayment sysImportReceivePayment) {
        return sysImportReceivePaymentMapper.updateSysImportReceivePayment(sysImportReceivePayment);
    }

    /**
     * 获取收款信息主键
     *
     * @param importOrderIds
     * @return
     */
    @Override
    public List<Long> selectIdsByImportOderIds(Long[] importOrderIds) {
        return sysImportReceivePaymentMapper.selectIdsByImportOderIds(importOrderIds);
    }

    /**
     * 删除收款信息
     *
     * @param importOrderIds
     * @return
     */
    @Override
    public int deleteSysImportReceivePaymentByImportOrders(Long[] importOrderIds) {
        return sysImportReceivePaymentMapper.deleteSysImportReceivePaymentByImportOrders(importOrderIds);
    }
}
