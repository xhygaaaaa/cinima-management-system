package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Movies;

/**
 * 电影信息管理Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface IMoviesService 
{
    /**
     * 查询电影信息管理
     * 
     * @param movieId 电影信息管理主键
     * @return 电影信息管理
     */
    public Movies selectMoviesByMovieId(Long movieId);

    /**
     * 查询电影信息管理列表
     * 
     * @param movies 电影信息管理
     * @return 电影信息管理集合
     */
    public List<Movies> selectMoviesList(Movies movies);

    /**
     * 新增电影信息管理
     * 
     * @param movies 电影信息管理
     * @return 结果
     */
    public int insertMovies(Movies movies);

    /**
     * 修改电影信息管理
     * 
     * @param movies 电影信息管理
     * @return 结果
     */
    public int updateMovies(Movies movies);

    /**
     * 批量删除电影信息管理
     * 
     * @param movieIds 需要删除的电影信息管理主键集合
     * @return 结果
     */
    public int deleteMoviesByMovieIds(Long[] movieIds);

    /**
     * 删除电影信息管理信息
     * 
     * @param movieId 电影信息管理主键
     * @return 结果
     */
    public int deleteMoviesByMovieId(Long movieId);
}
