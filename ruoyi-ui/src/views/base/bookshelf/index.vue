<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="书架地址" prop="bookshelfAddress">
        <el-input
          v-model="queryParams.bookshelfAddress"
          placeholder="请输入书架地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书架容量" prop="bookshelfCapacity">
        <el-input
          v-model="queryParams.bookshelfCapacity"
          placeholder="请输入书架容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报警容量" prop="bookshelfCordon">
        <el-input
          v-model="queryParams.bookshelfCordon"
          placeholder="请输入报警容量"
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
          v-hasPermi="['base:bookshelf:add']"
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
          v-hasPermi="['base:bookshelf:edit']"
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
          v-hasPermi="['base:bookshelf:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['base:bookshelf:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookshelfList" @selection-change="handleSelectionChange" @sort-change="handleSortChange" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="书架编号" align="center" prop="bookshelfId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="书架类别" align="center" prop="bookshelfType" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="书架地址" align="center" prop="bookshelfAddress" />
      <el-table-column label="书架容量" align="center" prop="bookshelfCapacity" />
      <el-table-column label="报警容量" align="center" prop="bookshelfCordon" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:bookshelf:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:bookshelf:remove']"
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

    <!-- 添加或修改书架信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书架地址" prop="bookshelfAddress">
          <el-input v-model="form.bookshelfAddress" placeholder="请输入书架地址" />
        </el-form-item>
        <el-form-item label="书架容量" prop="bookshelfCapacity">
          <el-input v-model="form.bookshelfCapacity" placeholder="请输入书架容量" />
        </el-form-item>
        <el-form-item label="报警容量" prop="bookshelfCordon">
          <el-input v-model="form.bookshelfCordon" placeholder="请输入报警容量" />
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
import { listBookshelf, getBookshelf, delBookshelf, addBookshelf, updateBookshelf } from "@/api/base/bookshelf";

export default {
  name: "Bookshelf",
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
      // 书架信息表格数据
      bookshelfList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookshelfType: null,
        bookshelfAddress: null,
        bookshelfCapacity: null,
        bookshelfCordon: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookshelfType: [
          { required: true, message: "书架类别不能为空", trigger: "change" }
        ],
        bookshelfAddress: [
          { required: true, message: "书架地址不能为空", trigger: "blur" }
        ],
        bookshelfCapacity: [
          { required: true, message: "书架容量不能为空", trigger: "blur" }
        ],
        bookshelfCordon: [
          { required: true, message: "报警容量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询书架信息列表 */
    getList() {
      this.loading = true;
      listBookshelf(this.queryParams).then(response => {
        this.bookshelfList = response.rows;
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
        bookshelfId: null,
        bookshelfType: null,
        bookshelfAddress: null,
        bookshelfCapacity: null,
        bookshelfCordon: null
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
    // 点击进行排序
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.bookshelfId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加书架信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bookshelfId = row.bookshelfId || this.ids
      getBookshelf(bookshelfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改书架信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bookshelfId != null) {
            updateBookshelf(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBookshelf(this.form).then(response => {
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
      const bookshelfIds = row.bookshelfId || this.ids;
      this.$modal.confirm('是否确认删除书架信息编号为"' + bookshelfIds + '"的数据项？').then(function() {
        return delBookshelf(bookshelfIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('base/bookshelf/export', {
        ...this.queryParams
      }, `bookshelf_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
