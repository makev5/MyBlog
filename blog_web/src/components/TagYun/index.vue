<template>
  <!-- 标签云 -->
  <div>
      标签云
    <svg width='100%' height='100%' @mousemove='listener($event)'>
      <a :href="tag.href" v-for="(tag, index) in tags" :key="index">
        <text :x='tag.x' :y='tag.y' :font-size='20 * (600/(600-tag.z))' :fill-opacity='((400+tag.z)/600)'>{{tag.text}}</text>
      </a>
    </svg>
  </div>
</template>

<script>
export default {
  name: 'TagYun',
  data() {
    return {
      tagsNum: 20, // 标签数量
      RADIUS: 200, // 球的半径
      speedX: Math.PI / 360, // 球一帧绕x轴旋转的角度
      speedY: Math.PI / 360, // 球-帧绕y轴旋转的角度
      tags: ['Java', 'Vue']
    }
  },
  computed: {
    CX() { // 球心x坐标
      return this.width / 2
    },
    CY() { // 球心y坐标
      return this.height / 2
    }
  },
  created() { // 初始化标签位置
    let tags = []
    for (let i = 0; i < this.tagsNum; i++) {
      let tag = {}
      let k = -1 + (2 * (i + 1) - 1) / this.tagsNum
      let a = Math.acos(k)
      let b = a * Math.sqrt(this.tagsNum * Math.PI) // 计算标签相对于球心的角度
      tag.text = i + 'tag'
      tag.x = this.CX + this.RADIUS * Math.sin(a) * Math.cos(b) // 根据标签角度求出标签的x,y,z坐标
      tag.y = this.CY + this.RADIUS * Math.sin(a) * Math.sin(b)
      tag.z = this.RADIUS * Math.cos(a)
      tag.href = 'https://imgss.github.io' // 给标签添加链接
      tags.push(tag)
    }
    this.tags = tags // 让vue替我们完成视图更新
  },
  mounted() { // 使球开始旋转
    setInterval(() => {
      this.rotateX(this.speedX)
      this.rotateY(this.speedY)
    }, 17)
  },
  methods: {
    rotateX(angleX) {
      var cos = Math.cos(angleX)
      var sin = Math.sin(angleX)
      for (let tag of this.tags) {
        var y1 = (tag.y - this.CY) * cos - tag.z * sin + this.CY
        var z1 = tag.z * cos + (tag.y - this.CY) * sin
        tag.y = y1
        tag.z = z1
      }
    },
    rotateY(angleY) {
      var cos = Math.cos(angleY)
      var sin = Math.sin(angleY)
      for (let tag of this.tags) {
        var x1 = (tag.x - this.CX) * cos - tag.z * sin + this.CX
        var z1 = tag.z * cos + (tag.x - this.CX) * sin
        tag.x = x1
        tag.z = z1
      }
    },
    listener(event) { // 响应鼠标移动
      var x = event.clientX - this.CX
      var y = event.clientY - this.CY
      this.speedX = x * 0.0001 > 0 ? Math.min(this.RADIUS * 0.00002, x * 0.0001) : Math.max(-this.RADIUS * 0.00002, x * 0.0001)
      this.speedY = y * 0.0001 > 0 ? Math.min(this.RADIUS * 0.00002, y * 0.0001) : Math.max(-this.RADIUS * 0.00002, y * 0.0001)
    }
  }
}
</script>

<style lang="scss" scoped>
div{
    background-color: #fff;
    margin-top: 10px;
    padding: 4.6px;
    border-radius: 0.3rem;
    border: 1px solid #eee;
    -webkit-box-shadow: 0 2px 3px rgba(18,18,18,0.1), 0 0 0 1px rgba(18,18,18,0.1);
    box-shadow: 0 2px 3px rgba(18,18,18,0.1), 0 0 0 1px rgba(18,18,18,0.1);
}
 svg{
     display: block;
     margin: 0 auto;
 }
</style>
