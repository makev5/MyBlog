<template>
  <div id="header">
    <el-header>
      <div style="max-width:1200px;width:100%;margin:0 auto;display: flex;">
        <!-- 移动端菜单按钮 -->
        <div class="burger" :class="{active: toggleActive}" @click="toggle()">
          <div class="top-line" />
          <div class="middle-line" />
          <div class="bottom-line" />
        </div>
        <!-- logo -->
        <div class="logo">网行侠博客</div>
        <!-- 头部导航 -->
        <el-menu :default-active="activeIndex" :class="{open:toggleActive}" mode="horizontal" router active-text-color="rgb(95, 184, 120)">
          <el-menu-item v-for="(item,index) in menuList" :key="index" :index="item.path">{{ item.name }}</el-menu-item>
        </el-menu>

        <!-- 搜索功能 -->
        <div class="header-right">
          <i :class="icon" @click="Search" />
          <a id="pc_login" href="http://localhost:9528/" style="color:#909399;">登录</a>
          <a id="mobile_login" href="http://localhost:9528/"><i class="iconfont icon-LC_icon_user_line" /></a>
        </div>
      </div>
    </el-header>
    <!-- 搜索框区域 -->
    <div v-show="isSearch" class="search_box">
      <input v-model="keyword" type="search" placeholder="请输入关键字 Enter键搜索.." @keyup.enter="getBlog">
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      menuList: [ // 菜单导航
        {
          name: '首页',
          path: '/'
        },
        {
          name: '标签',
          path: '/tag'
        },
        {
          name: '时光机',
          path: '/timeline'
        },
        {
          name: '工具箱',
          path: '/tool'
        },
        {
          name: '文章分类',
          path: '/sort'
        },
        {
          name: '关于博客',
          path: '/about'
        }
      ],
      activeIndex: '/', // 当前激活菜单
      keyword: '', // 搜索框的值
      toggleActive: false, // 移动端抽屉开关
      isSearch: false, // 是否显示搜索框
      icon: 'iconfont icon-search'
    }
  },
  methods: {
    toggle() {
      this.toggleActive = !this.toggleActive
    },
    Search() {
      this.isSearch = !this.isSearch
      if (this.isSearch) {
        this.icon = 'iconfont icon-close'
      } else {
        this.icon = 'iconfont icon-search'
      }
    },
    getBlog() {
      if (this.keyword === '') {
        this.$message.warning('请请输入内容')
      } else {
        this.$store.dispatch('blog/search', this.keyword)
      }
    }
  }
}
</script>

<style scoped lang="scss">
#header{
    position: fixed;
    top: 0;
    width: 100%;
    background: #fff;
    z-index: 1024;
    .el-header{
      z-index: 1024;
      display:flex;
      justify-items: center;
      padding: 0 10px;
      box-shadow: 0 2px 3px hsla(0,0%,7%,.1), 0 0 0 1px hsla(0,0%,7%,.1);
      // menu按钮
      .open{
        transform: translate(0%) !important;
      }
      .burger div{
        width: 25px;
        height: 3px;
        background-color: #999;
        margin: 4px;
        line-height: 60px;
      }
      .burger{
        height: 25px;
        margin-top: 17.5px;
        display: none;
      }
      // logo
      .logo{
        font-size: 20px;
        font-weight: 600;
        color: #999;
        width: 200px;
        text-align: center;
        line-height: 60px;
        cursor: pointer;
      }
      // 导航菜单
      .el-menu{
        flex: 0.9;
      }
      //header-right
      .header-right{
        display: flex;
        flex: 0.1;
        cursor: pointer;
        justify-content: space-between;
        line-height: 60px;
        .iconfont{
          color: #999;
          font-size: 20px;
        }
        .login{
          position: absolute;
          right: 0;
          flex: 1;
          font-size: 14px;
          line-height: 60px;
          color: rgb(95, 184, 120);
        }
      }
    }
    // 搜索框
    .search_box{
      position: absolute;
      top: 80px;
      left: 50%;
      transform: translate(-50%);
      width: 80%;
      transition: all 0.5s ease-in;
      z-index: 100;
      transition: all .5s ease-in;
      input{
        margin: 10px auto;
        padding: 10px 0 10px 10px;
        display: block;
        width: 80%;
        text-align: center;
        border-radius: 100px;
        border: 1px solid #5FB878;
        outline: none;
        transition: all .5s ease-in;
      }
    }
    // pc移动搜索按钮
    #pc_login:hover{
      color: #5FB878 !important;
    }
    #mobile_login{
      display: none;
    }
}

@media only screen and (max-width: 916px) {
  #pc_login{
    display: none;
  }
  #mobile_login{
    display: block !important;
  }
  // 菜单按钮
  .burger{
    display: block !important;
    flex: 0.2;
  }
  .burger.active div{
    transition: 0.3s ease-in-out 0.3s;
  }
  .burger.active .top-line{
    transform: rotate(45deg) translate(4px,6px);
  }
  .burger.active .bottom-line{
    transform: rotate(-45deg) translate(4px,-6px);
  }
  .burger.active .middle-line{
    opacity: 0;
    transform: translateX(10px);
    transition: 0.3s ease-in-out;
  }
  // logo
  .logo{
    flex: 1 !important;
  }
  // 导航菜单
  .el-menu{
    position: fixed;
    top: 60px;
    left: 0;
    width: 50%;
    height: 100%;
    transform: translate(-100%);
    transition: 0.4s ease-in-out;
  }
  .el-menu-item{
    width: 100%;
    text-align: center;
    clear: both;
  }
  .el-menu-item:hover{
    color: rgb(95, 184, 120) !important;
  }
  .el-menu-item, .is-active{
    border-bottom: #fff !important;
  }
  .el-input--suffix .el-input__inner {
    display: none !important;
  }
  .header-right{
    flex: 0.2 !important;
  }
}
@media only screen and (max-width: 375px) {
    .logo{
        font-size: 16px !important;
    }
}
</style>
