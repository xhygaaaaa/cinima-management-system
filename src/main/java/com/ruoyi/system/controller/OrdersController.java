package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.service.IOrdersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户电影票订单信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/orders")
public class OrdersController extends BaseController
{
    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询用户电影票订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(Orders orders)
    {
        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
        return getDataTable(list);
    }

    /**
     * 导出用户电影票订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:export')")
    @Log(title = "用户电影票订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Orders orders)
    {
        List<Orders> list = ordersService.selectOrdersList(orders);
        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
        util.exportExcel(response, list, "用户电影票订单信息数据");
    }

    /**
     * 获取用户电影票订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(ordersService.selectOrdersByOrderId(orderId));
    }

    /**
     * 新增用户电影票订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:add')")
    @Log(title = "用户电影票订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Orders orders)
    {
        return toAjax(ordersService.insertOrders(orders));
    }

    /**
     * 修改用户电影票订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:edit')")
    @Log(title = "用户电影票订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Orders orders)
    {
        return toAjax(ordersService.updateOrders(orders));
    }

    /**
     * 删除用户电影票订单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:remove')")
    @Log(title = "用户电影票订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(ordersService.deleteOrdersByOrderIds(orderIds));
    }
}
