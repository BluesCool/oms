package com.ydgj.oms.system.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.ydgj.oms.system.domain.SysExportOrder;

/**
 * 出口订单Service接口
 * 
 * @author ruoyi
 * @date 2022-07-17
 */
public interface ISysExportOrderService 
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
     * @param operName
     * @return 结果
     */
    public int insertSysExportOrder(SysExportOrder sysExportOrder, String operName);

    /**
     * 修改出口订单
     * 
     * @param sysExportOrder 出口订单
     * @return 结果
     */
    public int updateSysExportOrder(SysExportOrder sysExportOrder);

    /**
     * 批量删除出口订单
     * 
     * @param exportOrderIds 需要删除的出口订单主键集合
     * @return 结果
     */
    public int deleteSysExportOrderByExportOrderIds(Long[] exportOrderIds);

    /**
     * 删除出口订单信息
     * 
     * @param exportOrderId 出口订单主键
     * @return 结果
     */
    public int deleteSysExportOrderByExportOrderId(Long exportOrderId);

    /**
     * 导入订单
     *
     * @param inputStream
     * @param operName
     * @param updateSupport
     * @return
     */
    String importOrder(InputStream inputStream, String operName, boolean updateSupport) throws IOException;
}
