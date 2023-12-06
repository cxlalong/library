import request from '@/utils/request'

// 查询读者信息列表
export function listReader(query) {
  return request({
    url: '/system/reader/list',
    method: 'get',
    params: query
  })
}

// 查询读者信息详细
export function getReader(readerId) {
  return request({
    url: '/system/reader/' + readerId,
    method: 'get'
  })
}

// 新增读者信息
export function addReader(data) {
  return request({
    url: '/system/reader',
    method: 'post',
    data: data
  })
}

// 修改读者信息
export function updateReader(data) {
  return request({
    url: '/system/reader',
    method: 'put',
    data: data
  })
}

// 删除读者信息
export function delReader(readerId) {
  return request({
    url: '/system/reader/' + readerId,
    method: 'delete'
  })
}
