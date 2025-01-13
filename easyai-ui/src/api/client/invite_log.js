import request from '@/utils/request'

// 查询用户邀请日志列表
export function listInvite_log(query) {
  return request({
    url: '/client/invite_log/list',
    method: 'get',
    params: query
  })
}

// 查询用户邀请日志详细
export function getInvite_log(inviteId) {
  return request({
    url: '/client/invite_log/' + inviteId,
    method: 'get'
  })
}

// 新增用户邀请日志
export function addInvite_log(data) {
  return request({
    url: '/client/invite_log',
    method: 'post',
    data: data
  })
}

// 修改用户邀请日志
export function updateInvite_log(data) {
  return request({
    url: '/client/invite_log',
    method: 'put',
    data: data
  })
}

// 删除用户邀请日志
export function delInvite_log(inviteId) {
  return request({
    url: '/client/invite_log/' + inviteId,
    method: 'delete'
  })
}
