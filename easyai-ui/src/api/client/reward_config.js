import request from '@/utils/request'

// 查询奖励配置列表
export function listReward_config(query) {
  return request({
    url: '/client/reward_config/list',
    method: 'get',
    params: query
  })
}

// 查询奖励配置详细
export function getReward_config(rewardConfigId) {
  return request({
    url: '/client/reward_config/' + rewardConfigId,
    method: 'get'
  })
}

// 新增奖励配置
export function addReward_config(data) {
  return request({
    url: '/client/reward_config',
    method: 'post',
    data: data
  })
}

// 修改奖励配置
export function updateReward_config(data) {
  return request({
    url: '/client/reward_config',
    method: 'put',
    data: data
  })
}

// 删除奖励配置
export function delReward_config(rewardConfigId) {
  return request({
    url: '/client/reward_config/' + rewardConfigId,
    method: 'delete'
  })
}
