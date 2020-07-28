<template>
  <el-container>
      <el-main>
        <div class="header">
          <h3 class="title">{{ blog.title }}</h3>
          <p class="desc">
            <span class="author"><i class="iconfont icon-LC_icon_user_line" />{{ blog.author }}</span>
            <span class="time"><i class="iconfont icon-shijian" />{{ $moment(blog.created).format('yyyy-MM-DD') }}</span>
            <span class="sort">分类：{{ blog.sortName }}</span>
            <span class="sort">标签：{{ blog.tabName }}</span>
            <span class="view">阅读：{{ blog.view }}</span>
          </p>
          <el-divider />
        </div>
        <p class="content markdown-body" v-html="blog.content"></p>
        <div  id="gitalk-container"></div>
      </el-main>
  </el-container>
</template>

<script>
import { getBlogById } from '@/api/blog'
import 'github-markdown-css/github-markdown.css'
import 'gitalk/dist/gitalk.css'
import Gitalk from 'gitalk'
export default {
  name: 'Blog',
  data() {
    return {
      blog: {
        title: '',
        author: '',
        created: '',
        sortName: '',
        content: ''
      },
      gitalk: new Gitalk({
        id: this.$route.params.id,
        clientID: '37b82c1b399cdecb78b8',
        clientSecret: 'd62674377b026da36ad3588ae387894e0407c3fb',
        repo: 'blog-comment',
        owner: 'makev5',
        admin: [ 'makev5' ],
        labels: [ 'makev5' ],
        perPage: 50,
        distractionFreeMode: false
      })
    }
  },
  mounted () {
    this.gitalk.render('gitalk-container')
  },
  created() {
    // 博客详情
    this.getBlogDetail(this.$route.params.id)
  },
  methods: {
    getBlogDetail(id) {
      getBlogById(id).then(res => {
        const blog = res.data
        var MarkdownIt = require('markdown-it')
        var md = new MarkdownIt()
        var content = md.render(blog.content)
        this.blog.title = blog.title
        this.blog.author = blog.author
        this.blog.created = blog.created
        this.blog.sortName = blog.sortName
        this.blog.tabName = blog.tabName
        this.blog.view = blog.view
        this.blog.content = content
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.header{
  margin-top: 50px;
}
.title,.desc{
  text-align: center;
}
.desc{
  margin: 5px 5px;
  span{
    margin: 0 5px;
  }
}
.el-divider{
  margin: 15px 0;
}
.OwO .OwO-items{
  max-width: 210px !important;
}
</style>
