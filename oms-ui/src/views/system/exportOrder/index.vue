<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="船名航次" prop="flightNumber">
        <el-input
          v-model="queryParams.flightNumber"
          placeholder="请输入船名航次"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="orgId">
        <el-select
          v-model="queryParams.orgId"
          placeholder="单位"
          clearable
          style="width: 200px"
        >
          <el-option
            v-for="org in orgs"
            :key="org.orgName"
            :label="org.orgName"
            :value="org.orgId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="分单" prop="branchOrder">
        <el-input
          v-model="queryParams.branchOrder"
          placeholder="请输入分单"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="件数" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入件数"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贸易方式" prop="tradeWayId">
        <el-select
          v-model="queryParams.tradeWayId"
          placeholder="请输入贸易方式名称"
          clearable
          style="width: 200px"
        >
          <el-option
            v-for="tradeWay in tradeWays"
            :key="tradeWay.tradeWayName"
            :label="tradeWay.tradeWayName"
            :value="tradeWay.tradeWayId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="海关编码" prop="customsCode">
        <el-input
          v-model="queryParams.customsCode"
          placeholder="请输入海关编码"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放行日期" prop="releaseDate">
        <el-date-picker clearable
                        v-model="queryParams.releaseDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        style="width: 200px"
                        placeholder="请选择放行日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="编号" prop="serialNumber">
        <el-input
          v-model="queryParams.serialNumber"
          placeholder="请输入编号"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报关行" prop="customsBrokerId">
        <el-select
          v-model="queryParams.customsBrokerId"
          placeholder="报关行"
          clearable
          style="width: 200px"
        >
          <el-option
            v-for="customsBroker in customsBrokers"
            :key="customsBroker.customsBrokerName"
            :label="customsBroker.customsBrokerName"
            :value="customsBroker.customsBrokerId"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:exportOrder:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:exportOrder:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:exportOrder:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:exportOrder:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:exportOrder:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="exportOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="船名航次" align="center" prop="flightNumber"/>
      <el-table-column label="单位id" align="center" prop="org.orgName"/>
      <el-table-column label="分单" align="center" prop="branchOrder"/>
      <el-table-column label="件数" align="center" prop="quantity"/>
      <el-table-column label="品名数量" align="center" prop="productQuantity"/>
      <el-table-column label="重量" align="center" prop="weight"/>
      <el-table-column label="贸易方式" align="center" prop="tradeWay.tradeWayName"/>
      <el-table-column label="手册编号" align="center" prop="manualNumber"/>
      <el-table-column label="货币" align="center" prop="currency.currencyName"/>
      <el-table-column label="申报金额" align="center" prop="amountDeclared"/>
      <el-table-column label="查验" align="center" prop="check.checkName"/>
      <el-table-column label="海关编码" align="center" prop="customsCode"/>
      <el-table-column label="订单日期" align="center" prop="orderDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="放行日期" align="center" prop="releaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="编号" align="center" prop="serialNumber"/>
      <el-table-column label="报关行" align="center" prop="customsBroker.customsBrokerName"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:exportOrder:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:exportOrder:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改出口订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="船名航次" prop="flightNumber">
              <el-input v-model="form.flightNumber" placeholder="请输入船名航次"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位">
              <el-select v-model="form.orgId" placeholder="请选择单位">
                <el-option
                  v-for="org in orgs"
                  :key="org.orgName"
                  :label="org.orgName"
                  :value="org.orgId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="分单" prop="branchOrder">
              <el-input v-model="form.branchOrder" placeholder="请输入分单"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量" prop="weight">
              <el-input v-model="form.weight" placeholder="请输入重量"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="件数" prop="quantity">
              <el-input v-model="form.quantity" placeholder="请输入件数"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品名数量" prop="productQuantity">
              <el-input v-model="form.productQuantity" placeholder="请输入品名数量"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="贸易方式">
              <el-select v-model="form.tradeWayId" placeholder="请选择贸易方式">
                <el-option
                  v-for="tradeWay in tradeWays"
                  :key="tradeWay.tradeWayName"
                  :label="tradeWay.tradeWayName"
                  :value="tradeWay.tradeWayId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手册编号" prop="manualNumber">
              <el-input v-model="form.manualNumber" placeholder="请输入手册编号"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="货币">
              <el-select v-model="form.currencyId" placeholder="请选择货币">
                <el-option
                  v-for="currency in currencys"
                  :key="currency.currencyName"
                  :label="currency.currencyName"
                  :value="currency.currencyId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申报金额" prop="amountDeclared">
              <el-input v-model="form.amountDeclared" placeholder="请输入申报金额"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="查验">
              <el-select v-model="form.checkId" placeholder="请选择查验">
                <el-option
                  v-for="check in checks"
                  :key="check.checkName"
                  :label="check.checkName"
                  :value="check.checkId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关编码" prop="customsCode">
              <el-input v-model="form.customsCode" placeholder="请输入海关编码"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单日期" prop="orderDate">
              <el-date-picker clearable
                              v-model="form.orderDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择订单日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="放行日期" prop="releaseDate">
              <el-date-picker clearable
                              v-model="form.releaseDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择放行日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="编号" prop="serialNumber">
              <el-input v-model="form.serialNumber" placeholder="请输入编号"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报关行">
              <el-select v-model="form.customsBrokerId" placeholder="请选择报关行">
                <el-option
                  v-for="customsBroker in customsBrokers"
                  :key="customsBroker.customsBrokerName"
                  :label="customsBroker.customsBrokerName"
                  :value="customsBroker.customsBrokerId"
                ></el-option>
              </el-select>
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

    <!--    订单导入-->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的工单
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listExportOrder,
  getExportOrder,
  delExportOrder,
  addExportOrder,
  updateExportOrder,
  getBaseData
} from "@/api/system/exportOrder";
import {getToken} from "@/utils/auth";

export default {
  name: "ExportOrder",
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
      // 出口订单表格数据
      exportOrderList: [],
      //查验
      checks: [],
      //货币
      currencys: [],
      //customsBrokers
      customsBrokers: [],
      //单位
      orgs: [],
      //贸易方式
      tradeWays: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        flightNumber: null,
        orgId: null,
        branchOrder: null,
        quantity: null,
        productQuantity: null,
        weight: null,
        tradeWayId: null,
        manualNumber: null,
        currencyId: null,
        amountDeclared: null,
        checkId: null,
        customsCode: null,
        releaseDate: null,
        serialNumber: null,
        customsBrokerId: null,
      },
      // 表单参数
      form: {},
      // 订单导入参数
      upload: {
        // 是否显示弹出层（订单导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/exportOrder/importData"
      },
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.baseData();
  },
  methods: {
    /** 查询出口订单列表 */
    getList() {
      this.loading = true;
      listExportOrder(this.queryParams).then(response => {
        this.exportOrderList = response.rows;
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
        exportOrderId: null,
        flightNumber: null,
        orgId: null,
        branchOrder: null,
        quantity: null,
        weight: null,
        tradeWayId: null,
        currencyId: null,
        amountDeclared: null,
        checkId: null,
        customsCode: null,
        orderDate: null,
        releaseDate: null,
        serialNumber: null,
        customsBrokerId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    //获取基础数据
    baseData(){
       getBaseData().then(response => {
         debugger;
         this.checks = response.checks;
         this.customsBrokers = response.customsBrokers;
         this.currencys = response.currencys;
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.exportOrderId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出口订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const exportOrderId = row.exportOrderId || this.ids
      getExportOrder(exportOrderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出口订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.exportOrderId != null) {
            updateExportOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExportOrder(this.form).then(response => {
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
      const exportOrderIds = row.exportOrderId || this.ids;
      this.$modal.confirm('是否确认删除出口订单编号为"' + exportOrderIds + '"的数据项？').then(function () {
        return delExportOrder(exportOrderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/exportOrder/export', {
        ...this.queryParams
      }, `exportOrder_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "出口订单导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/exportOrder/importTemplate', {
      }, `export_order_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
