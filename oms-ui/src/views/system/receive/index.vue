<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="总单编号" prop="masterOrder">
        <el-input
          v-model="queryParams.masterOrder"
          placeholder="请输入总单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分单编号" prop="branchOrder">
        <el-input
          v-model="queryParams.branchOrder"
          placeholder="请输入分单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收款状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="收款状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_receive_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['system:receive:add']"-->
      <!--        >新增</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:receive:edit']"
        >修改
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="danger"-->
      <!--          plain-->
      <!--          icon="el-icon-delete"-->
      <!--          size="mini"-->
      <!--          :disabled="multiple"-->
      <!--          @click="handleDelete"-->
      <!--          v-hasPermi="['system:receive:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:receive:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="总单" align="center" prop="importOrder.masterOrder"/>
      <el-table-column label="分单" align="center" prop="importOrder.branchOrder"/>
      <el-table-column label="收款状态" align="center" key="status">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="1"
            inactive-value="0"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="代理报关费" align="center" prop="agencyCustomsFee"/>
      <el-table-column label="代理商检费" align="center" prop="agentInspectionFee"/>
      <el-table-column label="换单费" align="center" prop="replacementFee"/>
      <el-table-column label="通关费" align="center" prop="customsClearanceFee"/>
      <el-table-column label="仓储费" align="center" prop="storageFee"/>
      <el-table-column label="代办3C" align="center" prop="agency3cFee"/>
      <el-table-column label="拼单费" align="center" prop="consolidationFee"/>
      <el-table-column label="海关查验费" align="center" prop="customsInspectionFee"/>
      <el-table-column label="检验检疫费" align="center" prop="inspectionQuarantineFee"/>
      <el-table-column label="内陆运费" align="center" prop="inlandDeliverFee"/>
      <el-table-column label="三检费" align="center" prop="threeInspectionFee"/>
      <el-table-column label="手续费" align="center" prop="handlingFee"/>
      <el-table-column label="开票金额" align="center" prop="invoiceAmount"/>
      <el-table-column label="关税" align="center" prop="advanceFee"/>
      <el-table-column label="增值税" align="center" prop="vat"/>
      <el-table-column label="收款总额" align="center" prop="totalPayment"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:receive:edit']"
          >修改
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['system:receive:remove']"-->
          <!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


    <!-- 修改收款对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="单位">
              <div v-if="form.org">
                {{ form.org.orgName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总单" prop="masterOrder">
              <div v-if="form.importOrder">
                {{ form.importOrder.masterOrder }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="分单">
              <div v-if="form.importOrder">
                {{ form.importOrder.branchOrder }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量">
              <div v-if="form.importOrder">
                {{ form.importOrder.weight }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="件数">
              <div v-if="form.importOrder">
                {{ form.importOrder.quantity }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品名数量">
              <div v-if="form.importOrder">
                {{ form.importOrder.productQuantity }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="贸易方式">
              <div v-if="form.tradeWay">
                {{ form.tradeWay.tradeWayName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手册编号">
              <div v-if="form.importOrder">
                {{ form.importOrder.manualNumber }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="货币">
              <div v-if="form.currency">
                {{ form.currency.currencyName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申报金额">
              <div v-if="form.importOrder">
                {{ form.importOrder.amountDeclared }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="c证品名">
              <div v-if="form.importOrder">
                {{ form.importOrder.certificateNameC }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="查验">
              <div v-if="form.check">
                {{ form.check.checkName }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="税金">
              <div v-if="form.importOrder">
                {{ form.importOrder.tax }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关编码">
              <div v-if="form.importOrder">
                {{ form.importOrder.customsCode }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单日期">
              <div v-if="form.importOrder">
                {{ form.importOrder.orderDate }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="放行日期">
              <div v-if="form.importOrder">
                {{ form.importOrder.releaseDate }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="编号">
              <div v-if="form.importOrder">
                {{ form.importOrder.serialNumber }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车队">
              <div v-if="form.fleet">
                {{ form.fleet.fleetName }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="船名航次" >
              <div v-if="form.importOrder">
                {{ form.importOrder.flightNumber }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分拨单号" >
              <div v-if="form.importOrder">
                {{ form.importOrder.dispatchNumber }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="报关行">
              <div v-if="form.customsBroker">
                {{ form.customsBroker.customsBrokerName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注">
              <div v-if="form.importOrder">
                {{ form.importOrder.remark }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="代理报关费" prop="agencyCustomsFee">
              <el-input v-model="form.agencyCustomsFee" placeholder="请输入代理报关费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代理商检费" prop="agentInspectionFee">
              <el-input v-model="form.agentInspectionFee" placeholder="请输入代理商检费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="换单费" prop="replacementFee">
              <el-input v-model="form.replacementFee" placeholder="请输入换单费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通关费" prop="customsClearanceFee">
              <el-input v-model="form.customsClearanceFee" placeholder="请输入通关费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="仓储费" prop="storageFee">
              <el-input v-model="form.storageFee" placeholder="请输入仓储费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代办3C" prop="agency3cFee">
              <el-input v-model="form.agency3cFee" placeholder="请输入代办3C" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="拼单费" prop="consolidationFee">
              <el-input v-model="form.consolidationFee" placeholder="请输入拼单费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关查验费" prop="customsInspectionFee">
              <el-input v-model="form.customsInspectionFee" placeholder="请输入海关查验费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检验检疫费" prop="inspectionQuarantineFee">
              <el-input v-model="form.inspectionQuarantineFee" placeholder="请输入检验检疫费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="内陆运费" prop="inlandDeliverFee">
              <el-input v-model="form.inlandDeliverFee" placeholder="请输入内陆运费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="三检费" prop="threeInspectionFee">
              <el-input v-model="form.threeInspectionFee" placeholder="请输入三检费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手续费" prop="handlingFee">
              <el-input v-model="form.handlingFee" placeholder="请输入手续费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开票金额" prop="invoiceAmount">
              <el-input v-model="form.invoiceAmount" placeholder="请输入开票金额" readonly/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关税" prop="advanceFee">
              <el-input v-model="form.advanceFee" placeholder="请输入关税" @blur="addTotalPayment"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="增值税" prop="vat">
              <el-input v-model="form.vat"  placeholder="请输入增值税" @blur="addTotalPayment"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收款总额" prop="totalPayment">
              <el-input v-model="form.totalPayment" placeholder="请输入收款总额" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addReceive,
  changeReceiveStatus,
  delReceive,
  getReceive,
  getReceiveBaseData,
  listReceive,
  updateReceive
} from "@/api/system/receive";


export default {
  name: "Receive",
  dicts: ['sys_receive_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收款表格数据
      receiveList: [],
      //查验
      // checks: [],
      // //货币
      // currencys: [],
      // //customsBrokers
      // customsBrokers: [],
      // //车队
      // fleets: [],
      // //单位
      // orgs: [],
      // //贸易方式
      // tradeWays: [],
      //
      importOrder: {},
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openAdd: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        importOrderId: null,
        agencyCustomsFee: null,
        agentInspectionFee: null,
        replacementFee: null,
        customsClearanceFee: null,
        storageFee: null,
        agency3cFee: null,
        consolidationFee: null,
        customsInspectionFee: null,
        inspectionQuarantineFee: null,
        inlandDeliverFee: null,
        threeInspectionFee: null,
        handlingFee: null,
        invoiceAmount: null,
        advanceFee: null,
        totalPayment: null,
        status: null,
        masterOrder: null,
        branchOrder: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    // this.receiveBaseDate();
    this.getList();
  },
  methods: {
    /** 查询收款列表 */
    getList() {
      this.loading = true;
      listReceive(this.queryParams).then(response => {
        this.receiveList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        importReceivePaymentId: null,
        importOrderId: null,
        importOrder: null,
        check: null,
        currency: null,
        customsBroker: null,
        fleet: null,
        org: null,
        tradeWay: null,
        agencyCustomsFee: null,
        agentInspectionFee: null,
        replacementFee: null,
        customsClearanceFee: null,
        storageFee: null,
        agency3cFee: null,
        consolidationFee: null,
        customsInspectionFee: null,
        inspectionQuarantineFee: null,
        inlandDeliverFee: null,
        invoiceAmount: null,
        threeInspectionFee: null,
        handlingFee: null,
        advanceFee: null,
        vat: null,
        totalPayment: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 获取基础 */
    receiveBaseDate() {
      getReceiveBaseData().then(response => {
        debugger;
        this.checks = response.checks;
        this.customsBrokers = response.customsBrokers;
        this.currencys = response.currencys;
        this.fleets = response.fleets;
        this.orgs = response.orgs;
        this.tradeWays = response.tradeWays;
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //改变收款状态
    handleStatusChange(row) {
      let text = row.status === "1" ? "已收款" : "未收款";
      this.$modal.confirm('确认总单："' + row.importOrder.masterOrder + '",分单："' + row.importOrder.branchOrder + '"' + text + '?').then(function () {
        return changeReceiveStatus(row.importReceivePaymentId, row.status);
      }).then(() => {
        this.$modal.msgSuccess("修改成功");
      }).catch(function () {
        row.status = row.status === "1" ? "0" : "1";
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.importReceivePaymentId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.openAdd = true;
      this.title = "添加收款";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const importReceivePaymentId = row.importReceivePaymentId || this.ids
      getReceive(importReceivePaymentId).then(response => {
        this.form = response.data;
        debugger;
        // this.importOrder = response.importOrder;

        this.open = true;
        this.title = "修改收款";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.importReceivePaymentId != null) {
            updateReceive(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReceive(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const importReceivePaymentIds = row.importReceivePaymentId || this.ids;
      this.$modal.confirm('是否确认删除收款编号为"' + importReceivePaymentIds + '"的数据项？').then(function () {
        return delReceive(importReceivePaymentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/receive/export', {
        ...this.queryParams
      }, `receive_${new Date().getTime()}.xlsx`)
    },

    addTotalPayment(){
      // advanceFee: null,
      //   vat: null,
      //   totalPayment: null,
      this.form.totalPayment = 0;
      if(this.form.invoiceAmount != null){
        this.form.totalPayment =  parseFloat(this.form.totalPayment) + parseFloat(this.form.invoiceAmount) ;
      }
      if(this.form.advanceFee != null){
        this.form.totalPayment =  parseFloat(this.form.totalPayment) + parseFloat(this.form.advanceFee) ;
      }
      if(this.form.vat != null){
        this.form.totalPayment =  parseFloat(this.form.totalPayment) + parseFloat(this.form.vat);
      }
    },
    /**
     * input失焦事件
     */
    addInvoiceAmount(){
      this.form.invoiceAmount = 0;
      if(this.form.agencyCustomsFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.agencyCustomsFee) ;
      }
      if(this.form.agentInspectionFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.agentInspectionFee);
      }
      if(this.form.replacementFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.replacementFee);
      }
      if(this.form.customsClearanceFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.customsClearanceFee);
      }
      if(this.form.storageFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.storageFee);
      }
      if(this.form.agency3cFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.agency3cFee);
      }
      if(this.form.consolidationFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.consolidationFee);
      }
      if(this.form.customsInspectionFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.customsInspectionFee);
      }
      if(this.form.inspectionQuarantineFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.inspectionQuarantineFee);
      }
      if(this.form.inlandDeliverFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.inlandDeliverFee);
      }
      if(this.form.threeInspectionFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.threeInspectionFee);
      }
      if(this.form.handlingFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.handlingFee);
      }
      this.addTotalPayment();
    }
  }
};
</script>
