package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HallsMapper;
import com.ruoyi.system.domain.Halls;
import com.ruoyi.system.service.IHallsService;

/**
 * 电影院内各影厅的信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class HallsServiceImpl implements IHallsService 
{
    @Autowired
    private HallsMapper hallsMapper;

    /**
     * 查询电影院内各影厅的信息
     * 
     * @param hallId 电影院内各影厅的信息主键
     * @return 电影院内各影厅的信息
     */
    @Override
    public Halls selectHallsByHallId(Long hallId)
    {
        return hallsMapper.selectHallsByHallId(hallId);
    }

    /**
     * 查询电影院内各影厅的信息列表
     * 
     * @param halls 电影院内各影厅的信息
     * @return 电影院内各影厅的信息
     */
    @Override
    public List<Halls> selectHallsList(Halls halls)
    {
        return hallsMapper.selectHallsList(halls);
    }

    /**
     * 新增电影院内各影厅的信息
     * 
     * @param halls 电影院内各影厅的信息
     * @return 结果
     */
    @Override
    public int insertHalls(Halls halls)
    {
        return hallsMapper.insertHalls(halls);
    }

    /**
     * 修改电影院内各影厅的信息
     * 
     * @param halls 电影院内各影厅的信息
     * @return 结果
     */
    @Override
    public int updateHalls(Halls halls)
    {
        return hallsMapper.updateHalls(halls);
    }

    /**
     * 批量删除电影院内各影厅的信息
     * 
     * @param hallIds 需要删除的电影院内各影厅的信息主键
     * @return 结果
     */
    @Override
    public int deleteHallsByHallIds(Long[] hallIds)
    {
        return hallsMapper.deleteHallsByHallIds(hallIds);
    }

    /**
     * 删除电影院内各影厅的信息信息
     * 
     * @param hallId 电影院内各影厅的信息主键
     * @return 结果
     */
    @Override
    public int deleteHallsByHallId(Long hallId)
    {
        return hallsMapper.deleteHallsByHallId(hallId);
    }
}
