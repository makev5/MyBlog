import request from '@/utils/request'
import { getToken } from '@/utils/auth'

// 登录
export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

// 得到用户信息
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get',
    headers: { 'Authorization': getToken() }
  })
}

// 退出登录
export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}

// 上传头像
export function uploadAvatar(data) {
  return request({
    url: '/qiniu/upload',
    method: 'post',
    data
  })
}

// 更新用户信息(添加或修改)
export function updateUserProfile(data) {
  return request({
    url: '/user/edit',
    method: 'put',
    data,
    headers: {
      'Authorization': getToken(),
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

// 得到用户列表
export function getUserList(query) {
  return request({
    url: '/user/list',
    method: 'get',
    params: query,
    headers: { 'Authorization': getToken() }
  })
}

// 通过ID获取用户信息
export function getUserById(id) {
  return request({
    url: `/user/${id}`,
    method: 'get',
    headers: { 'Authorization': getToken() }
  })
}

// 根据Id删除用户
export function delUserById(id) {
  return request({
    url: `/user/delete/${id}`,
    method: 'delete',
    headers: { 'Authorization': getToken() }
  })
}
