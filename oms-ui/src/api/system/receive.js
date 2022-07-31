import request from '@/utils/request'

// 查询收款列表
export function listReceive(query) {
  return request({
    url: '/system/receive/list',
    method: 'get',
    params: query
  })
}

// 查询收款详细
export function getReceive(importReceivePaymentId) {
  return request({
    url: '/system/receive/' + importReceivePaymentId,
    method: 'get'
  })
}

// 收款状态修改
export function changeReceiveStatus(importReceivePaymentId, status) {
  const data = {
    importReceivePaymentId,
    status
  }
  return request({
    url: '/system/receive/changeStatus',
    method: 'put',
    data: data
  })
}

//获取基础数据
export function getReceiveBaseData() {
  return request({
    url: '/system/receive/baseData' ,
    method: 'get'
  })
}

// 新增收款
export function addReceive(data) {
  return request({
    url: '/system/receive',
    method: 'post',
    data: data
  })
}

// 修改收款
export function updateReceive(data) {
  return request({
    url: '/system/receive',
    method: 'put',
    data: data
  })
}

// 删除收款
export function delReceive(importReceivePaymentId) {
  return request({
    url: '/system/receive/' + importReceivePaymentId,
    method: 'delete'
  })
}
