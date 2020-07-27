<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <el-form-item label="用户昵称" prop="username">
      <el-input v-model="user.nickname" />
    </el-form-item>
    <el-form-item label="手机号码" prop="iphone">
      <el-input v-model="user.iphone" maxlength="11" />
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="user.email" maxlength="50" />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="user.sex">
        <el-radio label="1">男</el-radio>
        <el-radio label="2">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updateUserProfile } from '@/api/user'

export default {
  props: {
    user: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      // 表单校验
      rules: {
        username: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        iphone: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    submit() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          const newUser = {}
          newUser.id = this.user.id
          newUser.avatar = this.user.avatar
          newUser.email = this.user.email
          newUser.iphone = this.user.iphone
          newUser.sex = this.user.sex
          newUser.username = this.user.username
          updateUserProfile(newUser).then(response => {
            if (response.code === 200) {
              this.$message({
                duration: 3000,
                message: '修改成功',
                type: 'success'
              })
            }
            location.reload()
          })
        }
      })
    },
    close() {
      this.$store.dispatch('tagsView/delView', this.$route)
      this.$router.push({ path: '/' })
    }
  }
}
</script>
