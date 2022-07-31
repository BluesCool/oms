import request from '@/utils/request'

// 查询货币列表
export function listCurrency(query) {
  return request({
    url: '/system/currency/list',
    method: 'get',
    params: query
  })
}

// 查询货币详细
export function getCurrency(currencyId) {
  return request({
    url: '/system/currency/' + currencyId,
    method: 'get'
  })
}

// 新增货币
export function addCurrency(data) {
  return request({
    url: '/system/currency',
    method: 'post',
    data: data
  })
}

// 修改货币
export function updateCurrency(data) {
  return request({
    url: '/system/currency',
    method: 'put',
    data: data
  })
}

// 删除货币
export function delCurrency(currencyId) {
  return request({
    url: '/system/currency/' + currencyId,
    method: 'delete'
  })
}
