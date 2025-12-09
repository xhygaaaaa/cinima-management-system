package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CinemasMapper;
import com.ruoyi.system.domain.Cinemas;
import com.ruoyi.system.service.ICinemasService;

/**
 * 电影院信息管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class CinemasServiceImpl implements ICinemasService 
{
    @Autowired
    private CinemasMapper cinemasMapper;

    /**
     * 查询电影院信息管理
     * 
     * @param cinemaId 电影院信息管理主键
     * @return 电影院信息管理
     */
    @Override
    public Cinemas selectCinemasByCinemaId(Long cinemaId)
    {
        return cinemasMapper.selectCinemasByCinemaId(cinemaId);
    }

    /**
     * 查询电影院信息管理列表
     * 
     * @param cinemas 电影院信息管理
     * @return 电影院信息管理
     */
    @Override
    public List<Cinemas> selectCinemasList(Cinemas cinemas)
    {
        return cinemasMapper.selectCinemasList(cinemas);
    }

    /**
     * 新增电影院信息管理
     * 
     * @param cinemas 电影院信息管理
     * @return 结果
     */
    @Override
    public int insertCinemas(Cinemas cinemas)
    {
        return cinemasMapper.insertCinemas(cinemas);
    }

    /**
     * 修改电影院信息管理
     * 
     * @param cinemas 电影院信息管理
     * @return 结果
     */
    @Override
    public int updateCinemas(Cinemas cinemas)
    {
        return cinemasMapper.updateCinemas(cinemas);
    }

    /**
     * 批量删除电影院信息管理
     * 
     * @param cinemaIds 需要删除的电影院信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCinemasByCinemaIds(Long[] cinemaIds)
    {
        return cinemasMapper.deleteCinemasByCinemaIds(cinemaIds);
    }

    /**
     * 删除电影院信息管理信息
     * 
     * @param cinemaId 电影院信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCinemasByCinemaId(Long cinemaId)
    {
        return cinemasMapper.deleteCinemasByCinemaId(cinemaId);
    }
}
