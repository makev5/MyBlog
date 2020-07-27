<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="8" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div class="text-center">
              <userAvatar :user="user" />
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <div><svg-icon icon-class="user" />用昵称:</div>
                <div class="pull-right">{{ user.nickname }}</div>
              </li>
              <el-divider />
              <li class="list-group-item">
                <div><svg-icon icon-class="iphone" />手机号码:</div>
                <div class="pull-right">{{ user.iphone }}</div>
              </li>
              <el-divider />
              <li class="list-group-item">
                <div><svg-icon icon-class="email" />用户邮箱:</div>
                <div class="pull-right">{{ user.email }}</div>
              </li>
              <el-divider />
              <li class="list-group-item">
                <div><svg-icon icon-class="people" />所属角色:</div>
                <div class="pull-right">{{ roleGroup }}</div>
              </li>
              <el-divider />
              <li class="list-group-item">
                <div><svg-icon icon-class="date" />创建日期:</div>
                <div class="pull-right">{{ $moment(user.created).format('YYYY年MM月DD日') }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="基本资料" name="userinfo">
              <userInfo :user="user" />
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="resetPwd">
              <resetPwd :user="user" />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userAvatar from './userAvatar'
import userInfo from './userInfo'
import resetPwd from './resetPwd'
import { getInfo } from '@/api/user'
export default {
  name: 'Profile',
  components: { userAvatar, userInfo, resetPwd },
  data() {
    return {
      user: {},
      roleGroup: '',
      activeTab: 'userinfo'
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      getInfo().then(response => {
        this.user = response.data
        this.roleGroup = response.data.role[0]
      })
    }
  }
}
</script>

<style scoped>
    .text-center{
        text-align: center;
    }
    .list-group.list-group-striped{
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .list-group-item{
        text-indent: .5em;
        display: flex;
        justify-content: space-between;
    }
    .el-divider--horizontal{
        margin: 18px 0;
    }
</style>
