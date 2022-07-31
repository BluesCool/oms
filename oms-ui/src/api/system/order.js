import request from '@/utils/request'
import {parseStrEmpty} from "@/utils/ruoyi";

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/system/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(importOrderId) {
  return request({
    url: '/system/order/' + parseStrEmpty(importOrderId),
    method: 'get'
  })
}

//获取基础数据
export function getBaseData() {
  return request({
    url: '/system/order/baseData' ,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/system/order',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(importOrderId) {
  return request({
    url: '/system/order/' + importOrderId,
    method: 'delete'
  })
}
