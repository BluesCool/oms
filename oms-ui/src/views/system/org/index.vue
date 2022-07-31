<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单位名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位简称" prop="orgShort">
        <el-input
          v-model="queryParams.orgShort"
          placeholder="请输入单位简称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮编" prop="postCode">
        <el-input
          v-model="queryParams.postCode"
          placeholder="请输入邮编"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="telephone">
        <el-input
          v-model="queryParams.telephone"
          placeholder="请输入电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人" prop="corporation">
        <el-input
          v-model="queryParams.corporation"
          placeholder="请输入法人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人电话" prop="corporationPhone">
        <el-input
          v-model="queryParams.corporationPhone"
          placeholder="请输入法人电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人身份证" prop="corporationIdCard">
        <el-input
          v-model="queryParams.corporationIdCard"
          placeholder="请输入法人身份证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册地址" prop="registeredAddress">
        <el-input
          v-model="queryParams.registeredAddress"
          placeholder="请输入注册地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="县" prop="county">
        <el-input
          v-model="queryParams.county"
          placeholder="请输入县"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:org:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:org:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:org:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:org:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orgList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="orgId" />
      <el-table-column label="单位名称" align="center" prop="orgName" />
      <el-table-column label="单位简称" align="center" prop="orgShort" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="邮编" align="center" prop="postCode" />
      <el-table-column label="电话" align="center" prop="telephone" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="法人" align="center" prop="corporation" />
      <el-table-column label="法人电话" align="center" prop="corporationPhone" />
      <el-table-column label="法人身份证" align="center" prop="corporationIdCard" />
      <el-table-column label="注册地址" align="center" prop="registeredAddress" />
      <el-table-column label="省" align="center" prop="province" />
      <el-table-column label="市" align="center" prop="city" />
      <el-table-column label="县" align="center" prop="county" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:org:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:org:remove']"
          >删除</el-button>
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

    <!-- 添加或修改组织机构对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="单位名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入单位名称" />
        </el-form-item>
        <el-form-item label="单位简称" prop="orgShort">
          <el-input v-model="form.orgShort" placeholder="请输入单位简称" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="邮编" prop="postCode">
          <el-input v-model="form.postCode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="电话" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="法人" prop="corporation">
          <el-input v-model="form.corporation" placeholder="请输入法人" />
        </el-form-item>
        <el-form-item label="法人电话" prop="corporationPhone">
          <el-input v-model="form.corporationPhone" placeholder="请输入法人电话" />
        </el-form-item>
        <el-form-item label="法人身份证" prop="corporationIdCard">
          <el-input v-model="form.corporationIdCard" placeholder="请输入法人身份证" />
        </el-form-item>
        <el-form-item label="注册地址" prop="registeredAddress">
          <el-input v-model="form.registeredAddress" placeholder="请输入注册地址" />
        </el-form-item>
        <el-form-item label="省" prop="province">
          <el-input v-model="form.province" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="city">
          <el-input v-model="form.city" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="县" prop="county">
          <el-input v-model="form.county" placeholder="请输入县" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrg, getOrg, delOrg, addOrg, updateOrg } from "@/api/system/org";

export default {
  name: "Org",
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
      // 组织机构表格数据
      orgList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgName: null,
        orgShort: null,
        address: null,
        postCode: null,
        telephone: null,
        description: null,
        corporation: null,
        corporationPhone: null,
        corporationIdCard: null,
        registeredAddress: null,
        province: null,
        city: null,
        county: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询组织机构列表 */
    getList() {
      this.loading = true;
      listOrg(this.queryParams).then(response => {
        this.orgList = response.rows;
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
        orgId: null,
        orgName: null,
        orgShort: null,
        address: null,
        postCode: null,
        telephone: null,
        description: null,
        corporation: null,
        corporationPhone: null,
        corporationIdCard: null,
        registeredAddress: null,
        province: null,
        city: null,
        county: null,
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
      this.ids = selection.map(item => item.orgId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加组织机构";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orgId = row.orgId || this.ids
      getOrg(orgId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改组织机构";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orgId != null) {
            updateOrg(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrg(this.form).then(response => {
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
      const orgIds = row.orgId || this.ids;
      this.$modal.confirm('是否确认删除组织机构编号为"' + orgIds + '"的数据项？').then(function() {
        return delOrg(orgIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/org/export', {
        ...this.queryParams
      }, `org_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
