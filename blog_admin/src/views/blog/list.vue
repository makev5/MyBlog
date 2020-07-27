<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input
        v-model="title"
        placeholder="标题"
        style="width: 200px;margin-right: 10px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />
      <el-select
        v-model="type"
        placeholder="类型"
        clearable
        class="filter-item"
        style="width: 130px;margin-right: 10px;"
        @change="handleFilter"
      >
        <el-option
          v-for="item in sortArr"
          :key="item.id"
          :label="item.name"
          :value="item.name"
        />
      </el-select>

      <el-button
        class="filter-item"
        style="margin-right: 10px;"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >搜索</el-button>

      <router-link to="/blog/add">
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-edit"
        >添加</el-button>
      </router-link>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80" type="index" />

      <el-table-column width="180px" align="center" label="标题">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="作者">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="博客封面">
        <template slot-scope="scope">
          <img :src="scope.row.cover" alt="" srcset="" width="50px" height="50px">
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" align="center" label="分类" width="110">
        <template slot-scope="scope">
          <span>
            {{ scope.row.sortName }}
          </span>
        </template>
      </el-table-column>

      <el-table-column min-width="200px" align="center" label="标签">
        <template slot-scope="scope">
          <span>{{ scope.row.tabName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160" align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{ $moment(scope.row.created).format('yyyy-MM-DD') }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <router-link :to="`/${scope.row.id}/editblog`">
            <el-button type="primary" size="small" icon="el-icon-edit">
              编辑
            </el-button>
          </router-link>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="delBlog(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList, deleteBlog, getBlogCountBySort, getBlogBySearch } from '@/api/article'
import Pagination from '@/components/Pagination'
export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    formatTime(val) {
      var split = val.split('T')
      var month = split[0].split('-')
      var realTime = month[0] + '-' + month[1] + '-' + month[2] + ' ' + split[1]
      return realTime
    }
  },
  data() {
    return {
      title: '',
      type: '',
      sortArr: [],
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        currentPage: 1,
        pageSize: 5
      }
    }
  },
  created() {
    this.getList()
    getBlogCountBySort().then(res => {
      this.sortArr = res.data.blogCountBySort
    })
  },
  methods: {
    handleFilter() {
      this.listLoading = true
      console.log(this.title, this.type)
      getBlogBySearch(this.title, this.type).then(res => {
        console.log(res)
        this.list = res.data.records
        this.listQuery.currentPage = res.data.current
        this.listQuery.pageSize = res.data.size
        this.total = res.data.total
        this.listLoading = false
      })
    },
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listQuery.currentPage = response.data.current
        this.listQuery.pageSize = response.data.size
        this.total = response.data.total
        this.listLoading = false
      })
    },
    delBlog(id) {
      this.$confirm('此操作将永久删除该博客, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteBlog(id).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        location.reload()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>

<style>
.filter-container{
  margin-bottom: 10px;
}
.filter-item {
  margin-bottom: 5px;
}
.el-table .cell {
  display:-webkit-box;
  -webkit-line-clamp:3;
  -webkit-box-orient:vertical;
  overflow:hidden;
}
</style>
