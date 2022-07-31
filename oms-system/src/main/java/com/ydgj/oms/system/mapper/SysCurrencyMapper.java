package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysCurrency;

/**
 * 货币Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface SysCurrencyMapper 
{
    /**
     * 查询货币
     * 
     * @param currencyId 货币主键
     * @return 货币
     */
    public SysCurrency selectSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 查询货币列表
     * 
     * @param sysCurrency 货币
     * @return 货币集合
     */
    public List<SysCurrency> selectSysCurrencyList(SysCurrency sysCurrency);

    /**
     * 新增货币
     * 
     * @param sysCurrency 货币
     * @return 结果
     */
    public int insertSysCurrency(SysCurrency sysCurrency);

    /**
     * 修改货币
     * 
     * @param sysCurrency 货币
     * @return 结果
     */
    public int updateSysCurrency(SysCurrency sysCurrency);

    /**
     * 删除货币
     * 
     * @param currencyId 货币主键
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 批量删除货币
     * 
     * @param currencyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds);

    /**
     * 获取所有货币
     *      *
     * @return 结果
     */
    List<SysCurrency> selectCurrencyAll();

    /**
     * 获取货币by name
     * @param currencyCode
     * @return
     */
    SysCurrency selectSysCurrencyByCurrencyName(String currencyCode);
}
