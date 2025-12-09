package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Schedules;

/**
 * 电影具体放映安排Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface SchedulesMapper 
{
    /**
     * 查询电影具体放映安排
     * 
     * @param scheduleId 电影具体放映安排主键
     * @return 电影具体放映安排
     */
    public Schedules selectSchedulesByScheduleId(Long scheduleId);

    /**
     * 查询电影具体放映安排列表
     * 
     * @param schedules 电影具体放映安排
     * @return 电影具体放映安排集合
     */
    public List<Schedules> selectSchedulesList(Schedules schedules);

    /**
     * 新增电影具体放映安排
     * 
     * @param schedules 电影具体放映安排
     * @return 结果
     */
    public int insertSchedules(Schedules schedules);

    /**
     * 修改电影具体放映安排
     * 
     * @param schedules 电影具体放映安排
     * @return 结果
     */
    public int updateSchedules(Schedules schedules);

    /**
     * 删除电影具体放映安排
     * 
     * @param scheduleId 电影具体放映安排主键
     * @return 结果
     */
    public int deleteSchedulesByScheduleId(Long scheduleId);

    /**
     * 批量删除电影具体放映安排
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchedulesByScheduleIds(Long[] scheduleIds);
}
