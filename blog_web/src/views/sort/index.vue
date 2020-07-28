<template>
  <el-container>
    <el-main>
      <el-row :gutter="20">
        <el-col :xs="12" :sm="8" :md="8" :lg="6" :xl="6" v-for="item in sorts" :key="item.id">
          <div class="content">
            <router-link :to="`/blogsort/${item.name}`">
              <img :src="item.img" class="sortImg">
              <h4>{{ item.name }}</h4>
              <p>{{ item.value }} 文章</p>
            </router-link>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import { getBlogCountBySort } from '@/api/blog'
export default {
  data() {
    return {
      sorts: []
    }
  },
  created() {
    // 获得分类
    getBlogCountBySort().then(res => {
      this.sorts = res.data.blogCountBySort
    })
  },
  methods: {
    toList(name) {
      alert(name)
      this.$route.push('/blogs')
    }
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
      .sortImg{
        margin: -45px auto 10px;
        width: 60px;
        height: 60px;
        display: block;
      }
      h4{
        color: #551A8B;
      }
      p{
        color: #000;
      }
    }
  }
}
</style>
