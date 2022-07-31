package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysImportOrder;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
public interface SysImportOrderMapper 
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
     * @return 结果
     */
    public int insertSysImportOrder(SysImportOrder sysImportOrder);

    /**
     * 修改订单
     * 
     * @param sysImportOrder 订单
     * @return 结果
     */
    public int updateSysImportOrder(SysImportOrder sysImportOrder);

    /**
     * 删除订单
     * 
     * @param importOrderId 订单主键
     * @return 结果
     */
    public int deleteSysImportOrderByImportOrderId(Long importOrderId);

    /**
     * 批量删除订单
     * 
     * @param importOrderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysImportOrderByImportOrderIds(Long[] importOrderIds);

    /**
     * 获取进口订单 by 总单和分单
     * @param importOrder
     * @return
     */
    List<SysImportOrder> selectSysImportOrderByMasterOrderAndBranchOrder(SysImportOrder importOrder);
}
