<template>
  <el-container>
      <el-main>
        <!-- header -->
        <div class="about-me" body-style="width: 100%;box-sizing: border-box;">
            <el-avatar :size="80" :src="about.avatar" />
            <div>
              <h3>{{ about.name }}</h3>
              <p class="description">{{ about.introduction }}</p>
            </div>
        </div>
        <div v-html="about.content"></div>
      </el-main>
  </el-container>
</template>

<script>
import { getAbout } from '@/api/blog'
export default {
  name: 'About',
  data() {
    return {
      about: {}
    }
  },
  created() {
    getAbout().then(res => {
      this.about = res.data
      console.log(this.about)
    })
  }
}
</script>

<style lang="scss" scoped>
.about-me{
    width: 100%;
    text-align: center;
    .el-avatar:hover{
      transform: rotate(360deg);
      transition: all .6s ease-in-out;
    }
}
.description{
    padding-top: 5px;
    font-size: 14px;
}
</style>
