import request from '@/utils/request'

// 查询用户会话列表
export function listChat(query) {
  return request({
    url: '/client/chat/list',
    method: 'get',
    params: query
  })
}

// 查询用户会话详细
export function getChat(sessionId) {
  return request({
    url: '/client/chat/' + sessionId,
    method: 'get'
  })
}

// 新增用户会话
export function addChat(data) {
  return request({
    url: '/client/chat',
    method: 'post',
    data: data
  })
}

// 修改用户会话
export function updateChat(data) {
  return request({
    url: '/client/chat',
    method: 'put',
    data: data
  })
}

// 删除用户会话
export function delChat(sessionId) {
  return request({
    url: '/client/chat/' + sessionId,
    method: 'delete'
  })
}
