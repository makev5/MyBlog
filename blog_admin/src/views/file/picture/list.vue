<template>
  <div class="app-container">
    <el-upload
      class="upload-picture"
      action="http://localhost:8081/qiniu/upload"
      :on-success="handleSuccess"
      :limit="1"
    >
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80" type="index" />

      <el-table-column width="180px" align="center" label="文件名">
        <template slot-scope="scope">
          <span>{{ scope.row.hash }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="缩略图">
        <template slot-scope="scope">
          <img :src="scope.row.url" alt="" srcset="" width="50px" height="50px">
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="文件类型">
        <template slot-scope="scope">
          <span>{{ scope.row.mimeType }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" align="center" label="文件大小" width="110">
        <template slot-scope="scope">
          <span>
            {{ (scope.row.fsize / 1024).toFixed(2) + 'KB' }}
          </span>
        </template>
      </el-table-column>

      <el-table-column min-width="200px" align="center" label="最后更新">
        <template slot-scope="scope">
          <span>{{ $moment((scope.row.putTime.toString().substring(0,13)) * 1).format('YYYY-MM-DD HH:mm:ss') }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="danger" size="small" icon="el-icon-delete" @click="delFile(scope.row.key)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { fitchList, removeFile } from '@/api/qiniu'
export default {
  name: 'Picture',
  data() {
    return {
      list: [],
      listLoading: false
    }
  },
  created() {
    fitchList().then(res => {
      this.list = res.data
    })
  },
  methods: {
    // 上传成功
    handleSuccess(response, file, fileList) {
      this.$message.success('上传成功')
      location.reload()
    },
    // 删除文件
    delFile(key) {
      removeFile(key).then(res => {
        if (res.code === 200) {
          this.$message.success('删除成功')
          location.reload()
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
