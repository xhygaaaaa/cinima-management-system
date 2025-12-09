import request from '@/utils/request'

// 查询电影具体放映安排列表
export function listSchedules(query) {
  return request({
    url: '/system/schedules/list',
    method: 'get',
    params: query
  })
}

// 查询电影具体放映安排详细
export function getSchedules(scheduleId) {
  return request({
    url: '/system/schedules/' + scheduleId,
    method: 'get'
  })
}

// 新增电影具体放映安排
export function addSchedules(data) {
  return request({
    url: '/system/schedules',
    method: 'post',
    data: data
  })
}

// 修改电影具体放映安排
export function updateSchedules(data) {
  return request({
    url: '/system/schedules',
    method: 'put',
    data: data
  })
}

// 删除电影具体放映安排
export function delSchedules(scheduleId) {
  return request({
    url: '/system/schedules/' + scheduleId,
    method: 'delete'
  })
}
