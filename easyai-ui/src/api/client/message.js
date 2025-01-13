import request from '@/utils/request'

// 查询消息列表
export function listMessage(query) {
  return request({
    url: '/client/message/list',
    method: 'get',
    params: query
  })
}

// 查询消息详细
export function getMessage(messageId) {
  return request({
    url: '/client/message/' + messageId,
    method: 'get'
  })
}

// 新增消息
export function addMessage(data) {
  return request({
    url: '/client/message',
    method: 'post',
    data: data
  })
}

// 修改消息
export function updateMessage(data) {
  return request({
    url: '/client/message',
    method: 'put',
    data: data
  })
}

// 删除消息
export function delMessage(messageId) {
  return request({
    url: '/client/message/' + messageId,
    method: 'delete'
  })
}
