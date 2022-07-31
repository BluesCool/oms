import request from '@/utils/request'

export function changePaymentStatus(exportPaymentId, status) {
  const data = {
    exportPaymentId,
    status
  }
  return request({
    url: '/system/exportPayment/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询出口付款列表
export function listExportPayment(query) {
  return request({
    url: '/system/exportPayment/list',
    method: 'get',
    params: query
  })
}

// 查询出口付款详细
export function getExportPayment(exportPaymentId) {
  return request({
    url: '/system/exportPayment/' + exportPaymentId,
    method: 'get'
  })
}

// 新增出口付款
export function addExportPayment(data) {
  return request({
    url: '/system/exportPayment',
    method: 'post',
    data: data
  })
}

// 修改出口付款
export function updateExportPayment(data) {
  return request({
    url: '/system/exportPayment',
    method: 'put',
    data: data
  })
}

// 删除出口付款
export function delExportPayment(exportPaymentId) {
  return request({
    url: '/system/exportPayment/' + exportPaymentId,
    method: 'delete'
  })
}
