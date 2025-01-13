import request from '@/utils/request'

// 查询消息异常日志列表
export function listMessage_error_log(query) {
  return request({
    url: '/client/message_error_log/list',
    method: 'get',
    params: query
  })
}

// 查询消息异常日志详细
export function getMessage_error_log(messageErrorLogId) {
  return request({
    url: '/client/message_error_log/' + messageErrorLogId,
    method: 'get'
  })
}

// 新增消息异常日志
export function addMessage_error_log(data) {
  return request({
    url: '/client/message_error_log',
    method: 'post',
    data: data
  })
}

// 修改消息异常日志
export function updateMessage_error_log(data) {
  return request({
    url: '/client/message_error_log',
    method: 'put',
    data: data
  })
}

// 删除消息异常日志
export function delMessage_error_log(messageErrorLogId) {
  return request({
    url: '/client/message_error_log/' + messageErrorLogId,
    method: 'delete'
  })
}
