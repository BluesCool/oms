package com.ydgj.oms.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 车队对象 sys_fleet
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public class SysFleet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "车队序号", cellType = Excel.ColumnType.NUMERIC)
    private Long fleetId;

    /** 车队名称 */
    @Excel(name = "车队名称")
    private String fleetName;

    /** 责任人 */
    @Excel(name = "责任人")
    private String responsibleMan;

    /** 责任人电话 */
    @Excel(name = "责任人电话")
    private String responsibleManPhone;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlateNumber;

    public void setFleetId(Long fleetId) 
    {
        this.fleetId = fleetId;
    }

    public Long getFleetId() 
    {
        return fleetId;
    }
    public void setFleetName(String fleetName) 
    {
        this.fleetName = fleetName;
    }

    public String getFleetName() 
    {
        return fleetName;
    }
    public void setResponsibleMan(String responsibleMan) 
    {
        this.responsibleMan = responsibleMan;
    }

    public String getResponsibleMan() 
    {
        return responsibleMan;
    }
    public void setResponsibleManPhone(String responsibleManPhone) 
    {
        this.responsibleManPhone = responsibleManPhone;
    }

    public String getResponsibleManPhone() 
    {
        return responsibleManPhone;
    }
    public void setLicensePlateNumber(String licensePlateNumber) 
    {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() 
    {
        return licensePlateNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fleetId", getFleetId())
            .append("fleetName", getFleetName())
            .append("responsibleMan", getResponsibleMan())
            .append("responsibleManPhone", getResponsibleManPhone())
            .append("licensePlateNumber", getLicensePlateNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
