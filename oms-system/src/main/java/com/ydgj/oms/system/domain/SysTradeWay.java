package com.ydgj.oms.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 贸易方式对象 sys_trade_way
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public class SysTradeWay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "贸易方式序号", cellType = Excel.ColumnType.NUMERIC)
    private Long tradeWayId;

    /** 贸易方式名称 */
    @Excel(name = "贸易方式名称")
    private String tradeWayName;

    public void setTradeWayId(Long tradeWayId) 
    {
        this.tradeWayId = tradeWayId;
    }

    public Long getTradeWayId() 
    {
        return tradeWayId;
    }
    public void setTradeWayName(String tradeWayName) 
    {
        this.tradeWayName = tradeWayName;
    }

    public String getTradeWayName() 
    {
        return tradeWayName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tradeWayId", getTradeWayId())
            .append("tradeWayName", getTradeWayName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
