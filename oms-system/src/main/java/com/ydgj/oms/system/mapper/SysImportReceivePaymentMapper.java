package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysImportReceivePayment;

/**
 * 收款Mapper接口
 * 
 * @author gw
 * @date 2022-07-03
 */
public interface SysImportReceivePaymentMapper 
{
    /**
     * 查询收款
     * 
     * @param importReceivePaymentId 收款主键
     * @return 收款
     */
    public SysImportReceivePayment selectSysImportReceivePaymentByImportReceivePaymentId(Long importReceivePaymentId);

    /**
     * 查询收款列表
     * 
     * @param sysImportReceivePayment 收款
     * @return 收款集合
     */
    public List<SysImportReceivePayment> selectSysImportReceivePaymentList(SysImportReceivePayment sysImportReceivePayment);

    /**
     * 新增收款
     * 
     * @param sysImportReceivePayment 收款
     * @return 结果
     */
    public int insertSysImportReceivePayment(SysImportReceivePayment sysImportReceivePayment);

    /**
     * 修改收款
     * 
     * @param sysImportReceivePayment 收款
     * @return 结果
     */
    public int updateSysImportReceivePayment(SysImportReceivePayment sysImportReceivePayment);

    /**
     * 删除收款
     * 
     * @param importReceivePaymentId 收款主键
     * @return 结果
     */
    public int deleteSysImportReceivePaymentByImportReceivePaymentId(Long importReceivePaymentId);

    /**
     * 批量删除收款
     * 
     * @param importReceivePaymentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysImportReceivePaymentByImportReceivePaymentIds(Long[] importReceivePaymentIds);

    /**
     * 获取收款信息主键
     * @param importOrderIds
     * @return
     */
    List<Long> selectIdsByImportOderIds(Long[] importOrderIds);

    /**
     * 删除收款信息
     * @param importOrderIds
     * @return
     */
    int deleteSysImportReceivePaymentByImportOrders(Long[] importOrderIds);
}
