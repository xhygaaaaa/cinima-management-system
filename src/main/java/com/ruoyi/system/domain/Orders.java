package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户电影票订单信息对象 orders
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单序号 */
    private Long orderId;

    /** 下单用户序号 */
    @Excel(name = "下单用户序号")
    private Long userId;

    /** 关联的排片记录序号 */
    @Excel(name = "关联的排片记录序号")
    private Long scheduleId;

    /** 购票数量 */
    @Excel(name = "购票数量")
    private Long ticketCount;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String paymentStatus;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setTicketCount(Long ticketCount) 
    {
        this.ticketCount = ticketCount;
    }

    public Long getTicketCount() 
    {
        return ticketCount;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }

    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }

    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("scheduleId", getScheduleId())
            .append("ticketCount", getTicketCount())
            .append("totalAmount", getTotalAmount())
            .append("paymentStatus", getPaymentStatus())
            .append("paymentTime", getPaymentTime())
            .append("orderTime", getOrderTime())
            .toString();
    }
}
