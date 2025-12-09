package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Schedules;
import com.ruoyi.system.service.ISchedulesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影具体放映安排Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/schedules")
public class SchedulesController extends BaseController
{
    @Autowired
    private ISchedulesService schedulesService;

    /**
     * 查询电影具体放映安排列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:list')")
    @GetMapping("/list")
    public TableDataInfo list(Schedules schedules)
    {
        startPage();
        List<Schedules> list = schedulesService.selectSchedulesList(schedules);
        return getDataTable(list);
    }

    /**
     * 导出电影具体放映安排列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:export')")
    @Log(title = "电影具体放映安排", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Schedules schedules)
    {
        List<Schedules> list = schedulesService.selectSchedulesList(schedules);
        ExcelUtil<Schedules> util = new ExcelUtil<Schedules>(Schedules.class);
        util.exportExcel(response, list, "电影具体放映安排数据");
    }

    /**
     * 获取电影具体放映安排详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(schedulesService.selectSchedulesByScheduleId(scheduleId));
    }

    /**
     * 新增电影具体放映安排
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:add')")
    @Log(title = "电影具体放映安排", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Schedules schedules)
    {
        return toAjax(schedulesService.insertSchedules(schedules));
    }

    /**
     * 修改电影具体放映安排
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:edit')")
    @Log(title = "电影具体放映安排", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Schedules schedules)
    {
        return toAjax(schedulesService.updateSchedules(schedules));
    }

    /**
     * 删除电影具体放映安排
     */
    @PreAuthorize("@ss.hasPermi('system:schedules:remove')")
    @Log(title = "电影具体放映安排", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(schedulesService.deleteSchedulesByScheduleIds(scheduleIds));
    }
}
