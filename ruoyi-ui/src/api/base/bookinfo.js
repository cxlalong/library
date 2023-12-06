import request from '@/utils/request'

// 查询图书信息列表
export function listBookinfo(query) {
  return request({
    url: '/base/bookinfo/list',
    method: 'get',
    params: query
  })
}

// 查询图书信息详细
export function getBookinfo(bookId) {
  return request({
    url: '/base/bookinfo/' + bookId,
    method: 'get'
  })
}

// 新增图书信息
export function addBookinfo(data) {
  return request({
    url: '/base/bookinfo',
    method: 'post',
    data: data
  })
}

// 修改图书信息
export function updateBookinfo(data) {
  return request({
    url: '/base/bookinfo',
    method: 'put',
    data: data
  })
}

// 删除图书信息
export function delBookinfo(bookId) {
  return request({
    url: '/base/bookinfo/' + bookId,
    method: 'delete'
  })
}
