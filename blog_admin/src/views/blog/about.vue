<template>
  <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
    <el-form-item label="头像" prop="avatar">
      <el-upload
        v-model="ruleForm.avatar"
        action="http://localhost:8081/qiniu/upload"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :on-success="handleSuccess"
        :file-list="certificate"
      >
        <i class="el-icon-plus" />
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="ruleForm.avatar" alt="">
      </el-dialog>
    </el-form-item>

    <el-form-item label="名字" prop="name">
      <el-input v-model="ruleForm.name" />
    </el-form-item>

    <el-form-item label="简介" prop="introduction">
      <el-input v-model="ruleForm.introduction" type="textarea" />
    </el-form-item>

    <el-form-item label="内容" prop="content">
      <mavon-editor
        ref="md"
        v-model="ruleForm.content"
        :toolbars="toolbars"
        @imgAdd="imgAdd"
      />
    </el-form-item>

    <el-form-item>
      <el-button v-show="ruleForm.id != ''" type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
      <el-button v-show="ruleForm.id == ''" type="primary" @click="submitForm('ruleForm')">立即发布</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { getAbout } from '@/api/article'
import { Upload } from '@/api/qiniu'
export default {
  name: 'About',
  data() {
    return {
      ruleForm: {
        id: '',
        avatar: '',
        introduction: '',
        cover: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入名字', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入简介', trigger: 'blur' }
        ],
        content: [
          { trequired: true, message: '请输入内容', trigger: 'blur' }
        ]
      },
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: false, // 标记
        superscript: false, // 上角标
        subscript: false, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: false, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: false, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: false, // 下一步
        trash: false, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true, // 预览
        boxShadow: false
      },
      dialogVisible: false,
      certificate: []
    }
  },
  created() {
    getAbout().then(res => {
      this.ruleForm = res.data
      console.log(this.ruleForm)
      this.certificate.push({
        url: this.ruleForm.avatar
      })
    })
  },
  methods: {
    // 移除头像
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    // 上传成功
    handleSuccess(response, file, fileList) {
      this.ruleForm.cover = 'http://' + response.data
    },
    handlePictureCardPreview(file) {
      console.log(file)
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    imgAdd(pos, file) {
      console.log(pos)
      console.log(file)
      // 上传图片
      var data = new FormData()
      data.append('file', file)
      console.log(this.$refs.md)
      Upload(data).then(res => {
        console.log(res)
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1.  通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         * 3. 由于vue运行访问的路径只能在static下，so，我就把图片保存到它这里了
         */
        const content = this.ruleForm.content
        const oStr = `(${pos})`
        const nStr = `(${'http://' + res.data})`
        const index = content.indexOf(oStr)
        const str = content.replace(oStr, '')
        const insertStr = (soure, start, newStr) => {
          return soure.slice(0, start) + newStr + soure.slice(start)
        }
        this.ruleForm.content = insertStr(str, index, nStr)
        // this.$refs.md.$imglst2Url(pos, 'http://image.xiaoke6.cn/FhpqzjdxM_FKKyzXxI8QNpprxUsu')
        // console.log(this.$refs.md.$imglst2Url)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.demo-ruleForm{
  max-width: 960px;
  margin: 20px auto;
}
</style>
