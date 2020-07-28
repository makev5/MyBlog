<template>
<el-container>
  <el-main>
  <div id="blog">
    <div class="blogbox">
      <ul>
        <li v-for="item in blogList" :key="item.id" class="blog-item">
          <router-link :to="`../blog/${item.id}`">
            <h4 class="title">
              <a href="#" v-html="item.title"></a>
            </h4>
            <p class="desc" v-html="item.description"></p>
            <p class="info">
              <span>
                 <i class="iconfont icon-LC_icon_user_line" />{{ item.author }}
              </span>
              <span>
                <i class="iconfont icon-shijian" />{{ $moment(item.created).format('yyyy-MM-DD') }}
              </span>
              <span>
                <i class="iconfont icon-tag" />{{item.tabName }}
              </span>
            </p>
          </router-link>
          <div class="cover">
            <img :src="item.cover" alt="" srcset="">
          </div>
        </li>
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
  </el-main>
</el-container>
</template>

<script>
export default {
  name: 'BlogList',
  created() {
    // 获得博客列表
    this.$store.dispatch('blog/getBlogBySortName', this.$route.params.name)
  },
  methods: {
    // 分页
    change(currentPage) {
      this.$store.commit('blog/CHANGE', currentPage)
      this.$store.dispatch('blog/getBlogBySortName', this.$route.params.name)
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
      justify-content: space-between;
      align-items: center;
      list-style: none;
      padding: 10px 20px 0;
      margin-bottom: 10px;
      text-align: left;
      background: #fff;
      border: 1px solid #ddd;
      border-radius: 0.3rem;
      box-shadow: 2px 2px 3px #eee;
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
        margin-top: 10px;
        overflow: hidden;
        text-indent: 2em;
        letter-spacing: 2px;
        line-height: 25px;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
      }
      .info {
        margin: 10px 0;
        font-size: 14px;
        color: #666;
        span{
          margin: 0 2px;
        }
        .el-tag {
          margin-right: 5px;
        }
      }
      .cover{
        img{
         max-height: 100px;
        }
      }
    }
  }
}
@media only screen and (max-width: 375px) {
  .title a {
    font-size: 16px;
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
