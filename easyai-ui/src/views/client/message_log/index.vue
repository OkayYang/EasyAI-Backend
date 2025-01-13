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
      <el-form-item label="输入token" prop="inputToken">
        <el-input
          v-model="queryParams.inputToken"
          placeholder="请输入输入token"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="输出token" prop="outputToken">
        <el-input
          v-model="queryParams.outputToken"
          placeholder="请输入输出token"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计token" prop="totalToken">
        <el-input
          v-model="queryParams.totalToken"
          placeholder="请输入合计token"
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
          v-hasPermi="['client:message_log:add']"
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
          v-hasPermi="['client:message_log:edit']"
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
          v-hasPermi="['client:message_log:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:message_log:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="message_logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="消息日志id" align="center" prop="messageLogId" />
      <el-table-column label="会话id" align="center" prop="sessionId" />
      <el-table-column label="消息id" align="center" prop="messageId" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="输入token" align="center" prop="inputToken" />
      <el-table-column label="输出token" align="center" prop="outputToken" />
      <el-table-column label="合计token" align="center" prop="totalToken" />
      <el-table-column label="模型名" align="center" prop="modelName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:message_log:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:message_log:remove']"
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

    <!-- 添加或修改消息日志对话框 -->
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
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="输入token" prop="inputToken">
          <el-input v-model="form.inputToken" placeholder="请输入输入token" />
        </el-form-item>
        <el-form-item label="输出token" prop="outputToken">
          <el-input v-model="form.outputToken" placeholder="请输入输出token" />
        </el-form-item>
        <el-form-item label="合计token" prop="totalToken">
          <el-input v-model="form.totalToken" placeholder="请输入合计token" />
        </el-form-item>
        <el-form-item label="模型名" prop="modelName">
          <el-input v-model="form.modelName" placeholder="请输入模型名" />
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
import { listMessage_log, getMessage_log, delMessage_log, addMessage_log, updateMessage_log } from "@/api/client/message_log";

export default {
  name: "Message_log",
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
      // 消息日志表格数据
      message_logList: [],
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
        content: null,
        inputToken: null,
        outputToken: null,
        totalToken: null,
        modelName: null,
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
    /** 查询消息日志列表 */
    getList() {
      this.loading = true;
      listMessage_log(this.queryParams).then(response => {
        this.message_logList = response.rows;
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
        messageLogId: null,
        sessionId: null,
        messageId: null,
        email: null,
        content: null,
        inputToken: null,
        outputToken: null,
        totalToken: null,
        modelName: null,
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
      this.ids = selection.map(item => item.messageLogId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加消息日志";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const messageLogId = row.messageLogId || this.ids
      getMessage_log(messageLogId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改消息日志";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.messageLogId != null) {
            updateMessage_log(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessage_log(this.form).then(response => {
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
      const messageLogIds = row.messageLogId || this.ids;
      this.$modal.confirm('是否确认删除消息日志编号为"' + messageLogIds + '"的数据项？').then(function() {
        return delMessage_log(messageLogIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/message_log/export', {
        ...this.queryParams
      }, `message_log_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
