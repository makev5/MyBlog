<template>
  <el-container>
    <el-main>
      <el-timeline>
        <el-timeline-item timestamp="2018/4/12" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/12 20:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/3" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/3 20:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/2" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/2 20:46</p>
          </el-card>
        </el-timeline-item>
    </el-timeline>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'TimeLine'
}
</script>

<style lang="scss" scoped>
.el-card{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
</style>
