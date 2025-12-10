<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="电影标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入电影标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电影导演" prop="director">
        <el-input
          v-model="queryParams.director"
          placeholder="请输入电影导演"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主要演员" prop="actors">
        <el-input
          v-model="queryParams.actors"
          placeholder="请输入主要演员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电影类型" prop="genre">
        <el-input
          v-model="queryParams.genre"
          placeholder="请输入电影类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电影时长(分)" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入电影时长(分)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电影上映日期" prop="releaseDate">
        <el-date-picker clearable
          v-model="queryParams.releaseDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择电影上映日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="电影评分" prop="rating">
        <el-input
          v-model="queryParams.rating"
          placeholder="请输入电影评分"
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
          v-hasPermi="['system:movies:add']"
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
          v-hasPermi="['system:movies:edit']"
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
          v-hasPermi="['system:movies:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:movies:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="moviesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="电影序号" align="center" prop="movieId" />
      <el-table-column label="电影标题" align="center" prop="title" />
      <el-table-column label="电影导演" align="center" prop="director" />
      <el-table-column label="主要演员" align="center" prop="actors" />
      <el-table-column label="电影类型" align="center" prop="genre" />
      <el-table-column label="电影时长(分)" align="center" prop="duration" />
      <el-table-column label="电影上映日期" align="center" prop="releaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电影剧情简介" align="center" prop="description" />
      <el-table-column label="电影评分" align="center" prop="rating" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:movies:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:movies:remove']"
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

    <!-- 添加或修改电影信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="电影标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入电影标题" />
        </el-form-item>
        <el-form-item label="电影导演" prop="director">
          <el-input v-model="form.director" placeholder="请输入电影导演" />
        </el-form-item>
        <el-form-item label="主要演员" prop="actors">
          <el-input v-model="form.actors" placeholder="请输入主要演员" />
        </el-form-item>
        <el-form-item label="电影类型" prop="genre">
          <el-input v-model="form.genre" placeholder="请输入电影类型" />
        </el-form-item>
        <el-form-item label="电影时长(分)" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入电影时长(分)" />
        </el-form-item>
        <el-form-item label="电影上映日期" prop="releaseDate">
          <el-date-picker clearable
            v-model="form.releaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择电影上映日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电影剧情简介" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="电影评分" prop="rating">
          <el-input v-model="form.rating" placeholder="请输入电影评分" />
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
import { listMovies, getMovies, delMovies, addMovies, updateMovies } from "@/api/system/movies"

export default {
  name: "Movies",
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
      // 电影信息管理表格数据
      moviesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        director: null,
        actors: null,
        genre: null,
        duration: null,
        releaseDate: null,
        description: null,
        rating: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "电影标题不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询电影信息管理列表 */
    getList() {
      this.loading = true
      listMovies(this.queryParams).then(response => {
        this.moviesList = response.rows
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
        movieId: null,
        title: null,
        director: null,
        actors: null,
        genre: null,
        duration: null,
        releaseDate: null,
        description: null,
        rating: null
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
      this.ids = selection.map(item => item.movieId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加电影信息管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const movieId = row.movieId || this.ids
      getMovies(movieId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改电影信息管理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.movieId != null) {
            updateMovies(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addMovies(this.form).then(response => {
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
      const movieIds = row.movieId || this.ids
      this.$modal.confirm('是否确认删除电影信息管理编号为"' + movieIds + '"的数据项？').then(function() {
        return delMovies(movieIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/movies/export', {
        ...this.queryParams
      }, `movies_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
