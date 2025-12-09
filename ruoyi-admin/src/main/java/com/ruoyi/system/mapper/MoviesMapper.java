package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Movies;

/**
 * 电影信息管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface MoviesMapper 
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
     * 删除电影信息管理
     * 
     * @param movieId 电影信息管理主键
     * @return 结果
     */
    public int deleteMoviesByMovieId(Long movieId);

    /**
     * 批量删除电影信息管理
     * 
     * @param movieIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMoviesByMovieIds(Long[] movieIds);
}
