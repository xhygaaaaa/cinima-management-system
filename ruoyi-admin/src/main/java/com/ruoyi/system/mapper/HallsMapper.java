package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Halls;

/**
 * 电影院内各影厅的信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface HallsMapper 
{
    /**
     * 查询电影院内各影厅的信息
     * 
     * @param hallId 电影院内各影厅的信息主键
     * @return 电影院内各影厅的信息
     */
    public Halls selectHallsByHallId(Long hallId);

    /**
     * 查询电影院内各影厅的信息列表
     * 
     * @param halls 电影院内各影厅的信息
     * @return 电影院内各影厅的信息集合
     */
    public List<Halls> selectHallsList(Halls halls);

    /**
     * 新增电影院内各影厅的信息
     * 
     * @param halls 电影院内各影厅的信息
     * @return 结果
     */
    public int insertHalls(Halls halls);

    /**
     * 修改电影院内各影厅的信息
     * 
     * @param halls 电影院内各影厅的信息
     * @return 结果
     */
    public int updateHalls(Halls halls);

    /**
     * 删除电影院内各影厅的信息
     * 
     * @param hallId 电影院内各影厅的信息主键
     * @return 结果
     */
    public int deleteHallsByHallId(Long hallId);

    /**
     * 批量删除电影院内各影厅的信息
     * 
     * @param hallIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHallsByHallIds(Long[] hallIds);
}
