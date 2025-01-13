import request from '@/utils/request'

// 查询消息日志列表
export function listMessage_log(query) {
  return request({
    url: '/client/message_log/list',
    method: 'get',
    params: query
  })
}

// 查询消息日志详细
export function getMessage_log(messageLogId) {
  return request({
    url: '/client/message_log/' + messageLogId,
    method: 'get'
  })
}

// 新增消息日志
export function addMessage_log(data) {
  return request({
    url: '/client/message_log',
    method: 'post',
    data: data
  })
}

// 修改消息日志
export function updateMessage_log(data) {
  return request({
    url: '/client/message_log',
    method: 'put',
    data: data
  })
}

// 删除消息日志
export function delMessage_log(messageLogId) {
  return request({
    url: '/client/message_log/' + messageLogId,
    method: 'delete'
  })
}
