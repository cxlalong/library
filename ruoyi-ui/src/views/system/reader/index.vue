<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="读者证号" prop="readerNo">
        <el-input
          v-model="queryParams.readerNo"
          placeholder="请输入读者证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="读者名称" prop="readerName">
        <el-input
          v-model="queryParams.readerName"
          placeholder="请输入读者名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="读者电话" prop="readerPhone">
        <el-input
          v-model="queryParams.readerPhone"
          placeholder="请输入读者电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="读者邮箱" prop="readerEmail">
        <el-input
          v-model="queryParams.readerEmail"
          placeholder="请输入读者邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="读者身份" prop="identity">
        <el-input
          v-model="queryParams.identity"
          placeholder="请输入读者身份"
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
          v-hasPermi="['system:reader:add']"
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
          v-hasPermi="['system:reader:edit']"
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
          v-hasPermi="['system:reader:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:reader:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="readerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="读者编号" align="center" prop="readerId" />
      <el-table-column label="读者证号" align="center" prop="readerNo" />
      <el-table-column label="读者名称" align="center" prop="readerName" />
      <el-table-column label="读者电话" align="center" prop="readerPhone" />
      <el-table-column label="读者邮箱" align="center" prop="readerEmail" />
      <el-table-column label="读者身份" align="center" prop="identity" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:reader:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:reader:remove']"
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

    <!-- 添加或修改读者信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="读者证号" prop="readerNo">
          <el-input v-model="form.readerNo" placeholder="请输入读者证号" />
        </el-form-item>
        <el-form-item label="读者名称" prop="readerName">
          <el-input v-model="form.readerName" placeholder="请输入读者名称" />
        </el-form-item>
        <el-form-item label="读者电话" prop="readerPhone">
          <el-input v-model="form.readerPhone" placeholder="请输入读者电话" />
        </el-form-item>
        <el-form-item label="读者邮箱" prop="readerEmail">
          <el-input v-model="form.readerEmail" placeholder="请输入读者邮箱" />
        </el-form-item>
        <el-form-item label="读者身份" prop="identity">
          <el-input v-model="form.identity" placeholder="请输入读者身份" />
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
import { listReader, getReader, delReader, addReader, updateReader } from "@/api/system/reader";

export default {
  name: "Reader",
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
      // 读者信息表格数据
      readerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        readerNo: null,
        readerName: null,
        readerPhone: null,
        readerEmail: null,
        identity: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        readerNo: [
          { required: true, message: "读者证号不能为空", trigger: "blur" }
        ],
        readerName: [
          { required: true, message: "读者名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询读者信息列表 */
    getList() {
      this.loading = true;
      listReader(this.queryParams).then(response => {
        this.readerList = response.rows;
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
        readerId: null,
        readerNo: null,
        readerName: null,
        readerPhone: null,
        readerEmail: null,
        identity: null
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
      this.ids = selection.map(item => item.readerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加读者信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const readerId = row.readerId || this.ids
      getReader(readerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改读者信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.readerId != null) {
            updateReader(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReader(this.form).then(response => {
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
      const readerIds = row.readerId || this.ids;
      this.$modal.confirm('是否确认删除读者信息编号为"' + readerIds + '"的数据项？').then(function() {
        return delReader(readerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/reader/export', {
        ...this.queryParams
      }, `reader_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
