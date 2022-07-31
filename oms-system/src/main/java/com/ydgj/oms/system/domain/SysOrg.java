package com.ydgj.oms.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 组织机构对象 sys_org
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public class SysOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "单位序号", cellType = Excel.ColumnType.NUMERIC)
    private Long orgId;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String orgName;

    /** 单位简称 */
    @Excel(name = "单位简称")
    private String orgShort;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 邮编 */
    @Excel(name = "邮编")
    private String postCode;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 法人 */
    @Excel(name = "法人")
    private String corporation;

    /** 法人电话 */
    @Excel(name = "法人电话")
    private String corporationPhone;

    /** 法人身份证 */
    @Excel(name = "法人身份证")
    private String corporationIdCard;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String registeredAddress;

    /** 省 */
    @Excel(name = "省")
    private Long province;

    /** 市 */
    @Excel(name = "市")
    private Long city;

    /** 县 */
    @Excel(name = "县")
    private Long county;

    public void setOrgId(Long orgId) 
    {
        this.orgId = orgId;
    }

    public Long getOrgId() 
    {
        return orgId;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setOrgShort(String orgShort) 
    {
        this.orgShort = orgShort;
    }

    public String getOrgShort() 
    {
        return orgShort;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPostCode(String postCode) 
    {
        this.postCode = postCode;
    }

    public String getPostCode() 
    {
        return postCode;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCorporation(String corporation) 
    {
        this.corporation = corporation;
    }

    public String getCorporation() 
    {
        return corporation;
    }
    public void setCorporationPhone(String corporationPhone) 
    {
        this.corporationPhone = corporationPhone;
    }

    public String getCorporationPhone() 
    {
        return corporationPhone;
    }
    public void setCorporationIdCard(String corporationIdCard) 
    {
        this.corporationIdCard = corporationIdCard;
    }

    public String getCorporationIdCard() 
    {
        return corporationIdCard;
    }
    public void setRegisteredAddress(String registeredAddress) 
    {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredAddress() 
    {
        return registeredAddress;
    }
    public void setProvince(Long province) 
    {
        this.province = province;
    }

    public Long getProvince() 
    {
        return province;
    }
    public void setCity(Long city) 
    {
        this.city = city;
    }

    public Long getCity() 
    {
        return city;
    }
    public void setCounty(Long county) 
    {
        this.county = county;
    }

    public Long getCounty() 
    {
        return county;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orgId", getOrgId())
            .append("orgName", getOrgName())
            .append("orgShort", getOrgShort())
            .append("address", getAddress())
            .append("postCode", getPostCode())
            .append("telephone", getTelephone())
            .append("description", getDescription())
            .append("corporation", getCorporation())
            .append("corporationPhone", getCorporationPhone())
            .append("corporationIdCard", getCorporationIdCard())
            .append("registeredAddress", getRegisteredAddress())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
