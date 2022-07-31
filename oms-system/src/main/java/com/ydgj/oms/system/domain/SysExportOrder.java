package com.ydgj.oms.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ydgj.oms.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 出口订单对象 sys_export_order
 * 
 * @author ruoyi
 * @date 2022-07-17
 */
public class SysExportOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出口订单ID */
    private Long exportOrderId;

    /** 船名航次 */
    @Excel(name = "船名航次")
    private String flightNumber;

    /** 单位id */
    private Long orgId;

    @Excels({
            @Excel(name = "单位", targetAttr = "orgName"),
    })
    private SysOrg org;

    /** 分单 */
    @Excel(name = "分单")
    private String branchOrder;

    /** 数量 */
    @Excel(name = "件数")
    private Long quantity;

    /**
     * 品名数量
     */
    @Excel(name = "品名数量")
    private Long productQuantity;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 贸易方式id */
    private Long tradeWayId;

    @Excels({
            @Excel(name = "贸易方式", targetAttr = "tradeWayName"),
    })
    private SysTradeWay tradeWay;

    /**
     * 手册编号
     */
    @Excel(name = "手册编号")
    private String manualNumber;

    /** 货币id */
    private Long currencyId;

    @Excels({
            @Excel(name = "货币", targetAttr = "currencyName"),
    })
    private SysCurrency currency;

    /** 申报金额 */
    @Excel(name = "申报金额")
    private BigDecimal amountDeclared;

    /** 查验id */
    private Long checkId;


    @Excels({
            @Excel(name = "查验", targetAttr = "checkName"),
    })
    private SysCheck check;

    /** 海关编码 */
    @Excel(name = "海关编码")
    private String customsCode;

    /**
     * 订单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 放行日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "放行日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 编号 */
    @Excel(name = "编号")
    private String serialNumber;

    /** 报关行id */
    private Long customsBrokerId;


    @Excels({
            @Excel(name = "报关行", targetAttr = "customsBrokerName"),
    })
    private SysCustomsBroker customsBroker;

    public void setExportOrderId(Long exportOrderId) 
    {
        this.exportOrderId = exportOrderId;
    }

    public Long getExportOrderId() 
    {
        return exportOrderId;
    }
    public void setFlightNumber(String flightNumber) 
    {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() 
    {
        return flightNumber;
    }
    public void setOrgId(Long orgId) 
    {
        this.orgId = orgId;
    }

    public Long getOrgId() 
    {
        return orgId;
    }
    public void setBranchOrder(String branchOrder) 
    {
        this.branchOrder = branchOrder;
    }

    public String getBranchOrder() 
    {
        return branchOrder;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setTradeWayId(Long tradeWayId) 
    {
        this.tradeWayId = tradeWayId;
    }

    public Long getTradeWayId() 
    {
        return tradeWayId;
    }
    public void setCurrencyId(Long currencyId) 
    {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() 
    {
        return currencyId;
    }
    public void setAmountDeclared(BigDecimal amountDeclared) 
    {
        this.amountDeclared = amountDeclared;
    }

    public BigDecimal getAmountDeclared() 
    {
        return amountDeclared;
    }
    public void setCheckId(Long checkId) 
    {
        this.checkId = checkId;
    }

    public Long getCheckId() 
    {
        return checkId;
    }
    public void setCustomsCode(String customsCode) 
    {
        this.customsCode = customsCode;
    }

    public String getCustomsCode() 
    {
        return customsCode;
    }
    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() 
    {
        return releaseDate;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setCustomsBrokerId(Long customsBrokerId) 
    {
        this.customsBrokerId = customsBrokerId;
    }

    public Long getCustomsBrokerId() 
    {
        return customsBrokerId;
    }

    public SysOrg getOrg() {
        return org;
    }

    public void setOrg(SysOrg org) {
        this.org = org;
    }

    public SysTradeWay getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(SysTradeWay tradeWay) {
        this.tradeWay = tradeWay;
    }

    public SysCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(SysCurrency currency) {
        this.currency = currency;
    }

    public SysCheck getCheck() {
        return check;
    }

    public void setCheck(SysCheck check) {
        this.check = check;
    }

    public SysCustomsBroker getCustomsBroker() {
        return customsBroker;
    }

    public void setCustomsBroker(SysCustomsBroker customsBroker) {
        this.customsBroker = customsBroker;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getManualNumber() {
        return manualNumber;
    }

    public void setManualNumber(String manualNumber) {
        this.manualNumber = manualNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("exportOrderId", getExportOrderId())
            .append("flightNumber", getFlightNumber())
            .append("orgId", getOrgId())
            .append("branchOrder", getBranchOrder())
            .append("quantity", getQuantity())
            .append("weight", getWeight())
            .append("tradeWayId", getTradeWayId())
            .append("currencyId", getCurrencyId())
            .append("amountDeclared", getAmountDeclared())
            .append("checkId", getCheckId())
            .append("customsCode", getCustomsCode())
            .append("releaseDate", getReleaseDate())
            .append("serialNumber", getSerialNumber())
            .append("customsBrokerId", getCustomsBrokerId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
