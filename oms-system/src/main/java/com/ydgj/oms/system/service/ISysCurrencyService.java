package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysCurrency;

/**
 * 货币Service接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface ISysCurrencyService 
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
     * 批量删除货币
     * 
     * @param currencyIds 需要删除的货币主键集合
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds);

    /**
     * 删除货币信息
     * 
     * @param currencyId 货币主键
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 获取所有货币
     *
     * @return 结果
     */
    List<SysCurrency> selectCurrencyAll();
}
