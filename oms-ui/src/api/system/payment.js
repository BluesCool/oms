import request from '@/utils/request'

// 查询付款列表
export function listPayment(query) {
  return request({
    url: '/system/payment/list',
    method: 'get',
    params: query
  })
}

// 查询付款详细
export function getPayment(importPaymentId) {
  return request({
    url: '/system/payment/' + importPaymentId,
    method: 'get'
  })
}

//修改状态
export function changePaymentStatus(importPaymentId, status) {
  const data = {
    importPaymentId,
    status
  }
  return request({
    url: '/system/payment/changeStatus',
    method: 'put',
    data: data
  })
}

// 新增付款
export function addPayment(data) {
  return request({
    url: '/system/payment',
    method: 'post',
    data: data
  })
}

// 修改付款
export function updatePayment(data) {
  return request({
    url: '/system/payment',
    method: 'put',
    data: data
  })
}

// 删除付款
export function delPayment(importPaymentId) {
  return request({
    url: '/system/payment/' + importPaymentId,
    method: 'delete'
  })
}
