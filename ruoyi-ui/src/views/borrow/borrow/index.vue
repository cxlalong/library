<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="读者编号" prop="readerNo">
        <el-input
          v-model="queryParams.readerNo"
          placeholder="请输入读者编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书籍编号" prop="inboundId">
        <el-input
          v-model="queryParams.inboundId"
          placeholder="请输入书籍编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="借书时间" prop="borrowTime">
        <el-date-picker clearable
                        v-model="queryParams.borrowTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择借书时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label=" 归还时间" prop="returnTime">
        <el-date-picker clearable
                        v-model="queryParams.returnTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择 归还时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否归还" prop="borrowStatu">
        <el-input
          v-model="queryParams.borrowStatu"
          placeholder="请输入是否归还"
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
          v-hasPermi="['borrow:borrow:add']"
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
          v-hasPermi="['borrow:borrow:edit']"
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
          v-hasPermi="['borrow:borrow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['borrow:borrow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="borrowList" @selection-change="handleSelectionChange" @sort-change="handleSortChange" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="借书id" align="center" prop="borrowId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="读者编号" align="center" prop="readerNo" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="书籍编号" align="center" prop="inboundId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="借书时间" align="center" prop="borrowTime" width="180" :sort-order="['descending','ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.borrowTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label=" 归还时间" align="center" prop="returnTime" width="180" :sort-order="['descending','ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否归还" align="center" prop="borrowStatu" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['borrow:borrow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['borrow:borrow:remove']"
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

    <!-- 添加或修改借阅信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="读者编号" prop="readerNo">
          <el-input v-model="form.readerNo" placeholder="请输入读者编号" />
        </el-form-item>
        <el-form-item label="书籍编号" prop="inboundId">
          <el-input v-model="form.inboundId" placeholder="请输入书籍编号" />
        </el-form-item>
        <el-form-item label="借书时间" prop="borrowTime">
          <el-date-picker clearable
                          v-model="form.borrowTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择借书时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label=" 归还时间" prop="returnTime">
          <el-date-picker clearable
                          v-model="form.returnTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择 归还时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否归还" prop="borrowStatu">
          <el-input v-model="form.borrowStatu" placeholder="请输入是否归还" />
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
import { listBorrow, getBorrow, delBorrow, addBorrow, updateBorrow } from "@/api/borrow/borrow";

export default {
  name: "Borrow",
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
      // 借阅信息表格数据
      borrowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        readerNo: null,
        inboundId: null,
        borrowTime: null,
        returnTime: null,
        borrowStatu: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        readerNo: [
          { required: true, message: "读者编号不能为空", trigger: "blur" }
        ],
        inboundId: [
          { required: true, message: "书籍编号不能为空", trigger: "blur" }
        ],
        borrowTime: [
          { required: true, message: "借书时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询借阅信息列表 */
    getList() {
      this.loading = true;
      listBorrow(this.queryParams).then(response => {
        this.borrowList = response.rows;
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
        borrowId: null,
        readerNo: null,
        inboundId: null,
        borrowTime: null,
        returnTime: null,
        borrowStatu: null
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
      this.ids = selection.map(item => item.borrowId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加借阅信息";
    },
    // 点击进行排序
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const borrowId = row.borrowId || this.ids
      getBorrow(borrowId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改借阅信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.borrowId != null) {
            updateBorrow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBorrow(this.form).then(response => {
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
      const borrowIds = row.borrowId || this.ids;
      this.$modal.confirm('是否确认删除借阅信息编号为"' + borrowIds + '"的数据项？').then(function() {
        return delBorrow(borrowIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('borrow/borrow/export', {
        ...this.queryParams
      }, `borrow_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
