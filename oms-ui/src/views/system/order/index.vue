<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="总单" prop="masterOrder">
        <el-input
          v-model="queryParams.masterOrder"
          placeholder="请输入总单"
          clearable
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
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
                        style="width: 200px"
                        type="date"
                        value-format="yyyy-MM-dd"
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
      <el-form-item label="车队名称" prop="fleetId">
        <el-select
          v-model="queryParams.fleetName"
          placeholder="车队"
          clearable
          style="width: 200px"
        >
          <el-option
            v-for="fleet in fleets"
            :key="fleet.fleetName"
            :label="fleet.fleetName"
            :value="fleet.fleetId"
          ></el-option>
        </el-select>
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
          v-hasPermi="['system:order:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-view"
          size="mini"
          :disabled="single"
          @click="handleDetail"
          v-hasPermi="['system:order:detail']"
        >详情
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
          v-hasPermi="['system:order:edit']"
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
          v-hasPermi="['system:order:remove']"
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
          v-hasPermi="['system:order:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:order:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border  :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="订单日期" align="center" prop="orderDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="org.orgName" width="200"/>
      <el-table-column label="总单" align="center" prop="masterOrder" width="200" />
      <el-table-column label="分单" align="center" prop="branchOrder" width="100"/>
      <el-table-column label="贸易方式" align="center" prop="tradeWay.tradeWayName"/>
      <el-table-column label="c证品名" align="center" prop="certificateNameC"/>
      <el-table-column label="查验" align="center" prop="check.checkName"/>
      <el-table-column label="海关编码" align="center" prop="customsCode" width="100"/>
      <el-table-column label="放行日期" align="center" prop="releaseDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车队" align="center" prop="fleet.fleetName"/>
      <el-table-column label="报关行" align="center" prop="customsBroker.customsBrokerName"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDetail(scope.row)"
            v-hasPermi="['system:order:detail']"
          >详情
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
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
    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="单位">
              <el-select v-model="form.orgId" placeholder="请选择单位" >
                <el-option
                  v-for="org in orgs"
                  :key="org.orgName"
                  :label="org.orgName"
                  :value="org.orgId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总单" prop="masterOrder">
              <el-input v-model="form.masterOrder" placeholder="请输入总单"/>
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
            <el-form-item label="c证品名" prop="certificateNameC">
              <el-input v-model="form.certificateNameC" placeholder="请输入c证品名"/>
            </el-form-item>
          </el-col>
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
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="税金" prop="tax">
              <el-input v-model="form.tax" placeholder="请输入税金"/>
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
            <el-form-item label="接单日期" prop="orderDate">
              <el-date-picker clearable
                              v-model="form.orderDate"
                              style="width: 200px"
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
                              style="width: 200px"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择放行日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="船名航次" prop="flightNumber">
              <el-input v-model="form.flightNumber" placeholder="请输入船名航次"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分拨单号" prop="dispatchNumber">
              <el-input v-model="form.dispatchNumber"  placeholder="请输入分拨单号"/>
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
            <el-form-item label="车队">
              <el-select v-model="form.fleetId" placeholder="请选择车队">
                <el-option
                  v-for="fleet in fleets"
                  :key="fleet.fleetName"
                  :label="fleet.fleetName"
                  :value="fleet.fleetId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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

    <el-dialog :title="detailTitle" :visible.sync="detailOpen" width="700px" append-to-body>
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
              {{ form.masterOrder }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="分单">
              {{ form.branchOrder }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量">
              {{ form.weight }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="件数">
              {{ form.quantity }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品名数量">
              {{ form.productQuantity }}
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
              {{ form.amountDeclared }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="c证品名">
              {{ form.certificateNameC }}
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
              {{ form.tax }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海关编码">
              {{ form.customsCode }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单日期">
              {{ form.orderDate }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="放行日期">
              {{ form.releaseDate }}
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="编号">
              {{ form.serialNumber }}
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
              {{ form.flightNumber }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分拨单号" >
              {{ form.dispatchNumber }}
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
              {{ form.remark }}
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="detailCancel">取 消</el-button>
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
import {addOrder, delOrder, getBaseData, getOrder, listOrder, updateOrder} from "@/api/system/order";
import {getToken} from "@/utils/auth";

export default {
  name: "Order",
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
      // 订单表格数据
      orderList: [],
      //查验
      checks: [],
      //货币
      currencys: [],
      //customsBrokers
      customsBrokers: [],
      //车队
      fleets: [],
      //单位
      orgs: [],
      //贸易方式
      tradeWays: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      detailTitle: "",
      detailOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgId: null,
        masterOrder: null,
        branchOrder: null,
        quantity: null,
        productQuantity: null,
        weight: null,
        tradeWayId: null,
        manualNumber: null,
        currencyId: null,
        amountDeclared: null,
        certificateNameC: null,
        checkId: null,
        tax: null,
        customsCode: null,
        orderDate: null,
        releaseDate: null,
        serialNumber: null,
        fleetId: null,
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
        url: process.env.VUE_APP_BASE_API + "/system/order/importData"
      },
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.baseDate()
    this.getList();
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    detailCancel() {
      this.detailOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        importOrderId: null,
        orgId: null,
        masterOrder: null,
        branchOrder: null,
        quantity: null,
        weight: null,
        tradeWayId: null,
        currencyId: null,
        amountDeclared: null,
        certificateNameC: null,
        checkId: null,
        tax: null,
        customsCode: null,
        releaseDate: null,
        serialNumber: null,
        fleetId: null,
        customsBrokerId: null,
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
      this.ids = selection.map(item => item.importOrderId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 获取基础 */
    baseDate() {
      getBaseData().then(response => {
        debugger;
        this.checks = response.checks;
        this.customsBrokers = response.customsBrokers;
        this.currencys = response.currencys;
        this.fleets = response.fleets;
        this.orgs = response.orgs;
        this.tradeWays = response.tradeWays;
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.baseDate();
      debugger;
      this.open = true;
      this.title = "添加订单";
      getOrder().then(response => {
        // this.form = response.data;
        this.checks = response.checks;
        this.customsBrokers = response.customsBrokers;
        this.currencys = response.currencys;
        this.fleets = response.fleets;
        this.orgs = response.orgs;
        this.tradeWays = response.tradeWays;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const importOrderId = row.importOrderId || this.ids
      getOrder(importOrderId).then(response => {
        this.form = response.data;
        this.checks = response.checks;
        this.customsBrokers = response.customsBrokers;
        this.currencys = response.currencys;
        this.fleets = response.fleets;
        this.orgs = response.orgs;
        this.tradeWays = response.tradeWays;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 修改按钮操作 */
    handleDetail(row) {
      this.reset();
      const importOrderId = row.importOrderId || this.ids
      getOrder(importOrderId).then(response => {
        this.form = response.data;
        this.checks = response.checks;
        this.customsBrokers = response.customsBrokers;
        this.currencys = response.currencys;
        this.fleets = response.fleets;
        this.orgs = response.orgs;
        this.tradeWays = response.tradeWays;
        this.detailOpen = true;
        this.detailTitle = "详情订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.importOrderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      const importOrderIds = row.importOrderId || this.ids;
      this.$modal.confirm('是否确认删除订单编号为"' + importOrderIds + '"的数据项？').then(function () {
        return delOrder(importOrderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "进口订单导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/order/importTemplate', {
      }, `import_order_template_${new Date().getTime()}.xlsx`)
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
