package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影院内各影厅的信息对象 halls
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Halls extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 影厅序号 */
    private Long hallId;

    /** 所属电影院序号 */
    @Excel(name = "所属电影院序号")
    private Long cinemaId;

    /** 影厅座位总数 */
    @Excel(name = "影厅座位总数")
    private Long capacity;

    /** 座位布局 */
    @Excel(name = "座位布局")
    private String seatLayout;

    /** 影厅类型 */
    @Excel(name = "影厅类型")
    private String hallType;

    /** 影厅设施 */
    @Excel(name = "影厅设施")
    private String facilities;

    public void setHallId(Long hallId) 
    {
        this.hallId = hallId;
    }

    public Long getHallId() 
    {
        return hallId;
    }

    public void setCinemaId(Long cinemaId) 
    {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() 
    {
        return cinemaId;
    }

    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }

    public void setSeatLayout(String seatLayout) 
    {
        this.seatLayout = seatLayout;
    }

    public String getSeatLayout() 
    {
        return seatLayout;
    }

    public void setHallType(String hallType) 
    {
        this.hallType = hallType;
    }

    public String getHallType() 
    {
        return hallType;
    }

    public void setFacilities(String facilities) 
    {
        this.facilities = facilities;
    }

    public String getFacilities() 
    {
        return facilities;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hallId", getHallId())
            .append("cinemaId", getCinemaId())
            .append("capacity", getCapacity())
            .append("seatLayout", getSeatLayout())
            .append("hallType", getHallType())
            .append("facilities", getFacilities())
            .toString();
    }
}
