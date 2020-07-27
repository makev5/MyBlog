import request from '@/utils/request'

// 上传
export function Upload(data) {
  return request({
    url: '/qiniu/upload',
    method: 'post',
    data
  })
}

// 移除
export function removeFile(key) {
  return request({
    url: `/qiniu/delete/${key}`,
    method: 'get'
  })
}
// 文件列表
export function fitchList() {
  return request({
    url: '/qiniu/list/',
    method: 'get'
  })
}
