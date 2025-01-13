<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="卡密" prop="cardKey">
        <el-input v-model="queryParams.cardKey" placeholder="请输入卡密" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="算力奖励" prop="reward">
        <el-input v-model="queryParams.reward" placeholder="请输入算力奖励" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="过期时间" prop="expireTime">
        <el-date-picker v-model="queryParams.expireTime" type="date" value-format="yyyy-MM-dd" clearable placeholder="请选择过期时间" />
      </el-form-item>
      <el-form-item label="卡密类型" prop="category">
        <el-input v-model="queryParams.category" placeholder="请输入卡密类型" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="卡密状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择卡密状态" clearable>
          <el-option v-for="dict in dict.type.easyai_card_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['client:card_key:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleBatchAdd" v-hasPermi="['client:card_key:add']">批量新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['client:card_key:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['client:card_key:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['client:card_key:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 卡密列表 -->
    <el-table v-loading="loading" :data="card_keyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="cardKeyId" />
      <el-table-column label="卡密" align="center" prop="cardKey" />
      <el-table-column label="算力奖励" align="center" prop="reward" />
      <el-table-column label="过期时间" align="center" prop="expireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="卡密类型" align="center" prop="category" />
      <el-table-column label="卡密状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.easyai_card_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['client:card_key:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['client:card_key:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 新增或修改卡密对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="卡密" prop="cardKey">
          <el-input v-model="form.cardKey" placeholder="请输入卡密" />
        </el-form-item>
        <el-form-item label="算力奖励" prop="reward">
          <el-input v-model="form.reward" placeholder="请输入算力奖励" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expireTime">
          <el-date-picker v-model="form.expireTime" type="date" value-format="yyyy-MM-dd" clearable placeholder="请选择过期时间" />
        </el-form-item>
        <el-form-item label="卡密类型" prop="category">
          <el-input v-model="form.category" placeholder="请输入卡密类型" />
        </el-form-item>
        <el-form-item label="卡密状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.easyai_card_status" :key="dict.value" :label="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 批量新增卡密对话框 -->
    <el-dialog v-bind="$attrs" @open="onBatchOpen" @close="onBatchClose" title="批量新增卡密"  :visible.sync="batchOpen" width="500px">
      <el-form ref="batchForm" :model="batchFormData" :rules="batchRules" size="medium" label-width="150px">
        <el-form-item label="算力奖励" prop="reward">
          <el-input v-model="batchFormData.reward" placeholder="请输入算力奖励" clearable :style="{width: '100%'}" />
        </el-form-item>
        <el-form-item label="卡密生成数量" prop="generateNum">
          <el-input v-model="batchFormData.generateNum" placeholder="请输入卡密生成数量" clearable :style="{width: '100%'}" />
        </el-form-item>
        <el-form-item label="卡密类别" prop="category">
          <el-input v-model="batchFormData.category" placeholder="请输入卡密类别" clearable :style="{width: '100%'}" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expireTime">
          <el-date-picker v-model="batchFormData.expireTime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" :style="{width: '100%'}" placeholder="请选择过期时间" clearable />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitBatch">确 定</el-button>
        <el-button @click="cancelBatch">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import {
  listCard_key,
  getCard_key,
  delCard_key,
  addCard_key,
  updateCard_key,
  addBatchCard_key
} from "@/api/client/card_key";

export default {
  name: "Card_key",
  dicts: ['easyai_card_status'],
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
      // 卡密表格数据
      card_keyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cardKey: null,
        reward: null,
        expireTime: null,
        category: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      batchOpen: false,
      batchFormData: {
        reward: "",
        generateNum: "",
        category: "",
        expireTime: "",
      },
      batchRules: {
        reward: [
          { required: true, message: "请输入算力奖励", trigger: "blur" },
          { validator: (rule, value, callback) => {
              if (value <= 0) {
                callback(new Error("算力奖励必须大于0"));
              } else {
                callback();
              }
            }, trigger: "blur"
          }
        ],
        generateNum: [
          { required: true, message: "请输入生成数量", trigger: "blur" },
          { validator: (rule, value, callback) => {
              if (!Number.isInteger(Number(value)) || value <= 0) {
                callback(new Error("卡密生成数量必须为大于0的整数"));
              } else {
                callback();
              }
            }, trigger: "blur"
          }
        ],
        category: [{ required: true, message: "请输入卡密类别", trigger: "blur" }],
        expireTime: [{ required: true, message: "请选择过期时间", trigger: "change" }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询卡密列表 */
    getList() {
      this.loading = true;
      listCard_key(this.queryParams).then(response => {
        this.card_keyList = response.rows;
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
        cardKeyId: null,
        cardKey: null,
        reward: null,
        createTime: null,
        expireTime: null,
        category: null,
        status: null
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
      this.ids = selection.map(item => item.cardKeyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加卡密";
    },
    handleBatchAdd(){
      this.reset();
      this.batchOpen = true;
      this.title = "批量添加卡密";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cardKeyId = row.cardKeyId || this.ids
      getCard_key(cardKeyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改卡密";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cardKeyId != null) {
            updateCard_key(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCard_key(this.form).then(response => {
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
      const cardKeyIds = row.cardKeyId || this.ids;
      this.$modal.confirm('是否确认删除卡密编号为"' + cardKeyIds + '"的数据项？').then(function() {
        return delCard_key(cardKeyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/card_key/export', {
        ...this.queryParams
      }, `card_key_${new Date().getTime()}.xlsx`)
    },
    submitBatch() {
      this.$refs.batchForm.validate((valid) => {
        if (valid) {
          addBatchCard_key(this.batchFormData).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.getList();
          });
          // 提交批量新增卡密的请求
          this.batchOpen = false;
        }
      });
    },
    cancelBatch() {
      this.batchOpen = false;
    },
    onBatchOpen() {
      this.batchOpen=true;
      this.batchFormData = {
        reward: "",
        generateNum: "",
        category: "",
        expireTime: "",
      };
    },
    onBatchClose() {
      this.batchOpen = false;
    },
  }


};
</script>
