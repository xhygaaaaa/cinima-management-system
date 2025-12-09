package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影信息管理对象 movies
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Movies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电影序号 */
    private Long movieId;

    /** 电影标题 */
    @Excel(name = "电影标题")
    private String title;

    /** 电影导演 */
    @Excel(name = "电影导演")
    private String director;

    /** 主要演员 */
    @Excel(name = "主要演员")
    private String actors;

    /** 电影类型 */
    @Excel(name = "电影类型")
    private String genre;

    /** 电影时长(分) */
    @Excel(name = "电影时长(分)")
    private Long duration;

    /** 电影上映日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "电影上映日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 电影剧情简介 */
    @Excel(name = "电影剧情简介")
    private String description;

    /** 电影评分（范围0-10） */
    @Excel(name = "电影评分", readConverterExp = "范=围0-10")
    private BigDecimal rating;

    public void setMovieId(Long movieId) 
    {
        this.movieId = movieId;
    }

    public Long getMovieId() 
    {
        return movieId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDirector(String director) 
    {
        this.director = director;
    }

    public String getDirector() 
    {
        return director;
    }

    public void setActors(String actors) 
    {
        this.actors = actors;
    }

    public String getActors() 
    {
        return actors;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public String getGenre() 
    {
        return genre;
    }

    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() 
    {
        return releaseDate;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setRating(BigDecimal rating) 
    {
        this.rating = rating;
    }

    public BigDecimal getRating() 
    {
        return rating;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("movieId", getMovieId())
            .append("title", getTitle())
            .append("director", getDirector())
            .append("actors", getActors())
            .append("genre", getGenre())
            .append("duration", getDuration())
            .append("releaseDate", getReleaseDate())
            .append("description", getDescription())
            .append("rating", getRating())
            .toString();
    }
}
