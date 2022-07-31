package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysExportOrder;
import com.ydgj.oms.system.domain.SysImportOrder;

/**
 * 出口订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-17
 */
public interface SysExportOrderMapper 
{
    /**
     * 查询出口订单
     * 
     * @param exportOrderId 出口订单主键
     * @return 出口订单
     */
    public SysExportOrder selectSysExportOrderByExportOrderId(Long exportOrderId);

    /**
     * 查询出口订单列表
     * 
     * @param sysExportOrder 出口订单
     * @return 出口订单集合
     */
    public List<SysExportOrder> selectSysExportOrderList(SysExportOrder sysExportOrder);

    /**
     * 新增出口订单
     * 
     * @param sysExportOrder 出口订单
     * @return 结果
     */
    public int insertSysExportOrder(SysExportOrder sysExportOrder);

    /**
     * 修改出口订单
     * 
     * @param sysExportOrder 出口订单
     * @return 结果
     */
    public int updateSysExportOrder(SysExportOrder sysExportOrder);

    /**
     * 删除出口订单
     * 
     * @param exportOrderId 出口订单主键
     * @return 结果
     */
    public int deleteSysExportOrderByExportOrderId(Long exportOrderId);

    /**
     * 批量删除出口订单
     * 
     * @param exportOrderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExportOrderByExportOrderIds(Long[] exportOrderIds);

    List<SysExportOrder> selectSysExportOrderByMasterOrderAndBranchOrder(SysExportOrder exportOrder );
}
