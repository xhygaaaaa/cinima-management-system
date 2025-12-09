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
import com.ruoyi.system.domain.Halls;
import com.ruoyi.system.service.IHallsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影院内各影厅的信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/halls")
public class HallsController extends BaseController
{
    @Autowired
    private IHallsService hallsService;

    /**
     * 查询电影院内各影厅的信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:halls:list')")
    @GetMapping("/list")
    public TableDataInfo list(Halls halls)
    {
        startPage();
        List<Halls> list = hallsService.selectHallsList(halls);
        return getDataTable(list);
    }

    /**
     * 导出电影院内各影厅的信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:halls:export')")
    @Log(title = "电影院内各影厅的信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Halls halls)
    {
        List<Halls> list = hallsService.selectHallsList(halls);
        ExcelUtil<Halls> util = new ExcelUtil<Halls>(Halls.class);
        util.exportExcel(response, list, "电影院内各影厅的信息数据");
    }

    /**
     * 获取电影院内各影厅的信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:halls:query')")
    @GetMapping(value = "/{hallId}")
    public AjaxResult getInfo(@PathVariable("hallId") Long hallId)
    {
        return success(hallsService.selectHallsByHallId(hallId));
    }

    /**
     * 新增电影院内各影厅的信息
     */
    @PreAuthorize("@ss.hasPermi('system:halls:add')")
    @Log(title = "电影院内各影厅的信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Halls halls)
    {
        return toAjax(hallsService.insertHalls(halls));
    }

    /**
     * 修改电影院内各影厅的信息
     */
    @PreAuthorize("@ss.hasPermi('system:halls:edit')")
    @Log(title = "电影院内各影厅的信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Halls halls)
    {
        return toAjax(hallsService.updateHalls(halls));
    }

    /**
     * 删除电影院内各影厅的信息
     */
    @PreAuthorize("@ss.hasPermi('system:halls:remove')")
    @Log(title = "电影院内各影厅的信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hallIds}")
    public AjaxResult remove(@PathVariable Long[] hallIds)
    {
        return toAjax(hallsService.deleteHallsByHallIds(hallIds));
    }
}
