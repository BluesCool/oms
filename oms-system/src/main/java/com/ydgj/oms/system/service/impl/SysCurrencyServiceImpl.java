package com.ydgj.oms.system.service.impl;

import java.util.List;
import com.ydgj.oms.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.mapper.SysCurrencyMapper;
import com.ydgj.oms.system.domain.SysCurrency;
import com.ydgj.oms.system.service.ISysCurrencyService;

/**
 * 货币Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
@Service
public class SysCurrencyServiceImpl implements ISysCurrencyService 
{
    @Autowired
    private SysCurrencyMapper sysCurrencyMapper;

    /**
     * 查询货币
     * 
     * @param currencyId 货币主键
     * @return 货币
     */
    @Override
    public SysCurrency selectSysCurrencyByCurrencyId(Long currencyId)
    {
        return sysCurrencyMapper.selectSysCurrencyByCurrencyId(currencyId);
    }

    /**
     * 查询货币列表
     * 
     * @param sysCurrency 货币
     * @return 货币
     */
    @Override
    public List<SysCurrency> selectSysCurrencyList(SysCurrency sysCurrency)
    {
        return sysCurrencyMapper.selectSysCurrencyList(sysCurrency);
    }

    /**
     * 新增货币
     * 
     * @param sysCurrency 货币
     * @return 结果
     */
    @Override
    public int insertSysCurrency(SysCurrency sysCurrency)
    {
        sysCurrency.setCreateTime(DateUtils.getNowDate());
        return sysCurrencyMapper.insertSysCurrency(sysCurrency);
    }

    /**
     * 修改货币
     * 
     * @param sysCurrency 货币
     * @return 结果
     */
    @Override
    public int updateSysCurrency(SysCurrency sysCurrency)
    {
        sysCurrency.setUpdateTime(DateUtils.getNowDate());
        return sysCurrencyMapper.updateSysCurrency(sysCurrency);
    }

    /**
     * 批量删除货币
     * 
     * @param currencyIds 需要删除的货币主键
     * @return 结果
     */
    @Override
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds)
    {
        return sysCurrencyMapper.deleteSysCurrencyByCurrencyIds(currencyIds);
    }

    /**
     * 删除货币信息
     * 
     * @param currencyId 货币主键
     * @return 结果
     */
    @Override
    public int deleteSysCurrencyByCurrencyId(Long currencyId)
    {
        return sysCurrencyMapper.deleteSysCurrencyByCurrencyId(currencyId);
    }

    /**
     * 获取所有货币
     *      *
     * @return 结果
     */
    @Override
    public List<SysCurrency> selectCurrencyAll() {
        return sysCurrencyMapper.selectCurrencyAll();
    }
}
