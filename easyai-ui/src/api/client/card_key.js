import request from '@/utils/request'

// 查询卡密列表
export function listCard_key(query) {
  return request({
    url: '/client/card_key/list',
    method: 'get',
    params: query
  })
}

// 查询卡密详细
export function getCard_key(cardKeyId) {
  return request({
    url: '/client/card_key/' + cardKeyId,
    method: 'get'
  })
}

// 新增卡密
export function addCard_key(data) {
  return request({
    url: '/client/card_key',
    method: 'post',
    data: data
  })
}

// 批量新增卡密
export function addBatchCard_key(data) {
  return request({
    url: '/client/card_key/batchAddCard',
    method: 'post',
    data: data
  })
}

// 修改卡密
export function updateCard_key(data) {
  return request({
    url: '/client/card_key',
    method: 'put',
    data: data
  })
}

// 删除卡密
export function delCard_key(cardKeyId) {
  return request({
    url: '/client/card_key/' + cardKeyId,
    method: 'delete'
  })
}
