import request from '@/utils/request'

// 查询入库管理列表
export function listInbound(query) {
  return request({
    url: '/inbound/inbound/list',
    method: 'get',
    params: query
  })
}

// 查询入库管理详细
export function getInbound(inboundId) {
  return request({
    url: '/inbound/inbound/' + inboundId,
    method: 'get'
  })
}

// 新增入库管理
export function addInbound(data) {
  return request({
    url: '/inbound/inbound',
    method: 'post',
    data: data
  })
}

// 修改入库管理
export function updateInbound(data) {
  return request({
    url: '/inbound/inbound',
    method: 'put',
    data: data
  })
}

// 删除入库管理
export function delInbound(inboundId) {
  return request({
    url: '/inbound/inbound/' + inboundId,
    method: 'delete'
  })
}
