<template>
  <div class="app-container">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" />
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" />
      </el-form-item>

      <el-form-item label="手机号" prop="iphone">
        <el-input v-model="ruleForm.iphone" />
      </el-form-item>

      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email" />
      </el-form-item>

      <el-form-item label="头像" prop="avatar">
        <el-input v-model="ruleForm.avatar" type="textarea" />
      </el-form-item>

      <div class="option">
        <el-form-item label="性别">
          <el-select v-model.number="ruleForm.sex" placeholder="请选择性别">
            <el-option v-for="(item, index) in sexArr" :key="index" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
      </div>

      <el-form-item>
        <el-button v-show="ruleForm.id != ''" type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
        <el-button v-show="ruleForm.id == ''" type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getUserById, updateUserProfile } from '@/api/user'
export default {
  name: 'EditUser',
  data() {
    return {
      sexArr: [
        {
          id: 1,
          name: '男'
        },
        {
          id: 2,
          name: '女'
        }
      ],
      ruleForm: {
        id: '',
        username: '',
        password: '',
        iphone: '',
        email: '',
        avatar: '',
        sex: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 18, message: '长度在 5 到 18 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 32, message: '长度在 5 到 32 个字符', trigger: 'blur' }
        ],
        iphone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { min: 7, max: 11, message: '长度在 7 到 18 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        avatar: [
          { required: true, message: '请上传头像', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    const userId = this.$route.params.userId
    console.log(userId)
    const _this = this
    if (userId) {
      getUserById(userId).then(res => {
        console.log(res)
        _this.ruleForm.id = res.data.id
        _this.ruleForm.username = res.data.username
        _this.ruleForm.password = res.data.password
        _this.ruleForm.avatar = res.data.avatar
        _this.ruleForm.email = res.data.email
        _this.ruleForm.iphone = res.data.iphone
        _this.ruleForm.sex = res.data.sex * 1
      })
    }
  },
  methods: {
    // 提交
    submitForm(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          updateUserProfile(_this.ruleForm).then(res => {
            console.log(res)
            if (res.code === 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.$router.push('/user/list')
            }
          })
        }
      })
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.app-container {
  margin: 0 auto;
  text-align: center;
  max-width: 960px;
}
.option {
  display: flex;
}
</style>
