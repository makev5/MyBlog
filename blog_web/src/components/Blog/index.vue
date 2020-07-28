<template>
  <div id="blog">
    <div class="blogbox">
      <ul>
        <p v-if="blogList.length==0" style="text-align: center;line-height: 100px;">空空如也</p>
        <router-link :to="`blog/${item.id}`" tag="li" v-for="item in blogList" :key="item.id" class="blog-item">
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
            <p class="desc" v-html="item.description"></p>
            <p class="info">
              <span>
                 <i class="iconfont icon-LC_icon_user_line" />{{ item.author }}
              </span>
              <span>
                <i class="iconfont icon-liulan" /> {{ item.view }}
              </span>
              <span>
                <i class="iconfont icon-icon32" /> {{ item.sortName }}
              </span>
              <span>
                <i class="iconfont icon-shijian" /> {{ $moment(item.created).format('yyyy-MM-DD') }}
              </span>
            </p>
          </router-link>
        </router-link>
      </ul>
    </div>
    <!-- 分页 -->
    <el-pagination
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      @current-change="change"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'BlogList',
  created() {
    // 获得博客列表
    this.$store.commit('blog/BLOGLIST')
  },
  methods: {
    // 分页
    change(currentPage) {
      this.$store.commit('blog/CHANGE', currentPage)
      this.$store.commit('blog/BLOGLIST')
    }
  },
  computed: {
    blogList() {
      return this.$store.getters.blogList
    },
    total() {
      return this.$store.getters.total
    },
    pageSize() {
      return this.$store.getters.pageSize
    },
    pages() {
      return this.$store.getters.pages
    }
  }
}
</script>

<style lang="scss" scoped>
#blog {
  border-radius: 0.3rem;
  .blogbox {
    margin: 10px auto;
    .blog-item {
      display: flex;
      overflow: hidden;
      align-items: center;
      list-style: none;
      cursor: pointer;
      margin-bottom: 10px;
      text-align: left;
      background: #fff;
      border: 0.5px solid #ddd;
      box-sizing: border-box;
      border-radius: 0.3rem;
      box-shadow: 1px 1px 1px #eee;
      .cover{
        display: block;
        width: 150px;
        a {
          display: block;
          overflow: hidden;
          img{
            display: block;
            width: 150px;
            max-height: 150px;
          }
        }
      }
      .cover img:hover{
        transform: scale(1.2);
        transition: all .5s ease;
      }
      .article{
        margin: 0 10px;
        .title {
          display: block;
          font-size: 1rem;
          font-weight: 400;
          line-height: 30px;
          margin: 0;
          a {
            color: #333;
          }
        }
        .desc {
          color: #666;
          font-size: 14px;
          overflow: hidden;
          text-indent: 2em;
          letter-spacing: 2px;
          line-height: 25px;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
        }
        .info {
          display: flex;
          flex-wrap: wrap;
          margin-bottom: 10px;
          font-size: 14px;
          color: #666;
          span{
            margin: 0 10px;
          }
        }
      }
    }
  }
}
@media only screen and (max-width: 992px) {
  .blog-item{
    flex-wrap: wrap;
    .cover{
      width: 100% !important;
      img {
        width: 100% !important;
        max-height: 250px !important;
      }
    }
  }
}
@media only screen and (max-width: 375px) {
  .title a {
    font-size: 1rem;
  }
  .info {
    font-size: 10px !important;
    .iconfont {
      font-size: 12px !important;
    }
  }
}
.el-pagination{
  text-align: center;
}
</style>
