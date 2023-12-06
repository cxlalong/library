<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书名" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入图书名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入库时间" prop="inboundTime">
        <el-date-picker clearable
                        v-model="queryParams.inboundTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择入库时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="经手人" prop="inboundHandler">
        <el-input
          v-model="queryParams.inboundHandler"
          placeholder="请输入经手人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="在库状态" prop="inboundBookStatu">
        <el-input
          v-model="queryParams.inboundBookStatu"
          placeholder="请输入在库状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上架状态" prop="inboundStatu">
        <el-input
          v-model="queryParams.inboundStatu"
          placeholder="请输入上架状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书架状态" prop="bookshelfId">
        <el-input
          v-model="queryParams.bookshelfId"
          placeholder="请输入书架状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="inboundRemarks">
        <el-input
          v-model="queryParams.inboundRemarks"
          placeholder="请输入备注"
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
          v-hasPermi="['inbound:inbound:add']"
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
          v-hasPermi="['inbound:inbound:edit']"
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
          v-hasPermi="['inbound:inbound:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inbound:inbound:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inboundList" @selection-change="handleSelectionChange" @sort-change="handleSortChange" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="入库编号" align="center" prop="inboundId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="图书名" align="center" prop="bookId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="入库时间" align="center" prop="inboundTime" width="180" :sort-order="['descending','ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inboundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="经手人" align="center" prop="inboundHandler" />
      <el-table-column label="在库状态" align="center" prop="inboundBookStatu" />
      <el-table-column label="上架状态" align="center" prop="inboundStatu" />
      <el-table-column label="书架状态" align="center" prop="bookshelfId" />
      <el-table-column label="备注" align="center" prop="inboundRemarks" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inbound:inbound:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inbound:inbound:remove']"
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

    <!-- 添加或修改入库管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书名" prop="bookId">
          <el-input v-model="form.bookId" placeholder="请输入图书名" />
        </el-form-item>
        <el-form-item label="入库时间" prop="inboundTime">
          <el-date-picker clearable
                          v-model="form.inboundTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入库时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经手人" prop="inboundHandler">
          <el-input v-model="form.inboundHandler" placeholder="请输入经手人" />
        </el-form-item>
        <el-form-item label="在库状态" prop="inboundBookStatu">
          <el-input v-model="form.inboundBookStatu" placeholder="请输入在库状态" />
        </el-form-item>
        <el-form-item label="上架状态" prop="inboundStatu">
          <el-input v-model="form.inboundStatu" placeholder="请输入上架状态" />
        </el-form-item>
        <el-form-item label="书架状态" prop="bookshelfId">
          <el-input v-model="form.bookshelfId" placeholder="请输入书架状态" />
        </el-form-item>
        <el-form-item label="备注" prop="inboundRemarks">
          <el-input v-model="form.inboundRemarks" placeholder="请输入备注" />
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
import { listInbound, getInbound, delInbound, addInbound, updateInbound } from "@/api/inbound/inbound";

export default {
  name: "Inbound",
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
      // 入库管理表格数据
      inboundList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookId: null,
        inboundTime: null,
        inboundHandler: null,
        inboundBookStatu: null,
        inboundStatu: null,
        bookshelfId: null,
        inboundRemarks: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookId: [
          { required: true, message: "图书名不能为空", trigger: "blur" }
        ],
        inboundTime: [
          { required: true, message: "入库时间不能为空", trigger: "blur" }
        ],
        inboundHandler: [
          { required: true, message: "经手人不能为空", trigger: "blur" }
        ],
        inboundBookStatu: [
          { required: true, message: "在库状态不能为空", trigger: "blur" }
        ],
        inboundStatu: [
          { required: true, message: "上架状态不能为空", trigger: "blur" }
        ],
        bookshelfId: [
          { required: true, message: "书架状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询入库管理列表 */
    getList() {
      this.loading = true;
      listInbound(this.queryParams).then(response => {
        this.inboundList = response.rows;
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
        inboundId: null,
        bookId: null,
        inboundTime: null,
        inboundHandler: null,
        inboundBookStatu: null,
        inboundStatu: null,
        bookshelfId: null,
        inboundRemarks: null
      };
      this.resetForm("form");
    },
    // 点击进行排序
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
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
      this.ids = selection.map(item => item.inboundId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加入库管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inboundId = row.inboundId || this.ids
      getInbound(inboundId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改入库管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inboundId != null) {
            updateInbound(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInbound(this.form).then(response => {
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
      const inboundIds = row.inboundId || this.ids;
      this.$modal.confirm('是否确认删除入库管理编号为"' + inboundIds + '"的数据项？').then(function() {
        return delInbound(inboundIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inbound/inbound/export', {
        ...this.queryParams
      }, `inbound_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
