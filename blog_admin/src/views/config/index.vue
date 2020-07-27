<template>
  <el-main>
    <el-tabs type="border-card">
      <!-- 网站配置 -->
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date" /> 网站配置</span>
        <el-form label-width="80px" label-position="left">
          <el-form-item label="LOGO" prop="LOGO">
            <el-row :gutter="20">
              <el-col :span="6">
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
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="网站名称" prop="title">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-input v-model="ruleForm.title" />
              </el-col>
              <el-col :span="8">
                <el-input v-model="ruleForm.description" />
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="关键字" prop="keyword">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-input v-model="ruleForm.keyword" />
              </el-col>
              <el-col :span="8">
                <el-input v-model="ruleForm.keyword" />
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="作者" prop="keyword">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-input v-model="ruleForm.author" />
              </el-col>
              <el-col :span="8">
                <el-input v-model="ruleForm.beian" />
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <!-- 时光机 -->
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date" /> 时光机</span>
        <el-button type="primary" icon="el-icon-edit" />
        <!-- 对话框 -->
        <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
          <el-form :model="form" label-width="80px">
            <el-form-item label="标题">
              <el-input v-model="form.title" autocomplete="off" />
            </el-form-item>
            <el-form-item label="内容">
              <el-input v-model="form.title" autocomplete="off" />
            </el-form-item>
            <el-form-item label="时间">
              <el-date-picker
                v-model="date"
                type="date"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>
        <!-- 时间线 -->
        <el-timeline>
          <el-timeline-item timestamp="2018/4/12" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/12 20:46</p>
              <el-button type="primary" icon="el-icon-edit" @click="dialogFormVisible=true" />
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2018/4/3" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/3 20:46</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2018/4/2" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/2 20:46</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-tab-pane>
    </el-tabs>
  </el-main>
</template>

<script>
import { Upload } from '@/api/qiniu'
export default {
  name: 'WebInfo',
  data() {
    return {
      dialogFormVisible: false,
      form: {
      },
      date: '',
      ruleForm: {
        title: '',
        description: '',
        keyword: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入网站名称', trigger: 'blur' },
          { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
        ]
      },
      dialogVisible: false,
      certificate: []
    }
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

<style scoped>

</style>
