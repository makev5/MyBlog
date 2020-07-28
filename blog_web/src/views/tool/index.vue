<template>
  <el-container>
      <el-main>
          <el-row :gutter="20">
            <el-col :xs="12" :sm="8" :md="8" :lg="6" :xl="6" v-for="(item, index) in tools" :key="index">
              <div class="content">
                <a :href="item.url" target="_blank">
                  <img :src="item.img" class="toolImg">
                  <h4>{{ item.name }}</h4>
                </a>
                <p>{{ item.description }}</p>
              </div>
            </el-col>
          </el-row>
      </el-main>
  </el-container>
</template>

<script>
import { getToolList } from '@/api/blog'
export default {
  name: 'tool',
  data() {
    return {
      tools: []
    }
  },
  created() {
    getToolList().then(res => {
      this.tools = res.data
      console.log(this.tools)
    })
  }
}
</script>

<style lang="scss" scoped>
.el-container{
  text-align: center;
  .el-main{
    .el-col{
      padding: 20px 10px;
      .content {
        display: inline-block;
        width: 100%;
        background-color: #fff;
        border: 1px solid #f1f1f1;
        transition: border-color .3s;
        text-align: center;
        padding: 1.5rem 0;
      }
      h4{
        color: #551A8B;
      }
      .toolImg{
        margin: -45px auto 10px;
        width: 60px;
        height: 60px;
        display: block;
      }
    }
  }
}
</style>
