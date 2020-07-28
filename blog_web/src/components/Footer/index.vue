<template>
  <div class="footer-wrapper">
      <span>
        <i class="iconfont reco-copyright"></i>
        <a>
          <span><i class="iconfont icon-copyrightbanquan"></i> 2020 -</span>
          2021&nbsp;
          <span>网行侠博客</span>&nbsp;
          <span>
            豫ICP备19029763号
          </span>
        </a>
      </span>
  </div>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style lang="scss" scoped>
.footer-wrapper{
    padding: 1.5rem 2.5rem;
    border-top: 1px solid #eaecef;
    text-align: center;
    color: #5b5b5b;
    cursor: pointer;
    span:hover{
      color: #5FB878;
    }
}
</style>
