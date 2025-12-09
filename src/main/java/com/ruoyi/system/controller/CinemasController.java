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
import com.ruoyi.system.domain.Cinemas;
import com.ruoyi.system.service.ICinemasService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影院信息管理Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/cinemas")
public class CinemasController extends BaseController
{
    @Autowired
    private ICinemasService cinemasService;

    /**
     * 查询电影院信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cinemas cinemas)
    {
        startPage();
        List<Cinemas> list = cinemasService.selectCinemasList(cinemas);
        return getDataTable(list);
    }

    /**
     * 导出电影院信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:export')")
    @Log(title = "电影院信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cinemas cinemas)
    {
        List<Cinemas> list = cinemasService.selectCinemasList(cinemas);
        ExcelUtil<Cinemas> util = new ExcelUtil<Cinemas>(Cinemas.class);
        util.exportExcel(response, list, "电影院信息管理数据");
    }

    /**
     * 获取电影院信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:query')")
    @GetMapping(value = "/{cinemaId}")
    public AjaxResult getInfo(@PathVariable("cinemaId") Long cinemaId)
    {
        return success(cinemasService.selectCinemasByCinemaId(cinemaId));
    }

    /**
     * 新增电影院信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:add')")
    @Log(title = "电影院信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cinemas cinemas)
    {
        return toAjax(cinemasService.insertCinemas(cinemas));
    }

    /**
     * 修改电影院信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:edit')")
    @Log(title = "电影院信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cinemas cinemas)
    {
        return toAjax(cinemasService.updateCinemas(cinemas));
    }

    /**
     * 删除电影院信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:cinemas:remove')")
    @Log(title = "电影院信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cinemaIds}")
    public AjaxResult remove(@PathVariable Long[] cinemaIds)
    {
        return toAjax(cinemasService.deleteCinemasByCinemaIds(cinemaIds));
    }
}
