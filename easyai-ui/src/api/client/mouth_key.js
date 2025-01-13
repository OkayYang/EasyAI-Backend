import request from '@/utils/request'

// 查询口令管理列表
export function listMouth_key(query) {
  return request({
    url: '/client/mouth_key/list',
    method: 'get',
    params: query
  })
}

// 查询口令管理详细
export function getMouth_key(mouthKeyId) {
  return request({
    url: '/client/mouth_key/' + mouthKeyId,
    method: 'get'
  })
}

// 新增口令管理
export function addMouth_key(data) {
  return request({
    url: '/client/mouth_key',
    method: 'post',
    data: data
  })
}

// 修改口令管理
export function updateMouth_key(data) {
  return request({
    url: '/client/mouth_key',
    method: 'put',
    data: data
  })
}

// 删除口令管理
export function delMouth_key(mouthKeyId) {
  return request({
    url: '/client/mouth_key/' + mouthKeyId,
    method: 'delete'
  })
}
