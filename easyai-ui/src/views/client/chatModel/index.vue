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
      <el-form-item label="模型名称" prop="modelName">
        <el-input
          v-model="queryParams.modelName"
          placeholder="请输入模型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模型版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入模型版本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创意度" prop="temperature">
        <el-input
          v-model="queryParams.temperature"
          placeholder="请输入创意度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大token数" prop="maxTokens">
        <el-input
          v-model="queryParams.maxTokens"
          placeholder="请输入最大token数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大上下文长度" prop="maxContext">
        <el-input
          v-model="queryParams.maxContext"
          placeholder="请输入最大上下文长度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="算力点数" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入算力点数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序优先级" prop="orderNum">
        <el-input
          v-model="queryParams.orderNum"
          placeholder="请输入排序优先级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模型状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择模型状态" clearable>
          <el-option
            v-for="dict in dict.type.easyai_model_status"
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
          v-hasPermi="['client:chatModel:add']"
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
          v-hasPermi="['client:chatModel:edit']"
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
          v-hasPermi="['client:chatModel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:chatModel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chatModelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="模型id" align="center" prop="id" />
      <el-table-column label="平台" align="center" prop="platform">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_platform" :value="scope.row.platform"/>
        </template>
      </el-table-column>
      <el-table-column label="模型名称" align="center" prop="modelName" />
      <el-table-column label="模型版本" align="center" prop="version" />
      <el-table-column label="创意度" align="center" prop="temperature" />
      <el-table-column label="最大token数" align="center" prop="maxTokens" />
      <el-table-column label="最大上下文长度" align="center" prop="maxContext" />
      <el-table-column label="算力点数" align="center" prop="price" />
      <el-table-column label="排序优先级" align="center" prop="orderNum" />
      <el-table-column label="模型状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_model_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:chatModel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:chatModel:remove']"
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

    <!-- 添加或修改AI 模型对话框 -->
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
        <el-form-item label="模型名称" prop="modelName">
          <el-input v-model="form.modelName" placeholder="请输入模型名称" />
        </el-form-item>
        <el-form-item label="模型版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入模型版本" />
        </el-form-item>
        <el-form-item label="创意度" prop="temperature">
          <el-input v-model="form.temperature" placeholder="请输入创意度" />
        </el-form-item>
        <el-form-item label="最大token数" prop="maxTokens">
          <el-input v-model="form.maxTokens" placeholder="请输入最大token数" />
        </el-form-item>
        <el-form-item label="最大上下文长度" prop="maxContext">
          <el-input v-model="form.maxContext" placeholder="请输入最大上下文长度" />
        </el-form-item>
        <el-form-item label="算力点数" prop="price">
          <el-input v-model="form.price" placeholder="请输入算力点数" />
        </el-form-item>
        <el-form-item label="排序优先级" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入排序优先级" />
        </el-form-item>
        <el-form-item label="模型状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.easyai_model_status"
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
import { listChatModel, getChatModel, delChatModel, addChatModel, updateChatModel } from "@/api/client/chatModel";

export default {
  name: "ChatModel",
  dicts: ['easyai_model_status', 'easyai_platform'],
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
      // AI 模型表格数据
      chatModelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        platform: null,
        modelName: null,
        version: null,
        temperature: null,
        maxTokens: null,
        maxContext: null,
        price: null,
        orderNum: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        platform: [
          { required: true, message: "平台不能为空", trigger: "change" }
        ],
        modelName: [
          { required: true, message: "模型名称不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "算力点数不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "排序优先级不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "模型状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询AI 模型列表 */
    getList() {
      this.loading = true;
      listChatModel(this.queryParams).then(response => {
        this.chatModelList = response.rows;
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
        modelName: null,
        version: null,
        temperature: null,
        maxTokens: null,
        maxContext: null,
        price: null,
        orderNum: null,
        status: null,
        createTime: null,
        updateTime: null
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
      this.title = "添加AI 模型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getChatModel(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改AI 模型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChatModel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChatModel(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除AI 模型编号为"' + ids + '"的数据项？').then(function() {
        return delChatModel(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/chatModel/export', {
        ...this.queryParams
      }, `chatModel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
