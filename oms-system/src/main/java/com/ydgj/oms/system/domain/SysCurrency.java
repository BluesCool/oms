package com.ydgj.oms.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 货币对象 sys_currency
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public class SysCurrency extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "货币序号", cellType = Excel.ColumnType.NUMERIC)
    private Long currencyId;

    /** 货币名称 */
    @Excel(name = "货币名称")
    private String currencyName;

    /** 货币代码 */
    @Excel(name = "货币代码")
    private String currencyCode;

    public void setCurrencyId(Long currencyId) 
    {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() 
    {
        return currencyId;
    }
    public void setCurrencyName(String currencyName) 
    {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() 
    {
        return currencyName;
    }
    public void setCurrencyCode(String currencyCode) 
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() 
    {
        return currencyCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("currencyId", getCurrencyId())
            .append("currencyName", getCurrencyName())
            .append("currencyCode", getCurrencyCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
