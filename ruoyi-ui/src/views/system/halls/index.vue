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
      <el-form-item label="影厅座位总数" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入影厅座位总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="座位布局" prop="seatLayout">
        <el-input
          v-model="queryParams.seatLayout"
          placeholder="请输入座位布局"
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
          v-hasPermi="['system:halls:add']"
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
          v-hasPermi="['system:halls:edit']"
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
          v-hasPermi="['system:halls:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:halls:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hallsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="影厅序号" align="center" prop="hallId" />
      <el-table-column label="所属电影院序号" align="center" prop="cinemaId" />
      <el-table-column label="影厅座位总数" align="center" prop="capacity" />
      <el-table-column label="座位布局" align="center" prop="seatLayout" />
      <el-table-column label="影厅类型" align="center" prop="hallType" />
      <el-table-column label="影厅设施" align="center" prop="facilities" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:halls:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:halls:remove']"
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

    <!-- 添加或修改电影院内各影厅的信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属电影院序号" prop="cinemaId">
          <el-input v-model="form.cinemaId" placeholder="请输入所属电影院序号" />
        </el-form-item>
        <el-form-item label="影厅座位总数" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入影厅座位总数" />
        </el-form-item>
        <el-form-item label="座位布局" prop="seatLayout">
          <el-input v-model="form.seatLayout" placeholder="请输入座位布局" />
        </el-form-item>
        <el-form-item label="影厅设施" prop="facilities">
          <el-input v-model="form.facilities" type="textarea" placeholder="请输入内容" />
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
import { listHalls, getHalls, delHalls, addHalls, updateHalls } from "@/api/system/halls"

export default {
  name: "Halls",
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
      // 电影院内各影厅的信息表格数据
      hallsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cinemaId: null,
        capacity: null,
        seatLayout: null,
        hallType: null,
        facilities: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cinemaId: [
          { required: true, message: "所属电影院序号不能为空", trigger: "blur" }
        ],
        capacity: [
          { required: true, message: "影厅座位总数不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询电影院内各影厅的信息列表 */
    getList() {
      this.loading = true
      listHalls(this.queryParams).then(response => {
        this.hallsList = response.rows
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
        hallId: null,
        cinemaId: null,
        capacity: null,
        seatLayout: null,
        hallType: null,
        facilities: null
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
      this.ids = selection.map(item => item.hallId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加电影院内各影厅的信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const hallId = row.hallId || this.ids
      getHalls(hallId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改电影院内各影厅的信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.hallId != null) {
            updateHalls(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addHalls(this.form).then(response => {
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
      const hallIds = row.hallId || this.ids
      this.$modal.confirm('是否确认删除电影院内各影厅的信息编号为"' + hallIds + '"的数据项？').then(function() {
        return delHalls(hallIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/halls/export', {
        ...this.queryParams
      }, `halls_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
