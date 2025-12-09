package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Cinemas;

/**
 * 电影院信息管理Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface ICinemasService 
{
    /**
     * 查询电影院信息管理
     * 
     * @param cinemaId 电影院信息管理主键
     * @return 电影院信息管理
     */
    public Cinemas selectCinemasByCinemaId(Long cinemaId);

    /**
     * 查询电影院信息管理列表
     * 
     * @param cinemas 电影院信息管理
     * @return 电影院信息管理集合
     */
    public List<Cinemas> selectCinemasList(Cinemas cinemas);

    /**
     * 新增电影院信息管理
     * 
     * @param cinemas 电影院信息管理
     * @return 结果
     */
    public int insertCinemas(Cinemas cinemas);

    /**
     * 修改电影院信息管理
     * 
     * @param cinemas 电影院信息管理
     * @return 结果
     */
    public int updateCinemas(Cinemas cinemas);

    /**
     * 批量删除电影院信息管理
     * 
     * @param cinemaIds 需要删除的电影院信息管理主键集合
     * @return 结果
     */
    public int deleteCinemasByCinemaIds(Long[] cinemaIds);

    /**
     * 删除电影院信息管理信息
     * 
     * @param cinemaId 电影院信息管理主键
     * @return 结果
     */
    public int deleteCinemasByCinemaId(Long cinemaId);
}
