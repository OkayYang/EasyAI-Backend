<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会话id" prop="sessionId">
        <el-input
          v-model="queryParams.sessionId"
          placeholder="请输入会话id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消息id" prop="messageId">
        <el-input
          v-model="queryParams.messageId"
          placeholder="请输入消息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模型名" prop="modelName">
        <el-input
          v-model="queryParams.modelName"
          placeholder="请输入模型名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="错误代码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入错误代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="秘钥" prop="apiKey">
        <el-input
          v-model="queryParams.apiKey"
          placeholder="请输入秘钥"
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
          v-hasPermi="['client:message_error_log:add']"
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
          v-hasPermi="['client:message_error_log:edit']"
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
          v-hasPermi="['client:message_error_log:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:message_error_log:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="message_error_logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="messageErrorLogId" />
      <el-table-column label="会话id" align="center" prop="sessionId" />
      <el-table-column label="消息id" align="center" prop="messageId" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="模型名" align="center" prop="modelName" />
      <el-table-column label="错误代码" align="center" prop="code" />
      <el-table-column label="错误类型" align="center" prop="type" />
      <el-table-column label="错误消息" align="center" prop="message" />
      <el-table-column label="秘钥" align="center" prop="apiKey" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:message_error_log:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:message_error_log:remove']"
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

    <!-- 添加或修改消息异常日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会话id" prop="sessionId">
          <el-input v-model="form.sessionId" placeholder="请输入会话id" />
        </el-form-item>
        <el-form-item label="消息id" prop="messageId">
          <el-input v-model="form.messageId" placeholder="请输入消息id" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="模型名" prop="modelName">
          <el-input v-model="form.modelName" placeholder="请输入模型名" />
        </el-form-item>
        <el-form-item label="错误代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入错误代码" />
        </el-form-item>
        <el-form-item label="错误消息" prop="message">
          <el-input v-model="form.message" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="秘钥" prop="apiKey">
          <el-input v-model="form.apiKey" placeholder="请输入秘钥" />
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
import { listMessage_error_log, getMessage_error_log, delMessage_error_log, addMessage_error_log, updateMessage_error_log } from "@/api/client/message_error_log";

export default {
  name: "Message_error_log",
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
      // 消息异常日志表格数据
      message_error_logList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sessionId: null,
        messageId: null,
        email: null,
        modelName: null,
        code: null,
        type: null,
        message: null,
        apiKey: null,
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
    /** 查询消息异常日志列表 */
    getList() {
      this.loading = true;
      listMessage_error_log(this.queryParams).then(response => {
        this.message_error_logList = response.rows;
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
        messageErrorLogId: null,
        sessionId: null,
        messageId: null,
        email: null,
        modelName: null,
        code: null,
        type: null,
        message: null,
        apiKey: null,
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
      this.ids = selection.map(item => item.messageErrorLogId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加消息异常日志";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const messageErrorLogId = row.messageErrorLogId || this.ids
      getMessage_error_log(messageErrorLogId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改消息异常日志";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.messageErrorLogId != null) {
            updateMessage_error_log(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessage_error_log(this.form).then(response => {
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
      const messageErrorLogIds = row.messageErrorLogId || this.ids;
      this.$modal.confirm('是否确认删除消息异常日志编号为"' + messageErrorLogIds + '"的数据项？').then(function() {
        return delMessage_error_log(messageErrorLogIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/message_error_log/export', {
        ...this.queryParams
      }, `message_error_log_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
