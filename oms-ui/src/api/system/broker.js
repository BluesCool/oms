import request from '@/utils/request'

// 查询报关行列表
export function listBroker(query) {
  return request({
    url: '/system/broker/list',
    method: 'get',
    params: query
  })
}

// 查询报关行详细
export function getBroker(customsBrokerId) {
  return request({
    url: '/system/broker/' + customsBrokerId,
    method: 'get'
  })
}

// 新增报关行
export function addBroker(data) {
  return request({
    url: '/system/broker',
    method: 'post',
    data: data
  })
}

// 修改报关行
export function updateBroker(data) {
  return request({
    url: '/system/broker',
    method: 'put',
    data: data
  })
}

// 删除报关行
export function delBroker(customsBrokerId) {
  return request({
    url: '/system/broker/' + customsBrokerId,
    method: 'delete'
  })
}
