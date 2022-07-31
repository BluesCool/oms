<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <!--          v-hasPermi="['system:exportReceive:add']"-->
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
          v-hasPermi="['system:exportReceive:edit']"
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
      <!--          v-hasPermi="['system:exportReceive:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:exportReceive:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="exportReceiveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="分单" align="center" prop="exportOrder.branchOrder"/>
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
      <el-table-column label="运费" align="center" prop="freightFee"/>
      <el-table-column label="报关费" align="center" prop="customsFee"/>
      <el-table-column label="预付费" align="center" prop="advancePaymentFee"/>
      <el-table-column label="开票金额" align="center" prop="invoiceAmount"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:exportReceive:edit']"
          >修改
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['system:exportReceive:remove']"-->
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

    <!-- 添加或修改出口收款对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="船名航次" prop="flightNumber">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.flightNumber }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位">
              <div v-if="form.org">
                {{ form.org.orgName }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="分单">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.branchOrder }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.weight }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="件数">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.quantity }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品名数量">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.productQuantity }}
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
              <div v-if="form.exportOrder">
                {{ form.exportOrder.manualNumber }}
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
              <div v-if="form.exportOrder">
                {{ form.exportOrder.amountDeclared }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="查验">
              <div v-if="form.check">
                {{ form.check.checkName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关编码" prop="customsCode">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.customsCode }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单日期" prop="orderDate">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.orderDate }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="放行日期" prop="releaseDate">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.releaseDate }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="编号" prop="serialNumber">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.serialNumber }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报关行">
              <div v-if="form.customsBroker">
                {{ form.customsBroker.customsBrokerName }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <div v-if="form.exportOrder">
                {{ form.exportOrder.remark }}
              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="运费" prop="freightFee">
              <el-input v-model="form.freightFee" placeholder="请输入运费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报关费" prop="customsFee">
              <el-input v-model="form.customsFee" placeholder="请输入报关费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="预付费" prop="advancePaymentFee">
              <el-input v-model="form.advancePaymentFee" placeholder="请输入预付费" @blur="addInvoiceAmount"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开票金额" prop="invoiceAmount">
              <el-input v-model="form.invoiceAmount" placeholder="请输入开票金额" readonly/>
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
  listExportReceive,
  getExportReceive,
  delExportReceive,
  addExportReceive,
  updateExportReceive,
  changeReceiveStatus
} from "@/api/system/exportReceive";

export default {
  name: "ExportReceive",
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
      // 出口收款表格数据
      exportReceiveList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        exportOrderId: null,
        freightFee: null,
        customsFee: null,
        advancePaymentFee: null,
        invoiceAmount: null,
        status: null,
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
    /** 查询出口收款列表 */
    getList() {
      this.loading = true;
      listExportReceive(this.queryParams).then(response => {
        this.exportReceiveList = response.rows;
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
        exportReceivePaymentId: null,
        exportOrderId: null,
        exportOrder: null,
        check: null,
        currency : null,
        customsBroker : null,
        org : null,
        tradeWay : null,
        freightFee: null,
        customsFee: null,
        advancePaymentFee: null,
        invoiceAmount: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    //修改状态
    handleStatusChange(row) {
      let text = row.status === "1" ? "已收款" : "未收款";
      this.$modal.confirm('确认分单："' + row.exportOrder.branchOrder + '"'+text + '?').then(function() {
        return changeReceiveStatus(row.exportReceivePaymentId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(  "修改成功");
      }).catch(function() {
        row.status = row.status === "1" ? "0" : "1";
      });
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
      this.ids = selection.map(item => item.exportReceivePaymentId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出口收款";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const exportReceivePaymentId = row.exportReceivePaymentId || this.ids
      getExportReceive(exportReceivePaymentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出口收款";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.exportReceivePaymentId != null) {
            updateExportReceive(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExportReceive(this.form).then(response => {
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
      const exportReceivePaymentIds = row.exportReceivePaymentId || this.ids;
      this.$modal.confirm('是否确认删除出口收款编号为"' + exportReceivePaymentIds + '"的数据项？').then(function () {
        return delExportReceive(exportReceivePaymentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/exportReceive/export', {
        ...this.queryParams
      }, `exportReceive_${new Date().getTime()}.xlsx`)
    },
    addInvoiceAmount(){
      this.form.invoiceAmount = 0;
      if(this.form.freightFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.freightFee) ;
      }
      if(this.form.customsFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.customsFee);
      }
      if(this.form.advancePaymentFee != null){
        this.form.invoiceAmount =  parseFloat(this.form.invoiceAmount) + parseFloat(this.form.advancePaymentFee);
      }
    }
  }
};
</script>
