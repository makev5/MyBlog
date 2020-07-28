<template>
  <div class="m-content">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题图" prop="cover">
        <el-upload
          v-model="ruleForm.cover"
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
          <img width="100%" :src="ruleForm.cover" alt="">
        </el-dialog>
      </el-form-item>

      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title" />
      </el-form-item>

      <el-form-item label="摘要" prop="description">
        <el-input v-model="ruleForm.description" type="textarea" />
      </el-form-item>

      <div class="option">
        <el-form-item label="分类">
          <el-select v-model.number="ruleForm.sortId" placeholder="请选择分类">
            <el-option v-for="(item, index) in sortArr" :key="index" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="标签">
          <el-select v-model.number="ruleForm.tabId" placeholder="请选择标签">
            <el-option v-for="(item, index) in tabArr" :key="index" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
      </div>

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
  </div>
</template>

<script>
import { publishBlog, getBlog, getBlogCountByTag, getBlogCountBySort } from '@/api/article'
import { Upload } from '@/api/qiniu'
export default {
  name: 'BlogEdit',
  data() {
    return {
      sortArr: [],
      tabArr: [],
      ruleForm: {
        id: '',
        title: '',
        description: '',
        cover: '',
        content: '',
        sortId: '',
        tabId: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入摘要', trigger: 'blur' }
        ],
        content: [
          { trequired: true, message: '请输入内容', trigger: 'blur' }
        ],
        sortId: [
          { trequired: true, message: '请选择分类', trigger: 'blur' }
        ],
        tabId: [
          { trequired: true, message: '请选择标签', trigger: 'blur' }
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
      certificate: [],
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    const _this = this
    if (blogId) {
      getBlog(blogId).then(res => {
        const blog = res.data
        console.log(blog)
        _this.ruleForm.id = blog.id
        _this.ruleForm.title = blog.title
        _this.ruleForm.description = blog.description
        _this.ruleForm.content = blog.content
        _this.ruleForm.sortId = blog.sortId * 1
        _this.ruleForm.tabId = blog.tabId * 1
        _this.ruleForm.cover = blog.cover
        console.log(_this.ruleForm.cover)
        _this.certificate.push({
          url: blog.cover
        })
      })
    }
    getBlogCountByTag().then(res => {
      console.log(res.data.blogCountByTag)
      this.tabArr = res.data.blogCountByTag
    })
    getBlogCountBySort().then(res => {
      console.log(res.data)
      this.sortArr = res.data.blogCountBySort
    })
  },
  methods: {
    // 提交博客信息
    submitForm(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          alert(111)
          console.log(_this.ruleForm)
          publishBlog(_this.ruleForm).then(response => {
            _this.$alert('操作成功', '提示', {
              confirmButtonText: '确定',
              callback: action => {
                _this.$router.push('/blog/list')
              }
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 移除标题图
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

<style scoped>
.m-content{
  text-align: left;
  padding: 20px;
}
.option{
  display: flex;
}
</style>
