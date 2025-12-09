package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影具体放映安排对象 schedules
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Schedules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排片记录序号 */
    private Long scheduleId;

    /** 所属电影院序号 */
    @Excel(name = "所属电影院序号")
    private Long cinemaId;

    /** 放映影厅序号 */
    @Excel(name = "放映影厅序号")
    private Long hallId;

    /** 放映电影序号 */
    @Excel(name = "放映电影序号")
    private Long movieId;

    /** 放映时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "放映时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date showTime;

    /** 票价 */
    @Excel(name = "票价")
    private BigDecimal price;

    /** 剩余可售座位数 */
    @Excel(name = "剩余可售座位数")
    private Long seatsAvailable;

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setCinemaId(Long cinemaId) 
    {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() 
    {
        return cinemaId;
    }

    public void setHallId(Long hallId) 
    {
        this.hallId = hallId;
    }

    public Long getHallId() 
    {
        return hallId;
    }

    public void setMovieId(Long movieId) 
    {
        this.movieId = movieId;
    }

    public Long getMovieId() 
    {
        return movieId;
    }

    public void setShowTime(Date showTime) 
    {
        this.showTime = showTime;
    }

    public Date getShowTime() 
    {
        return showTime;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setSeatsAvailable(Long seatsAvailable) 
    {
        this.seatsAvailable = seatsAvailable;
    }

    public Long getSeatsAvailable() 
    {
        return seatsAvailable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("cinemaId", getCinemaId())
            .append("hallId", getHallId())
            .append("movieId", getMovieId())
            .append("showTime", getShowTime())
            .append("price", getPrice())
            .append("seatsAvailable", getSeatsAvailable())
            .toString();
    }
}
