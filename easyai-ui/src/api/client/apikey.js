import request from '@/utils/request'

// 查询密钥管理列表
export function listApikey(query) {
  return request({
    url: '/client/apikey/list',
    method: 'get',
    params: query
  })
}

// 查询密钥管理详细
export function getApikey(id) {
  return request({
    url: '/client/apikey/' + id,
    method: 'get'
  })
}

// 新增密钥管理
export function addApikey(data) {
  return request({
    url: '/client/apikey',
    method: 'post',
    data: data
  })
}

// 修改密钥管理
export function updateApikey(data) {
  return request({
    url: '/client/apikey',
    method: 'put',
    data: data
  })
}

// 删除密钥管理
export function delApikey(id) {
  return request({
    url: '/client/apikey/' + id,
    method: 'delete'
  })
}
