package com.ydgj.oms.system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ydgj.oms.common.exception.ServiceException;
import com.ydgj.oms.common.utils.DateUtils;
import com.ydgj.oms.common.utils.StringUtils;
import com.ydgj.oms.system.domain.*;
import com.ydgj.oms.system.mapper.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ydgj.oms.system.service.ISysExportOrderService;

import static com.ydgj.oms.common.utils.poi.ExcelUtil.getSheetPictures03;
import static com.ydgj.oms.common.utils.poi.ExcelUtil.getSheetPictures07;

/**
 * 出口订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-17
 */
@Service
public class SysExportOrderServiceImpl implements ISysExportOrderService 
{

    private static final Logger log = LoggerFactory.getLogger(SysExportOrderServiceImpl.class);
    @Autowired
    private SysExportOrderMapper sysExportOrderMapper;

    @Autowired
    private SysExportReceivePaymentMapper exportReceivePaymentMapper;

    @Autowired
    private SysExportPaymentMapper exportPaymentMapper;


    @Autowired
    private SysOrgMapper orgMapper;

    @Autowired
    private SysCustomsBrokerMapper customsBrokerMapper;

    @Autowired
    private SysCurrencyMapper currencyMapper;

    @Autowired
    private SysCheckMapper checkMapper;

    @Autowired
    private SysTradeWayMapper tradeWayMapper;


    /**
     * 查询出口订单
     * 
     * @param exportOrderId 出口订单主键
     * @return 出口订单
     */
    @Override
    public SysExportOrder selectSysExportOrderByExportOrderId(Long exportOrderId)
    {
        return sysExportOrderMapper.selectSysExportOrderByExportOrderId(exportOrderId);
    }

    /**
     * 查询出口订单列表
     * 
     * @param sysExportOrder 出口订单
     * @return 出口订单
     */
    @Override
    public List<SysExportOrder> selectSysExportOrderList(SysExportOrder sysExportOrder)
    {
        return sysExportOrderMapper.selectSysExportOrderList(sysExportOrder);
    }

    /**
     * 新增出口订单
     *
     * @param sysExportOrder 出口订单
     * @param operName
     * @return 结果
     */
    @Override
    public int insertSysExportOrder(SysExportOrder sysExportOrder, String operName)
    {
        sysExportOrder.setCreateTime(DateUtils.getNowDate());
        int i = sysExportOrderMapper.insertSysExportOrder(sysExportOrder);
        SysExportReceivePayment exportReceivePayment = new SysExportReceivePayment();
        exportReceivePayment.setExportOrderId(sysExportOrder.getExportOrderId());
        exportReceivePayment.setCreateTime(DateUtils.getNowDate());
        exportReceivePaymentMapper.insertSysExportReceivePayment(exportReceivePayment);
        SysExportPayment exportPayment = new SysExportPayment();
        exportPayment.setExportReceivePaymentId( exportReceivePayment.getExportReceivePaymentId());
        exportPaymentMapper.insertSysExportPayment(exportPayment);
        return i ;
    }

    /**
     * 修改出口订单
     * 
     * @param sysExportOrder 出口订单
     * @return 结果
     */
    @Override
    public int updateSysExportOrder(SysExportOrder sysExportOrder)
    {
        sysExportOrder.setUpdateTime(DateUtils.getNowDate());
        return sysExportOrderMapper.updateSysExportOrder(sysExportOrder);
    }

    /**
     * 批量删除出口订单
     * 
     * @param exportOrderIds 需要删除的出口订单主键
     * @return 结果
     */
    @Override
    public int deleteSysExportOrderByExportOrderIds(Long[] exportOrderIds)
    {
        return sysExportOrderMapper.deleteSysExportOrderByExportOrderIds(exportOrderIds);
    }

    /**
     * 删除出口订单信息
     * 
     * @param exportOrderId 出口订单主键
     * @return 结果
     */
    @Override
    public int deleteSysExportOrderByExportOrderId(Long exportOrderId)
    {
        return sysExportOrderMapper.deleteSysExportOrderByExportOrderId(exportOrderId);
    }

    /**
     * 导入订单
     * @param inputStream
     * @param operName
     * @param updateSupport
     * @return
     */
    @Override
    public String importOrder(InputStream inputStream, String operName, boolean updateSupport) throws IOException {
        Workbook wb = WorkbookFactory.create(inputStream);
// 如果指定sheet名,则取指定sheet中的内容 否则默认指向第1个sheet
        Sheet sheet = StringUtils.isNotEmpty(StringUtils.EMPTY) ? wb.getSheet(StringUtils.EMPTY) : wb.getSheetAt(0);
        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }
        boolean isXSSFWorkbook = !(wb instanceof HSSFWorkbook);

        Map<String, PictureData> pictures;
        if (isXSSFWorkbook) {
            pictures = getSheetPictures07((XSSFSheet) sheet, (XSSFWorkbook) wb);
        } else {
            pictures = getSheetPictures03((HSSFSheet) sheet, (HSSFWorkbook) wb);
        }
        int rows = sheet.getLastRowNum();
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        if (rows > 1) {


            for (int i = 1; i <= rows; i++) {
                SysExportOrder exportOrder = new SysExportOrder();
                try {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(0);
                    String cell0 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell0.trim())) {
                        exportOrder.setFlightNumber(cell0.trim());
                    }


                    cell = row.getCell(1);
                    String cell1 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell1.trim())) {
                        SysOrg org = orgMapper.selectSysOrgByOrgName(cell1);
                        if (StringUtils.isNull(org)) {
                            org = new SysOrg();
                            org.setOrgName(cell1);
                            orgMapper.insertSysOrg(org);
                        }
                        exportOrder.setOrgId(org.getOrgId());
                    }

                    cell = row.getCell(2);
                    String cell2 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell2.trim())) {
                        exportOrder.setBranchOrder(cell2.trim());
                    }

                    cell = row.getCell(3);
                    double cell3 = cell.getNumericCellValue();
                    exportOrder.setQuantity((long) cell3);

                    cell = row.getCell(4);
                    double cell4 = cell.getNumericCellValue();
                    exportOrder.setProductQuantity((long) cell4);

                    cell = row.getCell(5);
                    double cell5 = cell.getNumericCellValue();
                    exportOrder.setWeight(BigDecimal.valueOf(cell5));

                    cell = row.getCell(6);
                    String cell6 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell6.trim())) {
                        SysTradeWay tradeWay = tradeWayMapper.selectSysTradeWayByTradeWayName(cell6.trim());
                        if (StringUtils.isNull(tradeWay)) {
                            tradeWay = new SysTradeWay();
                            tradeWay.setTradeWayName(cell6.trim());
                            tradeWayMapper.insertSysTradeWay(tradeWay);
                        }
                        exportOrder.setTradeWayId(tradeWay.getTradeWayId());
                    }

                    cell = row.getCell(7);
                    String cell7 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell7.trim())) {
                        exportOrder.setManualNumber(cell7);
                    }

                    cell = row.getCell(8);
                    String cell8 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell8.trim())) {
                        SysCurrency currency = currencyMapper.selectSysCurrencyByCurrencyName(cell8.trim());
                        if (StringUtils.isNull(currency)) {
                            currency = new SysCurrency();
                            currency.setCurrencyName(cell8.trim());
                            currencyMapper.insertSysCurrency(currency);
                        }
                        exportOrder.setCurrencyId(currency.getCurrencyId());
                    }

                    cell = row.getCell(9);
                    double cell9 = cell.getNumericCellValue();
                    exportOrder.setAmountDeclared(BigDecimal.valueOf(cell9));

                    cell = row.getCell(10);
                    String cell10 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell10.trim())) {
                        SysCheck check = checkMapper.selectSysCheckByCheckName(cell10);
                        if (StringUtils.isNull(check)) {
                            check = new SysCheck();
                            check.setCheckName(cell10.trim());
                            checkMapper.insertSysCheck(check);
                        }
                        exportOrder.setCheckId(check.getCheckId());
                    }

                    cell = row.getCell(11);
                    String cell11 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell11.trim())) {
                        exportOrder.setCustomsCode(cell11.trim());
                    }

                    cell = row.getCell(12);
                    double cell12 = cell.getNumericCellValue();
                    Date orderDate = DateUtil.getJavaDate(cell12);
                    exportOrder.setOrderDate(orderDate);

                    cell = row.getCell(13);
                    double cell13 = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(cell13);
                    exportOrder.setReleaseDate(date);

                    cell = row.getCell(14);
                    String cell14 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell14.trim())) {
                        exportOrder.setSerialNumber(cell14.trim());
                    }


                    cell = row.getCell(15);
                    String cell15 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell15.trim())) {
                        SysCustomsBroker customsBroker = customsBrokerMapper.selectSysCustomsBrokerByCustomsBrokerName(cell14.trim());
                        if (StringUtils.isNull(customsBroker)) {
                            customsBroker = new SysCustomsBroker();
                            customsBroker.setCustomsBrokerName(cell15.trim());
                            customsBrokerMapper.insertSysCustomsBroker(customsBroker);
                        }
                        exportOrder.setCustomsBrokerId(customsBroker.getCustomsBrokerId());
                    }

                    List<SysExportOrder> mportOrderOlds = sysExportOrderMapper.selectSysExportOrderByMasterOrderAndBranchOrder(exportOrder);
                    if (StringUtils.isNull(mportOrderOlds) || mportOrderOlds.size() == 0) {
                        this.insertSysExportOrder(exportOrder, operName);

                        successNum++;
                        successMsg.append("<br/>" + successNum + "、分单 " + exportOrder.getBranchOrder() + " 导入成功");
                    }
                } catch (Exception e) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、分单 " + exportOrder.getBranchOrder() + " 导入失败：";
                    failureMsg.append(msg + e.getMessage());
                    log.error(msg, e);
                }
            }


        } else {
            throw new ServiceException("导入订单数据不能为空！");
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
