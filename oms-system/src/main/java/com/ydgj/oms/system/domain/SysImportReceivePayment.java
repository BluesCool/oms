package com.ydgj.oms.system.domain;

import java.math.BigDecimal;

import com.ydgj.oms.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 收款对象 sys_import_receive_payment
 * 
 * @author gw
 * @date 2022-07-03
 */
public class SysImportReceivePayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
//    @Excel(name = "收款序号", cellType = Excel.ColumnType.NUMERIC)
    private Long importReceivePaymentId;

    /** 订单id */
//    @Excel(name = "订单id")
    private Long importOrderId;

    @Excels({
            @Excel(name = "单位", targetAttr = "orgName"),
    })
    private SysOrg org;

    @Excels({
            @Excel(name = "总单", targetAttr = "masterOrder"),
            @Excel(name = "分单", targetAttr = "branchOrder"),
            @Excel(name = "件数", targetAttr = "quantity"),
            @Excel(name = "品名数量", targetAttr = "productQuantity"),
            @Excel(name = "重量", targetAttr = "weight"),
            @Excel(name = "手册编号", targetAttr = "manualNumber"),
            @Excel(name = "申报金额", targetAttr = "amountDeclared"),
            @Excel(name = "c证品名", targetAttr = "certificateNameC"),
            @Excel(name = "税金", targetAttr = "tax"),
            @Excel(name = "海关编码", targetAttr = "customsCode"),
            @Excel( name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd", targetAttr = "orderDate"),
            @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd", targetAttr = "releaseDate"),
            @Excel(name = "编号", targetAttr = "serialNumber"),

    })
    private SysImportOrder importOrder;

    @Excels({
            @Excel(name = "贸易方式", targetAttr = "tradeWayName"),
    })
    private SysTradeWay tradeWay;


    private SysCurrency currency;

    @Excels({
            @Excel(name = "报关行", targetAttr = "customsBrokerName"),
    })
    private SysCustomsBroker customsBroker;

    @Excels({
            @Excel(name = "车队", targetAttr = "fleetName"),
    })
    private SysFleet fleet;



    @Excels({
            @Excel(name = "查验", targetAttr = "checkName"),
    })
    private SysCheck check;

    public SysCheck getCheck() {
        return check;
    }

    public void setCheck(SysCheck check) {
        this.check = check;
    }

    public SysCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(SysCurrency currency) {
        this.currency = currency;
    }

    public SysCustomsBroker getCustomsBroker() {
        return customsBroker;
    }

    public void setCustomsBroker(SysCustomsBroker customsBroker) {
        this.customsBroker = customsBroker;
    }

    public SysFleet getFleet() {
        return fleet;
    }

    public void setFleet(SysFleet fleet) {
        this.fleet = fleet;
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

    /** 代理报关费 */
    @Excel(name = "代理报关费")
    private BigDecimal agencyCustomsFee;

    /** 代理商检费 */
    @Excel(name = "代理商检费")
    private BigDecimal agentInspectionFee;

    /** 换单费 */
    @Excel(name = "换单费")
    private BigDecimal replacementFee;

    /** 通关费 */
    @Excel(name = "通关费")
    private BigDecimal customsClearanceFee;

    /** 仓储费 */
    @Excel(name = "仓储费")
    private BigDecimal storageFee;

    /** 代办3C */
    @Excel(name = "代办3C")
    private BigDecimal agency3cFee;

    /** 拼单费 */
    @Excel(name = "拼单费")
    private BigDecimal consolidationFee;

    /** 海关查验费 */
    @Excel(name = "海关查验费")
    private BigDecimal customsInspectionFee;

    /** 检验检疫费 */
    @Excel(name = "检验检疫费")
    private BigDecimal inspectionQuarantineFee;

    /** 内陆运费 */
    @Excel(name = "内陆运费")
    private BigDecimal inlandDeliverFee;

    /** 三检费 */
    @Excel(name = "三检费")
    private BigDecimal threeInspectionFee;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal handlingFee;

    /** 开票金额 */
    @Excel(name = "开票金额")
    private BigDecimal invoiceAmount;

    /** 垫付关税 */
    @Excel(name = "关税")
    private BigDecimal advanceFee;

    /**
     * 增值税
     */
    @Excel(name = "增值税")
    private BigDecimal vat;

    /** 收款总额 */
    @Excel(name = "收款总额")
    private BigDecimal totalPayment;

    /** 收款状态（0未收 1已收） */
    @Excel(name = "收款状态", readConverterExp = "0=未收,1=已收")
    private String status;


    public void setImportReceivePaymentId(Long importReceivePaymentId) 
    {
        this.importReceivePaymentId = importReceivePaymentId;
    }

    public Long getImportReceivePaymentId() 
    {
        return importReceivePaymentId;
    }
    public void setImportOrderId(Long importOrderId) 
    {
        this.importOrderId = importOrderId;
    }

    public Long getImportOrderId() 
    {
        return importOrderId;
    }
    public void setAgencyCustomsFee(BigDecimal agencyCustomsFee) 
    {
        this.agencyCustomsFee = agencyCustomsFee;
    }

    public BigDecimal getAgencyCustomsFee() 
    {
        return agencyCustomsFee;
    }
    public void setAgentInspectionFee(BigDecimal agentInspectionFee) 
    {
        this.agentInspectionFee = agentInspectionFee;
    }

    public BigDecimal getAgentInspectionFee() 
    {
        return agentInspectionFee;
    }
    public void setReplacementFee(BigDecimal replacementFee) 
    {
        this.replacementFee = replacementFee;
    }

    public BigDecimal getReplacementFee() 
    {
        return replacementFee;
    }
    public void setCustomsClearanceFee(BigDecimal customsClearanceFee) 
    {
        this.customsClearanceFee = customsClearanceFee;
    }

    public BigDecimal getCustomsClearanceFee() 
    {
        return customsClearanceFee;
    }
    public void setStorageFee(BigDecimal storageFee) 
    {
        this.storageFee = storageFee;
    }

    public BigDecimal getStorageFee() 
    {
        return storageFee;
    }
    public void setAgency3cFee(BigDecimal agency3cFee) 
    {
        this.agency3cFee = agency3cFee;
    }

    public BigDecimal getAgency3cFee() 
    {
        return agency3cFee;
    }
    public void setConsolidationFee(BigDecimal consolidationFee) 
    {
        this.consolidationFee = consolidationFee;
    }

    public BigDecimal getConsolidationFee() 
    {
        return consolidationFee;
    }
    public void setCustomsInspectionFee(BigDecimal customsInspectionFee) 
    {
        this.customsInspectionFee = customsInspectionFee;
    }

    public BigDecimal getCustomsInspectionFee() 
    {
        return customsInspectionFee;
    }
    public void setInspectionQuarantineFee(BigDecimal inspectionQuarantineFee) 
    {
        this.inspectionQuarantineFee = inspectionQuarantineFee;
    }

    public BigDecimal getInspectionQuarantineFee() 
    {
        return inspectionQuarantineFee;
    }
    public void setInlandDeliverFee(BigDecimal inlandDeliverFee) 
    {
        this.inlandDeliverFee = inlandDeliverFee;
    }

    public BigDecimal getInlandDeliverFee() 
    {
        return inlandDeliverFee;
    }
    public void setInvoiceAmount(BigDecimal invoiceAmount) 
    {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getInvoiceAmount() 
    {
        return invoiceAmount;
    }
    public void setAdvanceFee(BigDecimal advanceFee) 
    {
        this.advanceFee = advanceFee;
    }

    public BigDecimal getAdvanceFee() 
    {
        return advanceFee;
    }
    public void setTotalPayment(BigDecimal totalPayment) 
    {
        this.totalPayment = totalPayment;
    }

    public BigDecimal getTotalPayment() 
    {
        return totalPayment;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public SysImportOrder getImportOrder() {
        return importOrder;
    }

    public void setImportOrder(SysImportOrder importOrder) {
        this.importOrder = importOrder;
    }

    public BigDecimal getThreeInspectionFee() {
        return threeInspectionFee;
    }

    public void setThreeInspectionFee(BigDecimal threeInspectionFee) {
        this.threeInspectionFee = threeInspectionFee;
    }

    public BigDecimal getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(BigDecimal handlingFee) {
        this.handlingFee = handlingFee;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("importReceivePaymentId", getImportReceivePaymentId())
            .append("importOrderId", getImportOrderId())
            .append("agencyCustomsFee", getAgencyCustomsFee())
            .append("agentInspectionFee", getAgentInspectionFee())
            .append("replacementFee", getReplacementFee())
            .append("customsClearanceFee", getCustomsClearanceFee())
            .append("storageFee", getStorageFee())
            .append("agency3cFee", getAgency3cFee())
            .append("consolidationFee", getConsolidationFee())
            .append("customsInspectionFee", getCustomsInspectionFee())
            .append("inspectionQuarantineFee", getInspectionQuarantineFee())
            .append("inlandDeliverFee", getInlandDeliverFee())
            .append("invoiceAmount", getInvoiceAmount())
            .append("advanceFee", getAdvanceFee())
            .append("totalPayment", getTotalPayment())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
