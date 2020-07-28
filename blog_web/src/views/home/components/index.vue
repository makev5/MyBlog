<template>
  <el-container>
    <el-row>
      <el-col :xs="24" :sm="15" :md="17" :lg="18" :xl="18">
        <el-main>
        <!-- 轮播图 -->
        <Banner />
        <!-- 博客内容 -->
        <Blog />
      </el-main>
      </el-col>
      <el-col :xs="24" :sm="9" :md="7" :lg="6" :xl="6">
        <el-aside width="100%">
        <!-- 作者信息 -->
        <Mycard />
        <!-- 博客统计 -->
        <!-- <BlogView /> -->
        <!-- 推荐文章 -->
        <Recommend />
        <!-- 友情链接 -->
        <Links />
      </el-aside>
      </el-col>
    </el-row>
    <el-footer>
      <Footer />
    </el-footer>
  </el-container>
</template>

<script>
import Banner from '@/components/Banner'
import Blog from '@/components/Blog'
import Mycard from '@/components/Mycard'
import Recommend from '@/components/Recommend'
import Links from '@/components/Link'
import Footer from '@/components/Footer'
export default {
  name: 'HomeIndex',
  components: {
    Banner,
    Blog,
    Mycard,
    Recommend,
    Links,
    Footer
  }

}
</script>

<style lang="scss" scoped>
.el-container{
  margin: 60px auto;
  max-width: 1100px;
  height: 100%;
  .el-main{
    padding: 20px 10px;
  }
  .el-aside{
    padding: 20px 10px;
  }
  .el-footer{
    padding: 0;
  }
}
</style>
