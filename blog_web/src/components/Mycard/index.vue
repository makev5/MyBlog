<template>
  <div class="author-box">
    <div class="author">
      <div>
        <span>
          <img src="http://yun.sevenit.cn/2020/07/21/bfd07b05f556b.jpg" alt="">
        </span>
      </div>
    </div>
    <div class="anthor-incroduction">
      <div class="author-name">{{ author }}</div>
      <div>{{ information }}</div>
      <div class="author-tag">
        <el-tag v-for="item in skills" :key="item.id" :type="item.type" effect="plain" size="mini">{{ item.name }}</el-tag>
      </div>
      <div class="contact">
        <span>社交账号</span>
      </div>
      <el-tooltip v-for="i in contact" :key="i.id" class="item" effect="dark" :content="i.content" placement="top">
        <a :href="i.url" class="contact-item" target="_blank">
          <i :class="i.icon" />
        </a>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Mycard',
  data() {
    return {
      author: '网行侠', // 作者
      information: '专注于Java和移动前端开发',
      skills: [ // 编程技能
        {
          id: 1,
          name: 'Vue',
          type: 'success'
        },
        {
          id: 2,
          name: 'Spring',
          type: 'info'
        },
        {
          id: 3,
          name: 'SpringBoot',
          type: 'danger'
        },
        {
          id: 4,
          name: 'Spring Clound',
          type: 'plain'
        },
        {
          id: 5,
          name: 'Bootstrap',
          type: 'warning'
        }
      ],
      contact: [ // 社交
        {
          id: 1,
          content: 'GitHub地址：https://github.com/makev5',
          url: 'https://github.com/702164551',
          icon: 'iconfont icon-github'
        },
        {
          id: 2,
          content: 'Gitee地址：https://gitee.com/makev5',
          url: 'https://gitee.com/makev5',
          icon: 'iconfont icon-gitee'
        },
        {
          id: 3,
          content: '抖音号：1767237833',
          url: ' https://v.douyin.com/JN5bpNf/',
          icon: 'iconfont icon-douyin'
        },
        {
          id: 4,
          content: 'QQ:702164551',
          url: 'http://wpa.qq.com/msgrd?v=3&uin=702164551&site=qq&menu=yes',
          icon: 'iconfont icon-QQ'
        },
        {
          id: 5,
          content: 'Wechat:702164551',
          url: 'https://yun.sevenit.cn/2020/07/08/1392be394c261.png',
          icon: 'iconfont icon-weixin'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
  .author-box {
    cursor: pointer;
    background-color: #fff;
    padding: 6.2px;
    border-radius: 0.3rem;
    border: 1px solid #eee;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
    .author {
      background-color: #fff;
      padding: 0.3rem;
      border-radius: 0.3rem;
      text-align: center;
      span {
        display: block;
        width: 80px;
        height: 80px;
        line-height: 100px;
        font-size: 18px;
        margin: 0 auto;
        img {
          display: block;
          width: 100%;
          height: 100%;
          border-radius: 50%;
        }
      }
    }
    .author img:hover{
      transform: rotate(360deg);
      transition: all .6s ease-in-out;
    }
    .anthor-incroduction {
      font-size: 0.8rem;
      color: #999;
      text-align: center;
      div {
        margin-bottom: 12px;
      }
      .author-name {
        font-size: 1rem;
        color: #1890ff;
      }
      .author-tag {
        line-height: 27px;
        .el-tag {
          margin: 3px 5px;
        }
      }
      .contact {
        margin: 10px 0;
        span {
          color: rgba(0, 0, 0, 0.85);
          font-weight: 500;
          font-size: 16px;
          white-space: nowrap;
          text-align: center;
        }
      }
    }
    .contact-item {
      display: inline-block;
      width: 28px;
      height: 28px;
      margin: 0 10px;
      margin-bottom: 10px;
      i {
        font-size: 28px;
        color: #333;
      }
    }
  }
</style>
