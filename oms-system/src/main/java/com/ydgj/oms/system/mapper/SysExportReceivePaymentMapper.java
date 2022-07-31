package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysExportReceivePayment;

/**
 * 出口收款Mapper接口
 * 
 * @author guwei
 * @date 2022-07-17
 */
public interface SysExportReceivePaymentMapper 
{
    /**
     * 查询出口收款
     * 
     * @param exportReceivePaymentId 出口收款主键
     * @return 出口收款
     */
    public SysExportReceivePayment selectSysExportReceivePaymentByExportReceivePaymentId(Long exportReceivePaymentId);

    /**
     * 查询出口收款列表
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 出口收款集合
     */
    public List<SysExportReceivePayment> selectSysExportReceivePaymentList(SysExportReceivePayment sysExportReceivePayment);

    /**
     * 新增出口收款
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 结果
     */
    public int insertSysExportReceivePayment(SysExportReceivePayment sysExportReceivePayment);

    /**
     * 修改出口收款
     * 
     * @param sysExportReceivePayment 出口收款
     * @return 结果
     */
    public int updateSysExportReceivePayment(SysExportReceivePayment sysExportReceivePayment);

    /**
     * 删除出口收款
     * 
     * @param exportReceivePaymentId 出口收款主键
     * @return 结果
     */
    public int deleteSysExportReceivePaymentByExportReceivePaymentId(Long exportReceivePaymentId);

    /**
     * 批量删除出口收款
     * 
     * @param exportReceivePaymentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExportReceivePaymentByExportReceivePaymentIds(Long[] exportReceivePaymentIds);

    /**
     * 查询ExportReceivePaymentIds
     * @param exportOrderIds
     * @return
     */
    List<Long> selectExportReceivePaymentIdsByExportOrderIds(Long[] exportOrderIds);
}
