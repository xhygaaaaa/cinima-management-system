import request from '@/utils/request'

// 查询电影院内各影厅的信息列表
export function listHalls(query) {
  return request({
    url: '/system/halls/list',
    method: 'get',
    params: query
  })
}

// 查询电影院内各影厅的信息详细
export function getHalls(hallId) {
  return request({
    url: '/system/halls/' + hallId,
    method: 'get'
  })
}

// 新增电影院内各影厅的信息
export function addHalls(data) {
  return request({
    url: '/system/halls',
    method: 'post',
    data: data
  })
}

// 修改电影院内各影厅的信息
export function updateHalls(data) {
  return request({
    url: '/system/halls',
    method: 'put',
    data: data
  })
}

// 删除电影院内各影厅的信息
export function delHalls(hallId) {
  return request({
    url: '/system/halls/' + hallId,
    method: 'delete'
  })
}
