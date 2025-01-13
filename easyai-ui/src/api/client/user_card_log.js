import request from '@/utils/request'

// 查询用户卡密日志列表
export function listUser_card_log(query) {
  return request({
    url: '/client/user_card_log/list',
    method: 'get',
    params: query
  })
}

// 查询用户卡密日志详细
export function getUser_card_log(userCardId) {
  return request({
    url: '/client/user_card_log/' + userCardId,
    method: 'get'
  })
}

// 新增用户卡密日志
export function addUser_card_log(data) {
  return request({
    url: '/client/user_card_log',
    method: 'post',
    data: data
  })
}

// 修改用户卡密日志
export function updateUser_card_log(data) {
  return request({
    url: '/client/user_card_log',
    method: 'put',
    data: data
  })
}

// 删除用户卡密日志
export function delUser_card_log(userCardId) {
  return request({
    url: '/client/user_card_log/' + userCardId,
    method: 'delete'
  })
}
