<template>
  <el-carousel :interval="10000" trigger="click">
    <a :href="item.url" target="_blank" v-for="(item, index) in banner" :key="index">
      <el-carousel-item >
        <img :src="item.img">
      </el-carousel-item>
    </a>
  </el-carousel>
</template>

<script>
import { getBanner } from '@/api/blog'
export default {
  name: 'Banner',
  data() {
    return {
      banner: [] // 图片容器
    }
  },
  created() {
    // 轮播图
    getBanner().then(res => {
      this.banner = res.data
    })
  }
}
</script>

<style lang="scss">
@media screen and (max-width: 628px) {
    .el-carousel{
        height: 200px !important;
        .el-carousel__container{
            height: 200px !important;
        }
    }
}
</style>

<style lang="scss" scoped>
#banner{
    border-radius: 0.3rem;
}
img{
    width:100%;
    height:100%;
    border-radius: 0.3rem;
    overflow: hidden;
}
</style>
