package com.ydgj.oms.system.domain;

import java.math.BigDecimal;

import com.ydgj.oms.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 出口付款对象 sys_export_payment
 * 
 * @author guwei
 * @date 2022-07-17
 */
public class SysExportPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出口付款id */
    private Long exportPaymentId;

    /** 出口收款表id */
//    @Excel(name = "出口收款表id")
    private Long exportReceivePaymentId;


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

    @Excels({
            @Excel(name = "运费", targetAttr = "freightFee"),
            @Excel(name = "报关费", targetAttr = "customsFee"),
            @Excel(name = "预付费", targetAttr = "advancePaymentFee"),
            @Excel(name = "开票金额", targetAttr = "invoiceAmount"),
            @Excel(name = "收款状态", readConverterExp = "0=未收,1=已收",targetAttr = "status")

    })
    private SysExportReceivePayment exportReceivePayment;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freightFee;

    /** 其他费用支出 */
    @Excel(name = "其他费用支出")
    private BigDecimal otherFee;

    /** 付款状态（0未付 1已付） */
    @Excel(name = "付款状态", readConverterExp = "0=未付,1=已付")
    private String status;

    @Excel(name = "利润")
    private BigDecimal profit;

    public void setExportPaymentId(Long exportPaymentId) 
    {
        this.exportPaymentId = exportPaymentId;
    }

    public Long getExportPaymentId() 
    {
        return exportPaymentId;
    }
    public void setExportReceivePaymentId(Long exportReceivePaymentId) 
    {
        this.exportReceivePaymentId = exportReceivePaymentId;
    }

    public Long getExportReceivePaymentId() 
    {
        return exportReceivePaymentId;
    }
    public void setFreightFee(BigDecimal freightFee) 
    {
        this.freightFee = freightFee;
    }

    public BigDecimal getFreightFee() 
    {
        return freightFee;
    }
    public void setOtherFee(BigDecimal otherFee) 
    {
        this.otherFee = otherFee;
    }

    public BigDecimal getOtherFee() 
    {
        return otherFee;
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

    public SysExportReceivePayment getExportReceivePayment() {
        return exportReceivePayment;
    }

    public void setExportReceivePayment(SysExportReceivePayment exportReceivePayment) {
        this.exportReceivePayment = exportReceivePayment;
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

    public BigDecimal getProfit() {
        if(exportReceivePayment != null && exportReceivePayment.getInvoiceAmount() != null && (freightFee != null || otherFee != null)
                 && exportReceivePayment.getInvoiceAmount().compareTo(new BigDecimal("0.00") ) > 0){
            profit =exportReceivePayment.getInvoiceAmount() != null ? exportReceivePayment.getInvoiceAmount() : new BigDecimal("0.00")
                    .subtract(freightFee != null ? freightFee : new BigDecimal("0.00"))
                    .subtract(otherFee != null ? otherFee : new BigDecimal("0.00"));
        }
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("exportPaymentId", getExportPaymentId())
            .append("exportReceivePaymentId", getExportReceivePaymentId())
            .append("freightFee", getFreightFee())
            .append("otherFee", getOtherFee())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
