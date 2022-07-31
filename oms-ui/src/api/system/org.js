import request from '@/utils/request'

// 查询组织机构列表
export function listOrg(query) {
  return request({
    url: '/system/org/list',
    method: 'get',
    params: query
  })
}

// 查询组织机构详细
export function getOrg(orgId) {
  return request({
    url: '/system/org/' + orgId,
    method: 'get'
  })
}

// 新增组织机构
export function addOrg(data) {
  return request({
    url: '/system/org',
    method: 'post',
    data: data
  })
}

// 修改组织机构
export function updateOrg(data) {
  return request({
    url: '/system/org',
    method: 'put',
    data: data
  })
}

// 删除组织机构
export function delOrg(orgId) {
  return request({
    url: '/system/org/' + orgId,
    method: 'delete'
  })
}
