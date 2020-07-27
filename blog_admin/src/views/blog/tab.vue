<template>
  <div class="app-container">
    <el-dialog title="添加标签" :visible.sync="dialogFormVisible">
      <el-form :model="tabForm">
        <el-form-item label="标签名称" label-width="120px">
          <el-input v-model="tabForm.name" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTab(tabForm)">确 定</el-button>
      </div>
    </el-dialog>

    <el-button
      class="filter-item"
      type="primary"
      style="margin-bottom: 5px;"
      size="small"
      icon="el-icon-edit"
      @click="dialogFormVisible = true"
    >添加</el-button>
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="200px" align="center" label="标签名称">
        <template slot-scope="{row}">
          <template v-if="row.edit">
            <el-input v-model="row.name" class="edit-input" size="small" />
            <el-button
              class="cancel-btn"
              size="mini"
              icon="el-icon-refresh"
              type="warning"
              @click="cancelEdit(row)"
            >
              取消
            </el-button>
          </template>
          <span v-else>{{ row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160px" align="center" label="标签图片">
        <template slot-scope="{row}">
          <img :src="row.img" alt="" srcset="" width="50px" height="50px">
        </template>
      </el-table-column>

      <el-table-column width="160px" align="center" label="创建时间">
        <template slot-scope="{row}">
          <span>{{ row.created }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160px" align="center" label="更新时间">
        <template slot-scope="{row}">
          <span>{{ row.updateTime }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="140">
        <template slot-scope="{row}">
          <el-button
            v-if="row.edit"
            type="success"
            size="mini"
            @click="confirmEdit(row)"
          >
            确定
          </el-button>
          <el-button
            v-else
            type="primary"
            size="mini"
            @click="row.edit=!row.edit"
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="mini"

            @click="delTab(row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.pageSize" @pagination="fetchTabList" />
  </div>
</template>

<script>
import { fetchTabList, editTab, delTab } from '@/api/article'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  name: 'InlineEditTable',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: [],
      tabForm: {
        name: ''
      },
      dialogFormVisible: false,
      total: 0,
      listLoading: true,
      listQuery: {
        currentPage: 1,
        pageSize: 5
      }
    }
  },
  created() {
    this.fetchTabList()
  },
  methods: {
    // 添加分类
    addTab() {
      this.dialogFormVisible = false
      console.log(this.tabForm)
      editTab(this.tabForm).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$message({
            message: '添加成功',
            type: 'success'
          })
        }
      })
    },
    // 删除标签
    delTab(row) {
      console.log(row)
      delTab(row.id).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
        }
        location.reload()
      })
    },
    async fetchTabList() {
      const _this = this
      this.listLoading = true
      const { data } = await fetchTabList(this.listQuery)
      console.log(data)
      const items = data.records
      this.list = items.map(v => {
        this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
        v.originalTitle = v.title //  will be used when user click the cancel botton
        return v
      })
      _this.listQuery.pageSize = data.size
      _this.listQuery.currentPage = data.current
      _this.total = data.total
      _this.listLoading = false
    },
    cancelEdit(row) {
      row.title = row.originalTitle
      row.edit = false
      this.$message({
        message: 'The title has been restored to the original value',
        type: 'warning'
      })
    },
    // 修改标签
    confirmEdit(row) {
      const tab = {}
      tab.id = row.id
      tab.name = row.name
      editTab(tab).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '修改成功',
            type: 'success'
          })
        }
        row.edit = false
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
.el-button{
    margin: 0 1px;
}
</style>
