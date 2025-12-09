import request from '@/utils/request'

// 查询电影信息管理列表
export function listMovies(query) {
  return request({
    url: '/system/movies/list',
    method: 'get',
    params: query
  })
}

// 查询电影信息管理详细
export function getMovies(movieId) {
  return request({
    url: '/system/movies/' + movieId,
    method: 'get'
  })
}

// 新增电影信息管理
export function addMovies(data) {
  return request({
    url: '/system/movies',
    method: 'post',
    data: data
  })
}

// 修改电影信息管理
export function updateMovies(data) {
  return request({
    url: '/system/movies',
    method: 'put',
    data: data
  })
}

// 删除电影信息管理
export function delMovies(movieId) {
  return request({
    url: '/system/movies/' + movieId,
    method: 'delete'
  })
}
