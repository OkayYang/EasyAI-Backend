<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="平台" prop="platform">
        <el-select v-model="queryParams.platform" placeholder="请选择平台" clearable>
          <el-option
            v-for="dict in dict.type.easyai_platform"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="apiName">
        <el-input
          v-model="queryParams.apiName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="api_key" prop="apiKey">
        <el-input
          v-model="queryParams.apiKey"
          placeholder="请输入api_key"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用途" prop="purpose">
        <el-input
          v-model="queryParams.purpose"
          placeholder="请输入用途"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理地址" prop="proxyUrl">
        <el-input
          v-model="queryParams.proxyUrl"
          placeholder="请输入代理地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择启用状态" clearable>
          <el-option
            v-for="dict in dict.type.easyai_apikey_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否用完" prop="isFinished">
        <el-select v-model="queryParams.isFinished" placeholder="请选择是否用完" clearable>
          <el-option
            v-for="dict in dict.type.easyai_apikey_isfinish"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['client:apikey:add']"
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
          v-hasPermi="['client:apikey:edit']"
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
          v-hasPermi="['client:apikey:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:apikey:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apikeyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="平台" align="center" prop="platform">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_platform" :value="scope.row.platform"/>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="apiName" />
      <el-table-column label="api_key" align="center" prop="apiKey" />
      <el-table-column label="用途" align="center" prop="purpose" />
      <el-table-column label="代理地址" align="center" prop="proxyUrl" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_apikey_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="是否用完" align="center" prop="isFinished">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_apikey_isfinish" :value="scope.row.isFinished"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:apikey:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:apikey:remove']"
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

    <!-- 添加或修改密钥管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="平台" prop="platform">
          <el-radio-group v-model="form.platform">
            <el-radio
              v-for="dict in dict.type.easyai_platform"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="名称" prop="apiName">
          <el-input v-model="form.apiName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="api_key" prop="apiKey">
          <el-input v-model="form.apiKey" placeholder="请输入api_key" />
        </el-form-item>
        <el-form-item label="用途" prop="purpose">
          <el-input v-model="form.purpose" placeholder="请输入用途" />
        </el-form-item>
        <el-form-item label="代理地址" prop="proxyUrl">
          <el-input v-model="form.proxyUrl" placeholder="请输入代理地址" />
        </el-form-item>
        <el-form-item label="启用状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.easyai_apikey_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否用完" prop="isFinished">
          <el-radio-group v-model="form.isFinished">
            <el-radio
              v-for="dict in dict.type.easyai_apikey_isfinish"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listApikey, getApikey, delApikey, addApikey, updateApikey } from "@/api/client/apikey";

export default {
  name: "Apikey",
  dicts: ['easyai_apikey_isfinish', 'easyai_apikey_status', 'easyai_platform'],
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
      // 密钥管理表格数据
      apikeyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        platform: null,
        apiName: null,
        apiKey: null,
        purpose: null,
        proxyUrl: null,
        status: null,
        isFinished: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        platform: [
          { required: true, message: "平台不能为空", trigger: "change" }
        ],
        apiName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        apiKey: [
          { required: true, message: "api_key不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "启用状态不能为空", trigger: "change" }
        ],
        isFinished: [
          { required: true, message: "是否用完不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询密钥管理列表 */
    getList() {
      this.loading = true;
      listApikey(this.queryParams).then(response => {
        this.apikeyList = response.rows;
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
        id: null,
        platform: null,
        apiName: null,
        apiKey: null,
        purpose: null,
        proxyUrl: null,
        createTime: null,
        updateTime: null,
        status: null,
        isFinished: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加密钥管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApikey(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改密钥管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApikey(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApikey(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除密钥管理编号为"' + ids + '"的数据项？').then(function() {
        return delApikey(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/apikey/export', {
        ...this.queryParams
      }, `apikey_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
