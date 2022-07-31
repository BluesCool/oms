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
      <el-form-item label="付款状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="付款状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_payment_status"
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
      <!--          v-hasPermi="['system:payment:add']"-->
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
          v-hasPermi="['system:payment:edit']"
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
      <!--          v-hasPermi="['system:payment:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:payment:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paymentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="总单" align="center" prop="importOrder.masterOrder"/>
      <el-table-column label="分单" align="center" prop="importOrder.branchOrder"/>
      <el-table-column label="付款状态" align="center" key="status">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="1"
            inactive-value="0"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="内陆支出费用" align="center" prop="inlandExpenditureFee"/>
      <el-table-column label="报关支出费用" align="center" prop="customsDeclarationFee"/>
      <el-table-column label="代办3C" align="center" prop="agency3cFee"/>
      <el-table-column label="其他费用支出" align="center" prop="otherFee"/>
      <el-table-column label="利润" align="center" prop="profit"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:payment:edit']"
          >修改
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['system:payment:remove']"-->
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

    <!-- 添加或修改付款对话框 -->
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
            <el-form-item label="申报金额" prop="amountDeclared">
              <div v-if="form.importOrder">
                {{ form.importOrder.amountDeclared }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="c证品名" prop="certificateNameC">
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
            <el-form-item label="船名航次" prop="flightNumber">
              <div v-if="form.importOrder">
                {{ form.importOrder.flightNumber }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分拨单号" prop="dispatchNumber">
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
            <el-form-item label="报关支出" prop="customsDeclarationFee">
              <el-input v-model="form.customsDeclarationFee" placeholder="请输入报关支出费用"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代办3C" prop="agency3cFee">
              <el-input v-model="form.agency3cFee" placeholder="请输入代办3C"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="内陆支出" prop="inlandExpenditureFee">
              <el-input v-model="form.inlandExpenditureFee" placeholder="请输入内陆支出费用"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="其他支出" prop="otherFee">
              <el-input v-model="form.otherFee" placeholder="请输入其他费用支出"/>
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
        <el-row>
          <el-col :span="12">
            <el-form-item label="代理报关费" prop="agencyCustomsFee">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.agencyCustomsFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代理商检费" prop="agentInspectionFee">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.agentInspectionFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="换单费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.replacementFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通关费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.customsClearanceFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="仓储费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.storageFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="代办3C">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.agency3cFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="拼单费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.consolidationFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关查验费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.customsInspectionFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检验检疫费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.inspectionQuarantineFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="内陆运费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.inlandDeliverFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="三检费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.threeInspectionFee }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手续费">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.handlingFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开票金额">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.invoiceAmount }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关税">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.advanceFee }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="增值税">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.vat }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收款总额">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.totalPayment }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注">
              <div v-if="form.importReceivePayment">
                {{ form.importReceivePayment.remark }}
              </div>
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
  listPayment,
  getPayment,
  delPayment,
  addPayment,
  updatePayment,
  changePaymentStatus
} from "@/api/system/payment";

export default {
  name: "Payment",
  dicts: ['sys_payment_status'],
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
      // 付款表格数据
      paymentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        importReceivePaymentId: null,
        inlandExpenditureFee: null,
        customsDeclarationFee: null,
        agency3cFee: null,
        otherFee: null,
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
    this.getList();
  },
  methods: {
    /** 查询付款列表 */
    getList() {
      this.loading = true;
      listPayment(this.queryParams).then(response => {
        this.paymentList = response.rows;
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
        importPaymentId: null,
        importReceivePaymentId: null,
        importOrder: null,
        check: null,
        currency: null,
        customsBroker: null,
        fleet: null,
        org: null,
        tradeWay: null,
        importReceivePayment: null,
        inlandExpenditureFee: null,
        customsDeclarationFee: null,
        agency3cFee: null,
        otherFee: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.importPaymentId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加付款";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const importPaymentId = row.importPaymentId || this.ids
      getPayment(importPaymentId).then(response => {
        this.form = response.data;
        debugger;
        this.open = true;
        this.title = "修改付款";
      });
    },
    //修改状态
    handleStatusChange(row) {
      let text = row.status === "1" ? "已付款" : "未付款";
      this.$modal.confirm('确认总单："' + row.importOrder.masterOrder + '",分单："' + row.importOrder.branchOrder + '"' + text + '?').then(function () {
        return changePaymentStatus(row.importPaymentId, row.status);
      }).then(() => {
        this.$modal.msgSuccess("修改成功");
      }).catch(function () {
        row.status = row.status === "1" ? "0" : "1";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.importPaymentId != null) {
            updatePayment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPayment(this.form).then(response => {
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
      const importPaymentIds = row.importPaymentId || this.ids;
      this.$modal.confirm('是否确认删除付款编号为"' + importPaymentIds + '"的数据项？').then(function () {
        return delPayment(importPaymentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/payment/export', {
        ...this.queryParams
      }, `payment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
