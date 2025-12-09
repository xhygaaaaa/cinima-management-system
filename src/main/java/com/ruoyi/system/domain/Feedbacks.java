package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户评价反馈对象 feedbacks
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Feedbacks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈记录序号 */
    private Long feedbackId;

    /** 提交反馈的用户序号 */
    @Excel(name = "提交反馈的用户序号")
    private Long userId;

    /** 被评价的电影院序号 */
    @Excel(name = "被评价的电影院序号")
    private Long cinemaId;

    /** 被评价的电影序号 */
    @Excel(name = "被评价的电影序号")
    private Long movieId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal rating;

    /** 反馈提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackTime;

    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setCinemaId(Long cinemaId) 
    {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() 
    {
        return cinemaId;
    }

    public void setMovieId(Long movieId) 
    {
        this.movieId = movieId;
    }

    public Long getMovieId() 
    {
        return movieId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setRating(BigDecimal rating) 
    {
        this.rating = rating;
    }

    public BigDecimal getRating() 
    {
        return rating;
    }

    public void setFeedbackTime(Date feedbackTime) 
    {
        this.feedbackTime = feedbackTime;
    }

    public Date getFeedbackTime() 
    {
        return feedbackTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedbackId", getFeedbackId())
            .append("userId", getUserId())
            .append("cinemaId", getCinemaId())
            .append("movieId", getMovieId())
            .append("content", getContent())
            .append("rating", getRating())
            .append("feedbackTime", getFeedbackTime())
            .toString();
    }
}
