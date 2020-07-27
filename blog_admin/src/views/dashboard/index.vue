<template>
  <div class="dashboard-container">

    <github-corner class="github-corner" />

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <!--文章贡献度-->
    <el-row>
      <CalendarChart />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart
            v-if="showPieBlogSortChart"
            ref="blogSortPie"
            :value="blogCountBySort"
            :tag-name="sortNameArray"
            @clickPie="clickBlogSortPie"
          />
        </div>
      </el-col>

      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart
            v-if="showPieChart"
            :value="blogCountByTag"
            :tag-name="tagNameArray"
            @clickPie="clickBlogTagPie"
          />
        </div>
      </el-col>

      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}">
        <box-card />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getBlogCountByTag, getBlogCountBySort } from '@/api/article'
import { mapGetters } from 'vuex'
import GithubCorner from '@/components/GithubCorner'
import PanelGroup from './components/PanelGroup'
import PieChart from './components/PieChart'
import CalendarChart from './components/CalendarChart'
import BoxCard from './components/BoxCard'
const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Dashboard',
  components: {
    GithubCorner,
    PanelGroup,
    CalendarChart,
    PieChart,
    BoxCard
  },
  data() {
    return {
      visitAddTotal: 0,
      userTotal: 0,
      commentTotal: 0,
      blogTotal: 0,
      showPieChart: false,
      showPieBlogSortChart: false,
      lineChartData: lineChartData.newVisitis,
      blogCountBySort: [],
      blogCountByTag: [],
      sortNameArray: [],
      tagNameArray: []
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  created() {
    // 通过分类获取博客数目
    getBlogCountBySort().then(response => {
      if (response.code === 200) {
        this.blogCountBySort = response.data.blogCountBySort
        var sortList = this.blogCountBySort
        for (var a = 0; a < this.blogCountBySort.length; a++) {
          this.sortNameArray.push(sortList[a].name)
        }
        this.showPieBlogSortChart = true
      }
    })

    // 通过标签获取博客数目
    getBlogCountByTag().then(response => {
      if (response.code === 200) {
        this.blogCountByTag = response.data.blogCountByTag
        var tagList = this.blogCountByTag
        for (var a = 0; a < this.blogCountByTag.length; a++) {
          this.tagNameArray.push(tagList[a].name)
        }
        this.showPieChart = true
      }
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    clickBlogTagPie() {
      this.$router.push({
        path: '/blog/list'
      })
    },
    clickBlogSortPie() {
      this.$router.push({
        path: '/blog/list'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }
</style>
