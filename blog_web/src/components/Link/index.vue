<template>
  <div id="links">
    <div class="links-box">
      <h2>
        <i class="iconfont icon-right" /> 友情链接
      </h2>
      <ul>
        <li v-for="item in links" :key="item.id" class="item">
          <a :href="item.url" target="_blank">
            <i class="iconfont icon-link" /> {{ item.name }}
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getLink } from '@/api/blog'
export default {
  name: 'Links',
  data() {
    return {
      links: []
    }
  },
  created() {
    // 友链
    getLink().then(res => {
      this.links = res.data
    })
  }
}
</script>

<style lang="scss" scoped>
#links {
  .links-box {
    background-color: #fff;
    margin-top: 10px;
    padding: 4.6px;
    border-radius: 0.3rem;
    border: 1px solid #eee;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
    h2 {
      position: relative;
      font-size: 18px;
      color: #282828;
      font-weight: 600;
      text-transform: uppercase;
      padding-bottom: 10px;
      margin-bottom: 10px;
    }
    h2::after{
      content: "";
      background-color: #ebeef5;
      left: 0;
      width: 100%;
      height: 2px;
      bottom: 0;
      position: absolute;
    }
    ul {
      margin: 5px 3px;
      overflow: hidden;
      display: flex;
      flex-wrap: wrap;
      .item {
        width: 50%;
        text-align: center;
        margin-bottom: 5px;
        a {
          color: #888;
          font-size: 14px;
        }
        a:hover {
          color: rgb(95, 184, 120);
        }
      }
    }
  }
}
</style>
