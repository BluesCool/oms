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
import com.ydgj.oms.system.service.ISysImportOrderService;

import static com.ydgj.oms.common.utils.poi.ExcelUtil.getSheetPictures03;
import static com.ydgj.oms.common.utils.poi.ExcelUtil.getSheetPictures07;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-03
 */
@Service
public class SysImportOrderServiceImpl implements ISysImportOrderService {

    private static final Logger log = LoggerFactory.getLogger(SysImportOrderServiceImpl.class);
    @Autowired
    private SysImportOrderMapper sysImportOrderMapper;


    @Autowired
    private SysOrgMapper orgMapper;

    @Autowired
    private SysFleetMapper fleetMapper;

    @Autowired
    private SysCustomsBrokerMapper customsBrokerMapper;

    @Autowired
    private SysCurrencyMapper currencyMapper;

    @Autowired
    private SysCheckMapper checkMapper;

    @Autowired
    private SysTradeWayMapper tradeWayMapper;

    @Autowired
    private SysImportReceivePaymentMapper importReceivePaymentMapper;

    @Autowired
    private SysImportPaymentMapper importPaymentMapper;


    /**
     * 查询订单
     *
     * @param importOrderId 订单主键
     * @return 订单
     */
    @Override
    public SysImportOrder selectSysImportOrderByImportOrderId(Long importOrderId) {
        return sysImportOrderMapper.selectSysImportOrderByImportOrderId(importOrderId);
    }

    /**
     * 查询订单列表
     *
     * @param sysImportOrder 订单
     * @return 订单
     */
    @Override
    public List<SysImportOrder> selectSysImportOrderList(SysImportOrder sysImportOrder) {
        return sysImportOrderMapper.selectSysImportOrderList(sysImportOrder);
    }

    /**
     * 新增订单
     *
     * @param sysImportOrder 订单
     * @param operName
     * @return 结果
     */
    @Override
    public int insertSysImportOrder(SysImportOrder sysImportOrder, String operName) {
//        sysImportOrder.setCreateTime(DateUtils.getNowDate());
        sysImportOrder.setCreateBy(operName);
        sysImportOrder.setCreateTime(new Date());
        int orderId = sysImportOrderMapper.insertSysImportOrder(sysImportOrder);
        SysImportReceivePayment importReceivePayment = new SysImportReceivePayment();
        importReceivePayment.setImportOrderId(sysImportOrder.getImportOrderId());
        importReceivePayment.setCreateBy(operName);
        importReceivePayment.setCreateTime(new Date());
        importReceivePaymentMapper.insertSysImportReceivePayment(importReceivePayment);
        SysImportPayment importPayment = new SysImportPayment();
        importPayment.setImportReceivePaymentId(importReceivePayment.getImportReceivePaymentId());
        importPayment.setCreateBy(operName);
        importPayment.setCreateTime(new Date());
        importPaymentMapper.insertSysImportPayment(importPayment);
        return orderId;
    }

    /**
     * 修改订单
     *
     * @param sysImportOrder 订单
     * @return 结果
     */
    @Override
    public int updateSysImportOrder(SysImportOrder sysImportOrder) {
        sysImportOrder.setUpdateTime(DateUtils.getNowDate());
        return sysImportOrderMapper.updateSysImportOrder(sysImportOrder);
    }

    /**
     * 批量删除订单
     *
     * @param importOrderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteSysImportOrderByImportOrderIds(Long[] importOrderIds) {
        return sysImportOrderMapper.deleteSysImportOrderByImportOrderIds(importOrderIds);
    }

    /**
     * 删除订单信息
     *
     * @param importOrderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteSysImportOrderByImportOrderId(Long importOrderId) {
        return sysImportOrderMapper.deleteSysImportOrderByImportOrderId(importOrderId);
    }

    /**
     * 导入订单
     *
     * @param is
     * @param operName
     * @param updateSupport
     * @return
     */
    @Override
    public String importOrder(InputStream is, String operName, boolean updateSupport) throws IOException {
        Workbook wb = WorkbookFactory.create(is);
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
                SysImportOrder importOrder = new SysImportOrder();
                try {
                    Row row = sheet.getRow(i);

                    Cell cell = row.getCell(0);
                    double cell0 = cell.getNumericCellValue();
                    Date orderDate = DateUtil.getJavaDate(cell0);
                    importOrder.setOrderDate(orderDate);


                    cell = row.getCell(1);
                    String cell1 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell1.trim())) {
                        SysOrg org = orgMapper.selectSysOrgByOrgName(cell1.trim());
                        if (StringUtils.isNull(org)) {
                            org = new SysOrg();
                            org.setOrgName(cell1);
                            orgMapper.insertSysOrg(org);
                        }
                        importOrder.setOrgId(org.getOrgId());
                    }

                    cell = row.getCell(2);
                    String cell2 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell2.trim())) {
                        importOrder.setMasterOrder(cell2.trim());
                    }

                    cell = row.getCell(3);
                    String cell3 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell3.trim())) {
                        importOrder.setBranchOrder(cell3.trim());
                    }

                    cell = row.getCell(4);
                    double cell4 = cell.getNumericCellValue();
                    importOrder.setQuantity((long) cell4);


                    cell = row.getCell(5);
                    double cell5 = cell.getNumericCellValue();
                    importOrder.setWeight(BigDecimal.valueOf(cell5));

                    cell = row.getCell(6);
                    String cell6 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell6.trim())) {
                        SysTradeWay tradeWay = tradeWayMapper.selectSysTradeWayByTradeWayName(cell6.trim());
                        if (StringUtils.isNull(tradeWay)) {
                            tradeWay = new SysTradeWay();
                            tradeWay.setTradeWayName(cell6.trim());
                            tradeWayMapper.insertSysTradeWay(tradeWay);
                        }
                        importOrder.setTradeWayId(tradeWay.getTradeWayId());
                    }

                    cell = row.getCell(7);
                    String cell7 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell7.trim())) {
                        importOrder.setManualNumber(cell7);
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
                        importOrder.setCurrencyId(currency.getCurrencyId());
                    }

                    cell = row.getCell(9);
                    double cell9 = cell.getNumericCellValue();
                    importOrder.setAmountDeclared(BigDecimal.valueOf(cell9));

                    cell = row.getCell(10);
                    String cell10 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell10.trim())) {
                        importOrder.setCertificateNameC(cell10.trim());
                    }

                    cell = row.getCell(11);
                    String cell11 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell11.trim())) {
                        SysCheck check = checkMapper.selectSysCheckByCheckName(cell11);
                        if (StringUtils.isNull(check)) {
                            check = new SysCheck();
                            check.setCheckName(cell11.trim());
                            checkMapper.insertSysCheck(check);
                        }
                        importOrder.setCheckId(check.getCheckId());
                    }

                    cell = row.getCell(12);
                    double cell12 = cell.getNumericCellValue();
                    importOrder.setTax(BigDecimal.valueOf(cell12));

                    cell = row.getCell(13);
                    String cell13 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell13.trim())) {
                        importOrder.setCustomsCode(cell13.trim());
                    }

                    cell = row.getCell(14);
                    double cell14 = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(cell14);
                    importOrder.setReleaseDate(date);

                    cell = row.getCell(15);
                    String cell15 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell15.trim())) {
                        SysFleet fleet = fleetMapper.selectSysFleetByFleetName(cell15);
                        if (StringUtils.isNull(fleet)) {
                            fleet = new SysFleet();
                            fleet.setFleetName(cell15.trim());
                            fleetMapper.insertSysFleet(fleet);
                        }
                        importOrder.setFleetId(fleet.getFleetId());
                    }

                    cell = row.getCell(16);
                    String cell16 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell16.trim())) {
                        SysCustomsBroker customsBroker = customsBrokerMapper.selectSysCustomsBrokerByCustomsBrokerName(cell16.trim());
                        if (StringUtils.isNull(customsBroker)) {
                            customsBroker = new SysCustomsBroker();
                            customsBroker.setCustomsBrokerName(cell16.trim());
                            customsBrokerMapper.insertSysCustomsBroker(customsBroker);
                        }
                        importOrder.setCustomsBrokerId(customsBroker.getCustomsBrokerId());
                    }

                    cell = row.getCell(17);
                    String cell17 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell17.trim())) {
                        importOrder.setSerialNumber(cell17.trim());
                    }

                    cell = row.getCell(18);
                    double cell18 = cell.getNumericCellValue();
                    importOrder.setProductQuantity((long) cell18);

                    cell = row.getCell(19);
                    String cell19 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell19.trim())) {
                        importOrder.setFlightNumber(cell19.trim());
                    }

                    cell = row.getCell(20);
                    String cell20 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell20.trim())) {
                        importOrder.setDispatchNumber(cell20.trim());
                    }

                    cell = row.getCell(21);
                    String cell21 = cell.getStringCellValue();
                    if (!StringUtils.isEmpty(cell21.trim())) {
                        importOrder.setRemark(cell21.trim());
                    }

                    List<SysImportOrder> importOrders = sysImportOrderMapper.selectSysImportOrderByMasterOrderAndBranchOrder(importOrder);
                    if (StringUtils.isNull(importOrders) || importOrders.size() == 0) {
                        this.insertSysImportOrder(importOrder, operName);

                        successNum++;
                        successMsg.append("<br/>" + successNum + "、总单 " + importOrder.getMasterOrder() + " 导入成功");
                    }
                } catch (Exception e) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、总单 " + importOrder.getMasterOrder() + " 导入失败：";
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
