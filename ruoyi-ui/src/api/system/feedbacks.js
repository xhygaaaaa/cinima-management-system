import request from '@/utils/request'

// 查询用户评价反馈列表
export function listFeedbacks(query) {
  return request({
    url: '/system/feedbacks/list',
    method: 'get',
    params: query
  })
}

// 查询用户评价反馈详细
export function getFeedbacks(feedbackId) {
  return request({
    url: '/system/feedbacks/' + feedbackId,
    method: 'get'
  })
}

// 新增用户评价反馈
export function addFeedbacks(data) {
  return request({
    url: '/system/feedbacks',
    method: 'post',
    data: data
  })
}

// 修改用户评价反馈
export function updateFeedbacks(data) {
  return request({
    url: '/system/feedbacks',
    method: 'put',
    data: data
  })
}

// 删除用户评价反馈
export function delFeedbacks(feedbackId) {
  return request({
    url: '/system/feedbacks/' + feedbackId,
    method: 'delete'
  })
}
