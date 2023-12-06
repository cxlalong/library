import request from '@/utils/request'

// 查询图书类别列表
export function listBooktype(query) {
  return request({
    url: '/base/booktype/list',
    method: 'get',
    params: query
  })
}

// 查询图书类别详细
export function getBooktype(typeId) {
  return request({
    url: '/base/booktype/' + typeId,
    method: 'get'
  })
}

// 新增图书类别
export function addBooktype(data) {
  return request({
    url: '/base/booktype',
    method: 'post',
    data: data
  })
}

// 修改图书类别
export function updateBooktype(data) {
  return request({
    url: '/base/booktype',
    method: 'put',
    data: data
  })
}

// 删除图书类别
export function delBooktype(typeId) {
  return request({
    url: '/base/booktype/' + typeId,
    method: 'delete'
  })
}
