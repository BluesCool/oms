package com.ydgj.oms.system.domain;

import java.math.BigDecimal;

import com.ydgj.oms.common.annotation.Excels;
import com.ydgj.oms.system.mapper.SysImportReceivePaymentMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ydgj.oms.common.annotation.Excel;
import com.ydgj.oms.common.core.domain.BaseEntity;

/**
 * 付款对象 sys_import_payment
 * 
 * @author ruoyi
 * @date 2022-07-03
 */
public class SysImportPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
//    @Excel(name = "付款序号", cellType = Excel.ColumnType.NUMERIC)
    private Long importPaymentId;

    /** 收款表id */
//    @Excel(name = "收款表id")
    private Long importReceivePaymentId;


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


    @Excels({
            @Excel(name = "代理报关费", targetAttr = "agencyCustomsFee"),
            @Excel(name = "代理商检费", targetAttr = "agentInspectionFee"),
            @Excel(name = "换单费", targetAttr = "replacementFee"),
            @Excel(name = "通关费", targetAttr = "customsClearanceFee"),
            @Excel(name = "仓储费", targetAttr = "storageFee"),
            @Excel(name = "代办3C", targetAttr = "agency3cFee"),
            @Excel(name = "拼单费", targetAttr = "consolidationFee"),
            @Excel(name = "海关查验费", targetAttr = "customsInspectionFee"),
            @Excel(name = "检验检疫费", targetAttr = "inspectionQuarantineFee"),
            @Excel(name = "内陆运费", targetAttr = "inlandDeliverFee"),
            @Excel( name = "三检费",  targetAttr = "threeInspectionFee"),
            @Excel(name = "手续费",  targetAttr = "handlingFee"),
            @Excel(name = "开票金额", targetAttr = "invoiceAmount"),
            @Excel(name = "关税", targetAttr = "advanceFee"),
            @Excel(name = "增值税", targetAttr = "vat"),
            @Excel(name = "收款总额", targetAttr = "totalPayment"),
            @Excel(name = "收款状态", readConverterExp = "0=未收,1=已收",targetAttr = "status")

    })
    private SysImportReceivePayment importReceivePayment;


    /** 内陆支出费用 */
    @Excel(name = "内陆支出费用")
    private BigDecimal inlandExpenditureFee;

    /** 报关支出费用 */
    @Excel(name = "报关支出费用")
    private BigDecimal customsDeclarationFee;

    /** 代办3C */
    @Excel(name = "代办3C")
    private BigDecimal agency3cFee;

    /** 其他费用支出 */
    @Excel(name = "其他费用支出")
    private BigDecimal otherFee;

    /** 付款状态（0未付 1已付） */
    @Excel(name = "付款状态", readConverterExp = "0=未付,1=已付")
    private String status;

    @Excel(name = "利润")
    private BigDecimal profit;

    public void setImportPaymentId(Long importPaymentId) 
    {
        this.importPaymentId = importPaymentId;
    }

    public Long getImportPaymentId() 
    {
        return importPaymentId;
    }
    public void setImportReceivePaymentId(Long importReceivePaymentId) 
    {
        this.importReceivePaymentId = importReceivePaymentId;
    }

    public Long getImportReceivePaymentId() 
    {
        return importReceivePaymentId;
    }
    public void setInlandExpenditureFee(BigDecimal inlandExpenditureFee) 
    {
        this.inlandExpenditureFee = inlandExpenditureFee;
    }

    public BigDecimal getInlandExpenditureFee() 
    {
        return inlandExpenditureFee;
    }
    public void setCustomsDeclarationFee(BigDecimal customsDeclarationFee) 
    {
        this.customsDeclarationFee = customsDeclarationFee;
    }

    public BigDecimal getCustomsDeclarationFee() 
    {
        return customsDeclarationFee;
    }
    public void setAgency3cFee(BigDecimal agency3cFee) 
    {
        this.agency3cFee = agency3cFee;
    }

    public BigDecimal getAgency3cFee() 
    {
        return agency3cFee;
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

    public SysImportOrder getImportOrder() {
        return importOrder;
    }

    public void setImportOrder(SysImportOrder importOrder) {
        this.importOrder = importOrder;
    }

    public SysImportReceivePayment getImportReceivePayment() {
        return importReceivePayment;
    }

    public void setImportReceivePayment(SysImportReceivePayment importReceivePayment) {
        this.importReceivePayment = importReceivePayment;
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

    public BigDecimal getProfit() {
        if(importReceivePayment != null
                && (inlandExpenditureFee !=null || customsDeclarationFee != null || agency3cFee != null || otherFee != null)
                && importReceivePayment.getTotalPayment().compareTo(new BigDecimal("0.00") ) > 0){
            profit =importReceivePayment.getTotalPayment()
                    .subtract(inlandExpenditureFee == null ? new BigDecimal("0.00") : inlandExpenditureFee)
                    .subtract(customsDeclarationFee== null ? new BigDecimal("0.00") : customsDeclarationFee)
                    .subtract(agency3cFee== null ? new BigDecimal("0.00") : agency3cFee)
                    .subtract(otherFee== null ? new BigDecimal("0.00") : otherFee);
        }
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("importPaymentId", getImportPaymentId())
            .append("importReceivePaymentId", getImportReceivePaymentId())
            .append("inlandExpenditureFee", getInlandExpenditureFee())
            .append("customsDeclarationFee", getCustomsDeclarationFee())
            .append("agency3cFee", getAgency3cFee())
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
