import request from '@/utils/request'
// import { getToken } from '@/utils/auth'

// 轮播图
export function getBanner() {
  return request({
    url: '/banner/list',
    method: 'get'
  })
}

// 获取博客列表
export function fetchList(query) {
  return request({
    url: '/blog/list',
    method: 'get',
    params: query
  })
}

// 获取博客详情
export function getBlogById(id) {
  return request({
    url: `/blog/${id}`,
    method: 'get'
  })
}

// 通过标签获得博客数
export function getBlogCountByTag() {
  return request({
    url: '/tab/getBlogCountByTag',
    method: 'get'
  })
}

// 通过分类获得博客数
export function getBlogCountBySort() {
  return request({
    url: '/sort/getBlogCountBySort',
    method: 'get'
  })
}

// 通过分类获得博客列表
export function getBlogBySortName(name, query) {
  return request({
    url: `/blog/getBlogBySort?sortName=${name}`,
    method: 'get',
    params: query
  })
}

// 通过标签获得博客列表
export function getBlogByTagName(name, query) {
  return request({
    url: `/blog/getBlogByTag?tagName=${name}`,
    method: 'get',
    params: query
  })
}

// 获取友链
export function getLink() {
  return request({
    url: '/link/list',
    method: 'get'
  })
}

// 博客搜索
export function getBlogBySearch(keyword, query) {
  return request({
    url: `/search/${keyword}`,
    method: 'get',
    params: query
  })
}

// 通过浏览量查询博客
export function getRank() {
  return request({
    url: '/blog/rank',
    method: 'get'
  })
}

// 得到关于博客
export function getAbout() {
  return request({
    url: '/about/detail',
    method: 'get'
  })
}

// 得到工具列表
export function getToolList() {
  return request({
    url: '/tool/list',
    method: 'get'
  })
}
