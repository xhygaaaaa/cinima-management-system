package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MoviesMapper;
import com.ruoyi.system.domain.Movies;
import com.ruoyi.system.service.IMoviesService;

/**
 * 电影信息管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class MoviesServiceImpl implements IMoviesService 
{
    @Autowired
    private MoviesMapper moviesMapper;

    /**
     * 查询电影信息管理
     * 
     * @param movieId 电影信息管理主键
     * @return 电影信息管理
     */
    @Override
    public Movies selectMoviesByMovieId(Long movieId)
    {
        return moviesMapper.selectMoviesByMovieId(movieId);
    }

    /**
     * 查询电影信息管理列表
     * 
     * @param movies 电影信息管理
     * @return 电影信息管理
     */
    @Override
    public List<Movies> selectMoviesList(Movies movies)
    {
        return moviesMapper.selectMoviesList(movies);
    }

    /**
     * 新增电影信息管理
     * 
     * @param movies 电影信息管理
     * @return 结果
     */
    @Override
    public int insertMovies(Movies movies)
    {
        return moviesMapper.insertMovies(movies);
    }

    /**
     * 修改电影信息管理
     * 
     * @param movies 电影信息管理
     * @return 结果
     */
    @Override
    public int updateMovies(Movies movies)
    {
        return moviesMapper.updateMovies(movies);
    }

    /**
     * 批量删除电影信息管理
     * 
     * @param movieIds 需要删除的电影信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMoviesByMovieIds(Long[] movieIds)
    {
        return moviesMapper.deleteMoviesByMovieIds(movieIds);
    }

    /**
     * 删除电影信息管理信息
     * 
     * @param movieId 电影信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMoviesByMovieId(Long movieId)
    {
        return moviesMapper.deleteMoviesByMovieId(movieId);
    }
}
