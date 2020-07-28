<template>
  <div id="blogview">
    <div class="blog-box">
      <h3><i class="iconfont icon-github" />关于小科博客</h3>
      <h6>专注于Web开发技术</h6>
      <p>博主从2018年入坑始终坚持不断学习，充实自己，用能力弥补学历缺陷，用博客记录自己的成长，关注新技术，敢于探索和挑战。愿多年以后可以进入大厂，跟更多前辈探讨技术！</p>
      <table>
        <tr>
          <th>文章</th>
          <th>标签</th>
          <th>分类</th>
          <th>访客</th>
        </tr>
        <tr>
          <td>242</td>
          <td>65</td>
          <td>42</td>
          <td>35456</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>
#blogview{
    .blog-box{
        background-color: #FFF;
        padding: .3rem;
        margin-top: 10px;
        border-radius: .3rem;
        border: 1px solid #eee;
        box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
        h3{
            color: #333;
            font-size: 15px;
            padding: 0 8px;
            font-weight: normal;
        }
        h6{
            margin: 0;
            padding: 0 8px;
            color: #696969;
        }
        p{
            color: #999;
            padding: 0 8px;
            font-size: 13px;
        }
        table{
            width: 100%;
            color: #696969;
            text-align: center;
        }
    }
}
</style>
