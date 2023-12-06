<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="出版名称" prop="publisherName">
        <el-input
          v-model="queryParams.publisherName"
          placeholder="请输入出版名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版社地址" prop="publisherAddress">
        <el-input
          v-model="queryParams.publisherAddress"
          placeholder="请输入出版社地址"
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
          v-hasPermi="['base:publisher:add']"
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
          v-hasPermi="['base:publisher:edit']"
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
          v-hasPermi="['base:publisher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['base:publisher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="publisherList" @selection-change="handleSelectionChange" @sort-change="handleSortChange" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出版社编号" align="center" prop="publisherId" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="出版名称" align="center" prop="publisherName" :sort-order="['descending','ascending']" sortable="custom" />
      <el-table-column label="出版社地址" align="center" prop="publisherAddress" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['base:publisher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['base:publisher:remove']"
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

    <!-- 添加或修改出版社信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="出版名称" prop="publisherName">
          <el-input v-model="form.publisherName" placeholder="请输入出版名称" />
        </el-form-item>
        <el-form-item label="出版社地址" prop="publisherAddress">
          <el-input v-model="form.publisherAddress" placeholder="请输入出版社地址" />
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
import { listPublisher, getPublisher, delPublisher, addPublisher, updatePublisher } from "@/api/base/publisher";

export default {
  name: "Publisher",
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
      // 出版社信息表格数据
      publisherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        publisherName: null,
        publisherAddress: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        publisherName: [
          { required: true, message: "出版名称不能为空", trigger: "blur" }
        ],
        publisherAddress: [
          { required: true, message: "出版社地址不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询出版社信息列表 */
    getList() {
      this.loading = true;
      listPublisher(this.queryParams).then(response => {
        this.publisherList = response.rows;
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
        publisherId: null,
        publisherName: null,
        publisherAddress: null
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
      this.ids = selection.map(item => item.publisherId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 点击进行排序
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出版社信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const publisherId = row.publisherId || this.ids
      getPublisher(publisherId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出版社信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.publisherId != null) {
            updatePublisher(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPublisher(this.form).then(response => {
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
      const publisherIds = row.publisherId || this.ids;
      this.$modal.confirm('是否确认删除出版社信息编号为"' + publisherIds + '"的数据项？').then(function() {
        return delPublisher(publisherIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('publisher/publisher/export', {
        ...this.queryParams
      }, `publisher_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
