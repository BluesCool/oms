package com.ydgj.oms.system.domain;

import java.math.BigDecimal;

import com.ydgj.oms.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 出口收款对象 sys_export_receive_payment
 * 
 * @author guwei
 * @date 2022-07-17
 */
public class SysExportReceivePayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出口收款id */
    private Long exportReceivePaymentId;

    /** 订单id */
//    @Excel(name = "订单id")
    private Long exportOrderId;

    @Excels({
            @Excel(name = "单位", targetAttr = "orgName"),
    })
    private SysOrg org;

    @Excels({
            @Excel(name = "船名航次", targetAttr = "flightNumber"),
            @Excel(name = "分单", targetAttr = "branchOrder"),
            @Excel(name = "件数", targetAttr = "quantity"),
            @Excel(name = "品名数量", targetAttr = "productQuantity"),
            @Excel(name = "重量", targetAttr = "weight"),
            @Excel(name = "手册编号", targetAttr = "manualNumber"),
            @Excel(name = "申报金额", targetAttr = "amountDeclared"),
            @Excel(name = "海关编码", targetAttr = "customsCode"),
            @Excel( name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd", targetAttr = "orderDate"),
            @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd", targetAttr = "releaseDate"),
            @Excel(name = "编号", targetAttr = "serialNumber"),

    })
    private SysExportOrder exportOrder;

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
            @Excel(name = "查验", targetAttr = "checkName"),
    })
    private SysCheck check;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freightFee;

    /** 报关费 */
    @Excel(name = "报关费")
    private BigDecimal customsFee;

    /** 预付费 */
    @Excel(name = "预付费")
    private BigDecimal advancePaymentFee;

    /** 开票金额 */
    @Excel(name = "开票金额")
    private BigDecimal invoiceAmount;



    /** 收款状态（0未收 1已收） */
    @Excel(name = "收款状态", readConverterExp = "0=未收,1=已收")
    private String status;

    public void setExportReceivePaymentId(Long exportReceivePaymentId) 
    {
        this.exportReceivePaymentId = exportReceivePaymentId;
    }

    public Long getExportReceivePaymentId() 
    {
        return exportReceivePaymentId;
    }
    public void setExportOrderId(Long exportOrderId) 
    {
        this.exportOrderId = exportOrderId;
    }

    public Long getExportOrderId() 
    {
        return exportOrderId;
    }
    public void setFreightFee(BigDecimal freightFee) 
    {
        this.freightFee = freightFee;
    }

    public BigDecimal getFreightFee() 
    {
        return freightFee;
    }
    public void setCustomsFee(BigDecimal customsFee) 
    {
        this.customsFee = customsFee;
    }

    public BigDecimal getCustomsFee() 
    {
        return customsFee;
    }
    public void setAdvancePaymentFee(BigDecimal advancePaymentFee) 
    {
        this.advancePaymentFee = advancePaymentFee;
    }

    public BigDecimal getAdvancePaymentFee() 
    {
        return advancePaymentFee;
    }
    public void setInvoiceAmount(BigDecimal invoiceAmount) 
    {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getInvoiceAmount() 
    {
        return invoiceAmount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public SysExportOrder getExportOrder() {
        return exportOrder;
    }

    public void setExportOrder(SysExportOrder exportOrder) {
        this.exportOrder = exportOrder;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("exportReceivePaymentId", getExportReceivePaymentId())
            .append("exportOrderId", getExportOrderId())
            .append("freightFee", getFreightFee())
            .append("customsFee", getCustomsFee())
            .append("advancePaymentFee", getAdvancePaymentFee())
            .append("invoiceAmount", getInvoiceAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
