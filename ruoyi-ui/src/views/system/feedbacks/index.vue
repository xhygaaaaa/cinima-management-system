<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="提交反馈的用户序号" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入提交反馈的用户序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被评价的电影院序号" prop="cinemaId">
        <el-input
          v-model="queryParams.cinemaId"
          placeholder="请输入被评价的电影院序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被评价的电影序号" prop="movieId">
        <el-input
          v-model="queryParams.movieId"
          placeholder="请输入被评价的电影序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评分" prop="rating">
        <el-input
          v-model="queryParams.rating"
          placeholder="请输入评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈提交时间" prop="feedbackTime">
        <el-date-picker clearable
          v-model="queryParams.feedbackTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择反馈提交时间">
        </el-date-picker>
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
          v-hasPermi="['system:feedbacks:add']"
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
          v-hasPermi="['system:feedbacks:edit']"
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
          v-hasPermi="['system:feedbacks:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:feedbacks:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feedbacksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="反馈记录序号" align="center" prop="feedbackId" />
      <el-table-column label="提交反馈的用户序号" align="center" prop="userId" />
      <el-table-column label="被评价的电影院序号" align="center" prop="cinemaId" />
      <el-table-column label="被评价的电影序号" align="center" prop="movieId" />
      <el-table-column label="反馈内容" align="center" prop="content" />
      <el-table-column label="评分" align="center" prop="rating" />
      <el-table-column label="反馈提交时间" align="center" prop="feedbackTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.feedbackTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:feedbacks:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:feedbacks:remove']"
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

    <!-- 添加或修改用户评价反馈对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="提交反馈的用户序号" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入提交反馈的用户序号" />
        </el-form-item>
        <el-form-item label="被评价的电影院序号" prop="cinemaId">
          <el-input v-model="form.cinemaId" placeholder="请输入被评价的电影院序号" />
        </el-form-item>
        <el-form-item label="被评价的电影序号" prop="movieId">
          <el-input v-model="form.movieId" placeholder="请输入被评价的电影序号" />
        </el-form-item>
        <el-form-item label="反馈内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="评分" prop="rating">
          <el-input v-model="form.rating" placeholder="请输入评分" />
        </el-form-item>
        <el-form-item label="反馈提交时间" prop="feedbackTime">
          <el-date-picker clearable
            v-model="form.feedbackTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择反馈提交时间">
          </el-date-picker>
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
import { listFeedbacks, getFeedbacks, delFeedbacks, addFeedbacks, updateFeedbacks } from "@/api/system/feedbacks"

export default {
  name: "Feedbacks",
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
      // 用户评价反馈表格数据
      feedbacksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        cinemaId: null,
        movieId: null,
        content: null,
        rating: null,
        feedbackTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "提交反馈的用户序号不能为空", trigger: "blur" }
        ],
        cinemaId: [
          { required: true, message: "被评价的电影院序号不能为空", trigger: "blur" }
        ],
        movieId: [
          { required: true, message: "被评价的电影序号不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询用户评价反馈列表 */
    getList() {
      this.loading = true
      listFeedbacks(this.queryParams).then(response => {
        this.feedbacksList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        feedbackId: null,
        userId: null,
        cinemaId: null,
        movieId: null,
        content: null,
        rating: null,
        feedbackTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.feedbackId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加用户评价反馈"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const feedbackId = row.feedbackId || this.ids
      getFeedbacks(feedbackId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改用户评价反馈"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.feedbackId != null) {
            updateFeedbacks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFeedbacks(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const feedbackIds = row.feedbackId || this.ids
      this.$modal.confirm('是否确认删除用户评价反馈编号为"' + feedbackIds + '"的数据项？').then(function() {
        return delFeedbacks(feedbackIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/feedbacks/export', {
        ...this.queryParams
      }, `feedbacks_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
