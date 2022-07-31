import request from '@/utils/request'

export function changeReceiveStatus(exportReceivePaymentId,status) {
  const data = {
    exportReceivePaymentId,
    status
  }
  return request({
    url: '/system/exportReceive/changeStatus',
    method: 'put',
    data: data
  })
}


// 查询出口收款列表
export function listExportReceive(query) {
  return request({
    url: '/system/exportReceive/list',
    method: 'get',
    params: query
  })
}

// 查询出口收款详细
export function getExportReceive(exportReceivePaymentId) {
  return request({
    url: '/system/exportReceive/' + exportReceivePaymentId,
    method: 'get'
  })
}

// 新增出口收款
export function addExportReceive(data) {
  return request({
    url: '/system/exportReceive',
    method: 'post',
    data: data
  })
}

// 修改出口收款
export function updateExportReceive(data) {
  return request({
    url: '/system/exportReceive',
    method: 'put',
    data: data
  })
}

// 删除出口收款
export function delExportReceive(exportReceivePaymentId) {
  return request({
    url: '/system/exportReceive/' + exportReceivePaymentId,
    method: 'delete'
  })
}
