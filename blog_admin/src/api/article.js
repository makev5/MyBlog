import request from '@/utils/request'
import { getToken } from '@/utils/auth'

// 获取博客列表
export function fetchList(query) {
  return request({
    url: '/blog/list',
    method: 'get',
    params: query
  })
}

// 发布博客
export function publishBlog(data) {
  return request({
    url: '/blog/edit',
    method: 'post',
    headers: { 'Authorization': getToken() },
    data
  })
}

// 获取某个博客信息
export function getBlog(id) {
  return request({
    url: `/blog/detail/${id}`,
    method: 'get'
  })
}

// 删除博客
export function deleteBlog(id) {
  return request({
    url: `/blog/delete/${id}`,
    method: 'delete'
  })
}

// 获取博客贡献
export function getBlogContributeCount() {
  return request({
    url: '/blog/contributeCount',
    method: 'get'
  })
}

// 获取标签列表
export function fetchTabList(query) {
  return request({
    url: '/tab/list',
    method: 'get',
    params: query
  })
}

// 通过标签获得博客数
export function getBlogCountByTag() {
  return request({
    url: '/tab/getBlogCountByTag',
    method: 'get'
  })
}

// 获取分类列表
export function fetchSortList(query) {
  return request({
    url: '/sort/list',
    method: 'get',
    params: query
  })
}

// 通过分类获得博客数
export function getBlogCountBySort() {
  return request({
    url: '/sort/getBlogCountBySort',
    method: 'get'
  })
}

// 搜索博客
export function getBlogBySearch(title, sortName) {
  return request({
    url: `/blog/search?title=${title}&&sortName=${sortName}`,
    method: 'get'
  })
}

// 修改分类
export function editSort(data) {
  return request({
    url: '/sort/edit',
    method: 'post',
    data
  })
}

// 删除分类
export function delSort(id) {
  return request({
    url: `/sort/delete/${id}`,
    method: 'delete'
  })
}

// 修改标签
export function editTab(data) {
  return request({
    url: '/tab/edit',
    method: 'post',
    data
  })
}

// 删除标签
export function delTab(id) {
  return request({
    url: `/tab/delete/${id}`,
    method: 'delete'
  })
}

// 得到关于博客
export function getAbout() {
  return request({
    url: '/about/detail',
    method: 'get'
  })
}
