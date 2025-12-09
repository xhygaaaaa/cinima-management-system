package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Orders;

/**
 * 用户电影票订单信息Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface IOrdersService 
{
    /**
     * 查询用户电影票订单信息
     * 
     * @param orderId 用户电影票订单信息主键
     * @return 用户电影票订单信息
     */
    public Orders selectOrdersByOrderId(Long orderId);

    /**
     * 查询用户电影票订单信息列表
     * 
     * @param orders 用户电影票订单信息
     * @return 用户电影票订单信息集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增用户电影票订单信息
     * 
     * @param orders 用户电影票订单信息
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改用户电影票订单信息
     * 
     * @param orders 用户电影票订单信息
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 批量删除用户电影票订单信息
     * 
     * @param orderIds 需要删除的用户电影票订单信息主键集合
     * @return 结果
     */
    public int deleteOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除用户电影票订单信息信息
     * 
     * @param orderId 用户电影票订单信息主键
     * @return 结果
     */
    public int deleteOrdersByOrderId(Long orderId);
}
