import request from '@/utils/request'

// 查询AI 模型列表
export function listChatModel(query) {
  return request({
    url: '/client/chatModel/list',
    method: 'get',
    params: query
  })
}

// 查询AI 模型详细
export function getChatModel(id) {
  return request({
    url: '/client/chatModel/' + id,
    method: 'get'
  })
}

// 新增AI 模型
export function addChatModel(data) {
  return request({
    url: '/client/chatModel',
    method: 'post',
    data: data
  })
}

// 修改AI 模型
export function updateChatModel(data) {
  return request({
    url: '/client/chatModel',
    method: 'put',
    data: data
  })
}

// 删除AI 模型
export function delChatModel(id) {
  return request({
    url: '/client/chatModel/' + id,
    method: 'delete'
  })
}
