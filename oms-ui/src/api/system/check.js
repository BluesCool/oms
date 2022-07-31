import request from '@/utils/request'

// 查询查验列表
export function listCheck(query) {
  return request({
    url: '/system/check/list',
    method: 'get',
    params: query
  })
}

// 查询查验详细
export function getCheck(checkId) {
  return request({
    url: '/system/check/' + checkId,
    method: 'get'
  })
}

// 新增查验
export function addCheck(data) {
  return request({
    url: '/system/check',
    method: 'post',
    data: data
  })
}

// 修改查验
export function updateCheck(data) {
  return request({
    url: '/system/check',
    method: 'put',
    data: data
  })
}

// 删除查验
export function delCheck(checkId) {
  return request({
    url: '/system/check/' + checkId,
    method: 'delete'
  })
}
