<template>
  <div id="recommend">
    <div class="box">
      <h2 class="reco-title">推荐文章</h2>
      <div class="suprecommend" v-for="item in first" :key="item.id">
        <router-link :to="`blog/${item.id}`">
          <img :src="item.cover" alt="" srcset="">
          <p class="blog-title">{{ item.title }}</p>
        </router-link>
      </div>
      <ul>
        <router-link :to="`blog/${item.id}`" tag="li" v-for="(item, index) in list" :key="index" class="blog-item">
          <!-- 博客图片 -->
          <div class="cover">
            <a>
             <img :src="item.cover" alt="" srcset="">
            </a>
          </div>
          <router-link :to="`blog/${item.id}`" class="article">
            <h4 class="title">
              <a href="#" v-html="item.title"></a>
            </h4>
            <p class="info">
              <span>
                <i class="iconfont icon-shijian" />{{ $moment(item.created).format('yyyy-MM-DD') }}
              </span>
            </p>
          </router-link>
        </router-link>
      </ul>
    </div>
  </div>
</template>

<script>
import { getRank } from '@/api/blog'
export default {
  name: 'Recommend',
  data() {
    return {
      first: [],
      list: []
    }
  },
  created() {
    getRank().then(res => {
      this.first = res.data.slice(0, 1)
      this.list = res.data.slice(1, 5)
    })
  }
}
</script>

<style lang="scss" scoped>
  .box {
    background-color: #fff;
    margin-top: 10px;
    padding: 4.6px;
    border-radius: 0.3rem;
    border: 1px solid #eee;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
    .reco-title{
      position: relative;
      font-size: 18px;
      color: #282828;
      font-weight: 600;
      text-transform: uppercase;
      padding-bottom: 10px;
      margin-bottom: 10px;
    }
    .reco-title::after{
      content: "";
      background-color: #ebeef5;
      left: 0;
      width: 100%;
      height: 2px;
      bottom: 0;
      position: absolute;
      transition: .5s;
    }
  }
  .suprecommend{
    position: relative;
    overflow: hidden;
    img{
      display: block;
      width: 100%;
      max-height: 120px;
      border-radius: 0.3rem;
    }
    img:hover{
      transform: scale(1.2);
      transition: all .5s ease-in-out;
    }
    .blog-title{
      position: absolute;
      bottom: 0;
      left: 0;
      right: 0;
      padding: 5px 10px;
      font-size: 12px;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      overflow: hidden;
      color: #fff;
      border-radius: 0.3rem;
      background: rgba(0,0,0,.7);
    }
  }
  .blog-item{
    display: flex;
    margin: 10px 0;
  }
  .cover{
    max-width: 100px;
    overflow: hidden;
    img{
      width: 100%;
      max-height: 50px;
      border-radius: 0.3rem;
      display: block;
    }
  }
  .cover img:hover{
    transform: scale(1.2);
    transition: all .5s ease-in-out;
  }
  .article{
    margin: 0 5px;
    .title{
      font-size: 12px;
      a{
        color: #333;
      }
    }
    .info{
      font-size: 12px;
      color: #333;
    }
  }
</style>
