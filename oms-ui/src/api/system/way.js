import request from '@/utils/request'

// 查询贸易方式列表
export function listWay(query) {
  return request({
    url: '/system/way/list',
    method: 'get',
    params: query
  })
}

// 查询贸易方式详细
export function getWay(tradeWayId) {
  return request({
    url: '/system/way/' + tradeWayId,
    method: 'get'
  })
}

// 新增贸易方式
export function addWay(data) {
  return request({
    url: '/system/way',
    method: 'post',
    data: data
  })
}

// 修改贸易方式
export function updateWay(data) {
  return request({
    url: '/system/way',
    method: 'put',
    data: data
  })
}

// 删除贸易方式
export function delWay(tradeWayId) {
  return request({
    url: '/system/way/' + tradeWayId,
    method: 'delete'
  })
}
