package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysExportPayment;

/**
 * 出口付款Mapper接口
 * 
 * @author guwei
 * @date 2022-07-17
 */
public interface SysExportPaymentMapper 
{
    /**
     * 查询出口付款
     * 
     * @param exportPaymentId 出口付款主键
     * @return 出口付款
     */
    public SysExportPayment selectSysExportPaymentByExportPaymentId(Long exportPaymentId);

    /**
     * 查询出口付款列表
     * 
     * @param sysExportPayment 出口付款
     * @return 出口付款集合
     */
    public List<SysExportPayment> selectSysExportPaymentList(SysExportPayment sysExportPayment);

    /**
     * 新增出口付款
     * 
     * @param sysExportPayment 出口付款
     * @return 结果
     */
    public int insertSysExportPayment(SysExportPayment sysExportPayment);

    /**
     * 修改出口付款
     * 
     * @param sysExportPayment 出口付款
     * @return 结果
     */
    public int updateSysExportPayment(SysExportPayment sysExportPayment);

    /**
     * 删除出口付款
     * 
     * @param exportPaymentId 出口付款主键
     * @return 结果
     */
    public int deleteSysExportPaymentByExportPaymentId(Long exportPaymentId);

    /**
     * 批量删除出口付款
     * 
     * @param exportPaymentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExportPaymentByExportPaymentIds(Long[] exportPaymentIds);

    int deleteSysExportPaymentByExportReceivePaymentIds(Long[] exportReceivePaymentIds);
}
