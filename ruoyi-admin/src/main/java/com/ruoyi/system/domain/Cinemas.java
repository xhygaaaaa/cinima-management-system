package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影院信息管理对象 cinemas
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Cinemas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电影院序号 */
    private Long cinemaId;

    /** 电影院名称 */
    @Excel(name = "电影院名称")
    private String name;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 营业时间 */
    @Excel(name = "营业时间")
    private String businessHours;

    /** 设施信息 */
    @Excel(name = "设施信息")
    private String facilities;

    public void setCinemaId(Long cinemaId) 
    {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() 
    {
        return cinemaId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }

    public void setBusinessHours(String businessHours) 
    {
        this.businessHours = businessHours;
    }

    public String getBusinessHours() 
    {
        return businessHours;
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
            .append("cinemaId", getCinemaId())
            .append("name", getName())
            .append("address", getAddress())
            .append("contact", getContact())
            .append("businessHours", getBusinessHours())
            .append("facilities", getFacilities())
            .toString();
    }
}
