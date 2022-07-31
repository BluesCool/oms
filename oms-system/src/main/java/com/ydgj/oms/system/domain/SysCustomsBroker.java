package com.ydgj.oms.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 报关行对象 sys_customs_broker
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public class SysCustomsBroker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "报关行序号", cellType = Excel.ColumnType.NUMERIC)
    private Long customsBrokerId;

    /** 报关行名称 */
    @Excel(name = "报关行名称")
    private String customsBrokerName;

    public void setCustomsBrokerId(Long customsBrokerId) 
    {
        this.customsBrokerId = customsBrokerId;
    }

    public Long getCustomsBrokerId() 
    {
        return customsBrokerId;
    }
    public void setCustomsBrokerName(String customsBrokerName) 
    {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsBrokerName() 
    {
        return customsBrokerName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customsBrokerId", getCustomsBrokerId())
            .append("customsBrokerName", getCustomsBrokerName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
