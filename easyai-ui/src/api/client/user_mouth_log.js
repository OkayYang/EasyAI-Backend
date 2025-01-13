import request from '@/utils/request'

// 查询用户口令日志列表
export function listUser_mouth_log(query) {
  return request({
    url: '/client/user_mouth_log/list',
    method: 'get',
    params: query
  })
}

// 查询用户口令日志详细
export function getUser_mouth_log(mouthUserId) {
  return request({
    url: '/client/user_mouth_log/' + mouthUserId,
    method: 'get'
  })
}

// 新增用户口令日志
export function addUser_mouth_log(data) {
  return request({
    url: '/client/user_mouth_log',
    method: 'post',
    data: data
  })
}

// 修改用户口令日志
export function updateUser_mouth_log(data) {
  return request({
    url: '/client/user_mouth_log',
    method: 'put',
    data: data
  })
}

// 删除用户口令日志
export function delUser_mouth_log(mouthUserId) {
  return request({
    url: '/client/user_mouth_log/' + mouthUserId,
    method: 'delete'
  })
}
