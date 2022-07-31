import request from '@/utils/request'

// 查询出口订单列表
export function listExportOrder(query) {
  return request({
    url: '/system/exportOrder/list',
    method: 'get',
    params: query
  })
}

//获取基础数据
export function getBaseData() {
  return request({
    url: '/system/exportOrder/baseData' ,
    method: 'get'
  })
}

// 查询出口订单详细
export function getExportOrder(exportOrderId) {
  return request({
    url: '/system/exportOrder/' + exportOrderId,
    method: 'get'
  })
}

// 新增出口订单
export function addExportOrder(data) {
  return request({
    url: '/system/exportOrder',
    method: 'post',
    data: data
  })
}

// 修改出口订单
export function updateExportOrder(data) {
  return request({
    url: '/system/exportOrder',
    method: 'put',
    data: data
  })
}

// 删除出口订单
export function delExportOrder(exportOrderId) {
  return request({
    url: '/system/exportOrder/' + exportOrderId,
    method: 'delete'
  })
}
