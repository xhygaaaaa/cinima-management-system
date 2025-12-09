import request from '@/utils/request'

// 查询电影院信息管理列表
export function listCinemas(query) {
  return request({
    url: '/system/cinemas/list',
    method: 'get',
    params: query
  })
}

// 查询电影院信息管理详细
export function getCinemas(cinemaId) {
  return request({
    url: '/system/cinemas/' + cinemaId,
    method: 'get'
  })
}

// 新增电影院信息管理
export function addCinemas(data) {
  return request({
    url: '/system/cinemas',
    method: 'post',
    data: data
  })
}

// 修改电影院信息管理
export function updateCinemas(data) {
  return request({
    url: '/system/cinemas',
    method: 'put',
    data: data
  })
}

// 删除电影院信息管理
export function delCinemas(cinemaId) {
  return request({
    url: '/system/cinemas/' + cinemaId,
    method: 'delete'
  })
}
