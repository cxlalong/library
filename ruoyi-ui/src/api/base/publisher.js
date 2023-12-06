import request from '@/utils/request'

// 查询出版社信息列表
export function listPublisher(query) {
  return request({
    url: '/base/publisher/list',
    method: 'get',
    params: query
  })
}

// 查询出版社信息详细
export function getPublisher(publisherId) {
  return request({
    url: '/base/publisher/' + publisherId,
    method: 'get'
  })
}

// 新增出版社信息
export function addPublisher(data) {
  return request({
    url: '/base/publisher',
    method: 'post',
    data: data
  })
}

// 修改出版社信息
export function updatePublisher(data) {
  return request({
    url: '/base/publisher',
    method: 'put',
    data: data
  })
}

// 删除出版社信息
export function delPublisher(publisherId) {
  return request({
    url: '/base/publisher/' + publisherId,
    method: 'delete'
  })
}
