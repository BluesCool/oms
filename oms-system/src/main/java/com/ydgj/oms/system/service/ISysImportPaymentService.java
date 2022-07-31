package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysImportPayment;

/**
 * 付款Service接口
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
public interface ISysImportPaymentService 
{
    /**
     * 查询付款
     * 
     * @param importPaymentId 付款主键
     * @return 付款
     */
    public SysImportPayment selectSysImportPaymentByImportPaymentId(Long importPaymentId);

    /**
     * 查询付款列表
     * 
     * @param sysImportPayment 付款
     * @return 付款集合
     */
    public List<SysImportPayment> selectSysImportPaymentList(SysImportPayment sysImportPayment);

    /**
     * 新增付款
     * 
     * @param sysImportPayment 付款
     * @return 结果
     */
    public int insertSysImportPayment(SysImportPayment sysImportPayment);

    /**
     * 修改付款
     * 
     * @param sysImportPayment 付款
     * @return 结果
     */
    public int updateSysImportPayment(SysImportPayment sysImportPayment);

    /**
     * 批量删除付款
     * 
     * @param importPaymentIds 需要删除的付款主键集合
     * @return 结果
     */
    public int deleteSysImportPaymentByImportPaymentIds(Long[] importPaymentIds);

    /**
     * 删除付款信息
     * 
     * @param importPaymentId 付款主键
     * @return 结果
     */
    public int deleteSysImportPaymentByImportPaymentId(Long importPaymentId);

    int deleteSysImportPaymentByImportReceivePaymentIds(Long[] importReceivePaymentIds);

    int updatePaymentStatus(SysImportPayment sysImportPayment);
}
