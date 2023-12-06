import request from '@/utils/request'

// 查询书架信息列表
export function listBookshelf(query) {
  return request({
    url: '/base/bookshelf/list',
    method: 'get',
    params: query
  })
}

// 查询书架信息详细
export function getBookshelf(bookshelfId) {
  return request({
    url: '/base/bookshelf/' + bookshelfId,
    method: 'get'
  })
}

// 新增书架信息
export function addBookshelf(data) {
  return request({
    url: '/base/bookshelf',
    method: 'post',
    data: data
  })
}

// 修改书架信息
export function updateBookshelf(data) {
  return request({
    url: '/base/bookshelf',
    method: 'put',
    data: data
  })
}

// 删除书架信息
export function delBookshelf(bookshelfId) {
  return request({
    url: '/base/bookshelf/' + bookshelfId,
    method: 'delete'
  })
}
