package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchedulesMapper;
import com.ruoyi.system.domain.Schedules;
import com.ruoyi.system.service.ISchedulesService;

/**
 * 电影具体放映安排Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class SchedulesServiceImpl implements ISchedulesService 
{
    @Autowired
    private SchedulesMapper schedulesMapper;

    /**
     * 查询电影具体放映安排
     * 
     * @param scheduleId 电影具体放映安排主键
     * @return 电影具体放映安排
     */
    @Override
    public Schedules selectSchedulesByScheduleId(Long scheduleId)
    {
        return schedulesMapper.selectSchedulesByScheduleId(scheduleId);
    }

    /**
     * 查询电影具体放映安排列表
     * 
     * @param schedules 电影具体放映安排
     * @return 电影具体放映安排
     */
    @Override
    public List<Schedules> selectSchedulesList(Schedules schedules)
    {
        return schedulesMapper.selectSchedulesList(schedules);
    }

    /**
     * 新增电影具体放映安排
     * 
     * @param schedules 电影具体放映安排
     * @return 结果
     */
    @Override
    public int insertSchedules(Schedules schedules)
    {
        return schedulesMapper.insertSchedules(schedules);
    }

    /**
     * 修改电影具体放映安排
     * 
     * @param schedules 电影具体放映安排
     * @return 结果
     */
    @Override
    public int updateSchedules(Schedules schedules)
    {
        return schedulesMapper.updateSchedules(schedules);
    }

    /**
     * 批量删除电影具体放映安排
     * 
     * @param scheduleIds 需要删除的电影具体放映安排主键
     * @return 结果
     */
    @Override
    public int deleteSchedulesByScheduleIds(Long[] scheduleIds)
    {
        return schedulesMapper.deleteSchedulesByScheduleIds(scheduleIds);
    }

    /**
     * 删除电影具体放映安排信息
     * 
     * @param scheduleId 电影具体放映安排主键
     * @return 结果
     */
    @Override
    public int deleteSchedulesByScheduleId(Long scheduleId)
    {
        return schedulesMapper.deleteSchedulesByScheduleId(scheduleId);
    }
}
