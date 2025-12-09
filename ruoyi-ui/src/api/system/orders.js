import request from '@/utils/request'

// 查询用户电影票订单信息列表
export function listOrders(query) {
  return request({
    url: '/system/orders/list',
    method: 'get',
    params: query
  })
}

// 查询用户电影票订单信息详细
export function getOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'get'
  })
}

// 新增用户电影票订单信息
export function addOrders(data) {
  return request({
    url: '/system/orders',
    method: 'post',
    data: data
  })
}

// 修改用户电影票订单信息
export function updateOrders(data) {
  return request({
    url: '/system/orders',
    method: 'put',
    data: data
  })
}

// 删除用户电影票订单信息
export function delOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'delete'
  })
}
