import { fetchList, getBlogBySearch, getBlogByTagName, getBlogBySortName } from '@/api/blog'
import router from '@/router'
const state = {
  blogList: [],
  total: 0,
  pages: 0,
  listQuery: {
    currentPage: 1,
    pageSize: 5
  }
}

const mutations = {
  // 获取博客列表
  BLOGLIST: (state) => {
    fetchList(state.listQuery).then(res => {
      state.blogList = res.data.records
      state.total = res.data.total
      state.pages = res.data.pages
    })
  },
  // 搜索
  SEARCH: (state, keyword) => {
    state.listQuery.currentPage = 1
    getBlogBySearch(keyword, state.listQuery).then(res => {
      state.blogList = res.data.records
      state.total = res.data.total
    })
    router.push('/')
  },
  // 分页
  CHANGE: (state, currentPage) => {
    state.listQuery.currentPage = currentPage
  },
  // 通过标签获得博客列表
  GETBLOGBYTAG: (state, tagName) => {
    getBlogByTagName(tagName, state.listQuery).then(res => {
      state.blogList = res.data.records
      state.total = res.data.total
    })
  },
  // 通过分类获得博客列表
  GETBLOGBYSORT: (state, sortName) => {
    getBlogBySortName(sortName, state.listQuery).then(res => {
      state.blogList = res.data.records
      state.total = res.data.total
    })
  }
}

const actions = {
  // 搜索
  search ({ commit }, keyword) {
    commit('SEARCH', keyword)
  },
  // 获取博客列表
  getBlogList({commit}) {
    commit('BLOGLIST')
  },
  // 通过标签获得博客列表
  getBlogByTagName({commit}, tagName) {
    commit('GETBLOGBYTAG', tagName)
  },
  // 通过分类获得博客列表
  getBlogBySortName({commit}, sortName) {
    commit('GETBLOGBYSORT', sortName)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
