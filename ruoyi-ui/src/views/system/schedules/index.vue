<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属电影院序号" prop="cinemaId">
        <el-input
          v-model="queryParams.cinemaId"
          placeholder="请输入所属电影院序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放映影厅序号" prop="hallId">
        <el-input
          v-model="queryParams.hallId"
          placeholder="请输入放映影厅序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放映电影序号" prop="movieId">
        <el-input
          v-model="queryParams.movieId"
          placeholder="请输入放映电影序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放映时间" prop="showTime">
        <el-date-picker clearable
          v-model="queryParams.showTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择放映时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="票价" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入票价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余可售座位数" prop="seatsAvailable">
        <el-input
          v-model="queryParams.seatsAvailable"
          placeholder="请输入剩余可售座位数"
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
          v-hasPermi="['system:schedules:add']"
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
          v-hasPermi="['system:schedules:edit']"
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
          v-hasPermi="['system:schedules:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:schedules:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="schedulesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="排片记录序号" align="center" prop="scheduleId" />
      <el-table-column label="所属电影院序号" align="center" prop="cinemaId" />
      <el-table-column label="放映影厅序号" align="center" prop="hallId" />
      <el-table-column label="放映电影序号" align="center" prop="movieId" />
      <el-table-column label="放映时间" align="center" prop="showTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.showTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="票价" align="center" prop="price" />
      <el-table-column label="剩余可售座位数" align="center" prop="seatsAvailable" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:schedules:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:schedules:remove']"
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

    <!-- 添加或修改电影具体放映安排对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属电影院序号" prop="cinemaId">
          <el-input v-model="form.cinemaId" placeholder="请输入所属电影院序号" />
        </el-form-item>
        <el-form-item label="放映影厅序号" prop="hallId">
          <el-input v-model="form.hallId" placeholder="请输入放映影厅序号" />
        </el-form-item>
        <el-form-item label="放映电影序号" prop="movieId">
          <el-input v-model="form.movieId" placeholder="请输入放映电影序号" />
        </el-form-item>
        <el-form-item label="放映时间" prop="showTime">
          <el-date-picker clearable
            v-model="form.showTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择放映时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="票价" prop="price">
          <el-input v-model="form.price" placeholder="请输入票价" />
        </el-form-item>
        <el-form-item label="剩余可售座位数" prop="seatsAvailable">
          <el-input v-model="form.seatsAvailable" placeholder="请输入剩余可售座位数" />
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
import { listSchedules, getSchedules, delSchedules, addSchedules, updateSchedules } from "@/api/system/schedules"

export default {
  name: "Schedules",
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
      // 电影具体放映安排表格数据
      schedulesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cinemaId: null,
        hallId: null,
        movieId: null,
        showTime: null,
        price: null,
        seatsAvailable: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cinemaId: [
          { required: true, message: "所属电影院序号不能为空", trigger: "blur" }
        ],
        hallId: [
          { required: true, message: "放映影厅序号不能为空", trigger: "blur" }
        ],
        movieId: [
          { required: true, message: "放映电影序号不能为空", trigger: "blur" }
        ],
        showTime: [
          { required: true, message: "放映时间不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "票价不能为空", trigger: "blur" }
        ],
        seatsAvailable: [
          { required: true, message: "剩余可售座位数不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询电影具体放映安排列表 */
    getList() {
      this.loading = true
      listSchedules(this.queryParams).then(response => {
        this.schedulesList = response.rows
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
        scheduleId: null,
        cinemaId: null,
        hallId: null,
        movieId: null,
        showTime: null,
        price: null,
        seatsAvailable: null
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
      this.ids = selection.map(item => item.scheduleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加电影具体放映安排"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const scheduleId = row.scheduleId || this.ids
      getSchedules(scheduleId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改电影具体放映安排"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scheduleId != null) {
            updateSchedules(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addSchedules(this.form).then(response => {
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
      const scheduleIds = row.scheduleId || this.ids
      this.$modal.confirm('是否确认删除电影具体放映安排编号为"' + scheduleIds + '"的数据项？').then(function() {
        return delSchedules(scheduleIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/schedules/export', {
        ...this.queryParams
      }, `schedules_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
