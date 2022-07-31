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
 * 订单对象 sys_import_order
 *
 * @author ruoyi
 * @date 2022-07-03
 */
public class SysImportOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long importOrderId;

    /**
     * 订单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd",sort = 0)
    private Date orderDate;

    /**
     * 单位id
     */
    private Long orgId;

    @Excels({
            @Excel(name = "单位", targetAttr = "orgName",sort = 1),
    })
    private SysOrg org;

    /**
     * 总单
     */
    @Excel(name = "总单",sort = 2)
    private String masterOrder;

    /**
     * 分单
     */
    @Excel(name = "分单",sort = 3)
    private String branchOrder;

    /**
     * 数量
     */
    @Excel(name = "件数",sort = 4)
    private Long quantity;

    /**
     * 重量
     */
    @Excel(name = "重量",sort = 5)
    private BigDecimal weight;

    /**
     * 贸易方式id
     */
    private Long tradeWayId;



    @Excels({
            @Excel(name = "贸易方式", targetAttr = "tradeWayName",sort = 6),
    })
    private SysTradeWay tradeWay;


    /**
     * 手册编号
     */
    @Excel(name = "手册编号",sort = 7)
    private String manualNumber;
    /**
     * 货币id
     */
    private Long currencyId;

    @Excels({
            @Excel(name = "货币", targetAttr = "currencyName",sort = 8),
    })
    private SysCurrency currency;


    /**
     * 申报金额
     */
    @Excel(name = "申报金额",sort = 9)
    private BigDecimal amountDeclared;

    /**
     * c证品名
     */
    @Excel(name = "c证品名",sort = 10)
    private String certificateNameC;

    /**
     * 查验id
     */
    private Long checkId;

    @Excels({
            @Excel(name = "查验", targetAttr = "checkName",sort = 11),
    })
    private SysCheck check;

    /**
     * 税金
     */
    @Excel(name = "税金",sort = 12)
    private BigDecimal tax;

    /**
     * 海关编码
     */
    @Excel(name = "海关编码",sort = 13)
    private String customsCode;



    /**
     * 放行日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "放行日期", width = 30, dateFormat = "yyyy-MM-dd",sort = 14)
    private Date releaseDate;

    /**
     * 车队id
     */
    private Long fleetId;

    @Excels({
            @Excel(name = "车队", targetAttr = "fleetName",sort = 15),
    })
    private SysFleet fleet;

    /**
     * 报关行id
     */
    private Long customsBrokerId;

    @Excels({
            @Excel(name = "报关行", targetAttr = "customsBrokerName",sort = 16),
    })
    private SysCustomsBroker customsBroker;

    /**
     * 品名数量
     */
    @Excel(name = "品名数量",sort = 17)
    private Long productQuantity;

    /**
     * 编号
     */
    @Excel(name = "编号",sort = 18)
    private String serialNumber;

    /** 船名航次 */
    @Excel(name = "船名航次",sort = 19)
    private String flightNumber;

    /**
     * 分拨单号
     */
    @Excel(name = "分拨单号",sort = 20)
    private String dispatchNumber;

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

    public SysFleet getFleet() {
        return fleet;
    }

    public void setFleet(SysFleet fleet) {
        this.fleet = fleet;
    }

    public SysCustomsBroker getCustomsBroker() {
        return customsBroker;
    }

    public void setCustomsBroker(SysCustomsBroker customsBroker) {
        this.customsBroker = customsBroker;
    }

    public void setImportOrderId(Long importOrderId) {
        this.importOrderId = importOrderId;
    }

    public Long getImportOrderId() {
        return importOrderId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setMasterOrder(String masterOrder) {
        this.masterOrder = masterOrder;
    }

    public String getMasterOrder() {
        return masterOrder;
    }

    public void setBranchOrder(String branchOrder) {
        this.branchOrder = branchOrder;
    }

    public String getBranchOrder() {
        return branchOrder;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setTradeWayId(Long tradeWayId) {
        this.tradeWayId = tradeWayId;
    }

    public Long getTradeWayId() {
        return tradeWayId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setAmountDeclared(BigDecimal amountDeclared) {
        this.amountDeclared = amountDeclared;
    }

    public BigDecimal getAmountDeclared() {
        return amountDeclared;
    }

    public void setCertificateNameC(String certificateNameC) {
        this.certificateNameC = certificateNameC;
    }

    public String getCertificateNameC() {
        return certificateNameC;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setFleetId(Long fleetId) {
        this.fleetId = fleetId;
    }

    public Long getFleetId() {
        return fleetId;
    }

    public void setCustomsBrokerId(Long customsBrokerId) {
        this.customsBrokerId = customsBrokerId;
    }

    public Long getCustomsBrokerId() {
        return customsBrokerId;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDispatchNumber() {
        return dispatchNumber;
    }

    public void setDispatchNumber(String dispatchNumber) {
        this.dispatchNumber = dispatchNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("importOrderId", getImportOrderId())
                .append("orgId", getOrgId())
                .append("masterOrder", getMasterOrder())
                .append("branchOrder", getBranchOrder())
                .append("quantity", getQuantity())
                .append("weight", getWeight())
                .append("tradeWayId", getTradeWayId())
                .append("currencyId", getCurrencyId())
                .append("amountDeclared", getAmountDeclared())
                .append("certificateNameC", getCertificateNameC())
                .append("checkId", getCheckId())
                .append("tax", getTax())
                .append("customsCode", getCustomsCode())
                .append("releaseDate", getReleaseDate())
                .append("serialNumber", getSerialNumber())
                .append("fleetId", getFleetId())
                .append("customsBrokerId", getCustomsBrokerId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
