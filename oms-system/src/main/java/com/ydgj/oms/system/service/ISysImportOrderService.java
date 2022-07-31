package com.ydgj.oms.system.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.ydgj.oms.system.domain.SysImportOrder;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
public interface ISysImportOrderService 
{
    /**
     * 查询订单
     * 
     * @param importOrderId 订单主键
     * @return 订单
     */
    public SysImportOrder selectSysImportOrderByImportOrderId(Long importOrderId);

    /**
     * 查询订单列表
     * 
     * @param sysImportOrder 订单
     * @return 订单集合
     */
    public List<SysImportOrder> selectSysImportOrderList(SysImportOrder sysImportOrder);

    /**
     * 新增订单
     *
     * @param sysImportOrder 订单
     * @param operName
     * @return 结果
     */
    public int insertSysImportOrder(SysImportOrder sysImportOrder, String operName);

    /**
     * 修改订单
     * 
     * @param sysImportOrder 订单
     * @return 结果
     */
    public int updateSysImportOrder(SysImportOrder sysImportOrder);

    /**
     * 批量删除订单
     * 
     * @param importOrderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteSysImportOrderByImportOrderIds(Long[] importOrderIds);

    /**
     * 删除订单信息
     * 
     * @param importOrderId 订单主键
     * @return 结果
     */
    public int deleteSysImportOrderByImportOrderId(Long importOrderId);

    String importOrder(InputStream is, String operName, boolean updateSupport) throws IOException;
}
