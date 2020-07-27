<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80" type="index" />

      <el-table-column width="120px" align="center" label="用户名">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" alt="" srcset="" style="width: 50px;height:50px;">
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" align="center" label="手机" width="110">
        <template slot-scope="scope">
          <span>
            {{ scope.row.iphone }}
          </span>
        </template>
      </el-table-column>

      <el-table-column width="50px" align="center" label="性别">
        <template slot-scope="scope">
          <span>{{ scope.row.sex * 1 === 1 ? '男' : '女' }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160" align="center" label="邮箱">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160px" align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{ scope.row.created }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160px" align="center" label="最后登录">
        <template slot-scope="scope">
          <span>{{ scope.row.lastLogin }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" min-width="120">
        <template slot-scope="scope">
          <router-link :to="`/${scope.row.id}/edituser`">
            <el-button type="primary" size="small" icon="el-icon-edit">
              编辑
            </el-button>
          </router-link>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="delUser(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.pageSize" @pagination="getUserList" />
  </div>
</template>

<script>
import { getUserList, delUserById } from '@/api/user'
import Pagination from '@/components/Pagination'
export default {
  name: 'UserList',
  components: { Pagination },
  data() {
    return {
      list: [],
      listLoading: true,
      total: 0,
      listQuery: {
        currentPage: 1,
        pageSize: 5
      }
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    // 得到用户列表
    getUserList() {
      this.listLoading = true
      getUserList(this.listQuery).then(res => {
        console.log(res)
        this.list = res.data.records
        this.listQuery.currentPage = res.data.current
        this.listQuery.pageSize = res.data.size
        this.total = res.data.total
      })
      this.listLoading = false
    },
    // 删除用户
    delUser(row) {
      console.log(row)
      delUserById(row).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
        }
        location.reload()
      })
    }
  }
}
</script>

<style scoped>

</style>
