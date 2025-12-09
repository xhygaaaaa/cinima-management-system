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
import com.ruoyi.system.domain.Movies;
import com.ruoyi.system.service.IMoviesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影信息管理Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/movies")
public class MoviesController extends BaseController
{
    @Autowired
    private IMoviesService moviesService;

    /**
     * 查询电影信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:movies:list')")
    @GetMapping("/list")
    public TableDataInfo list(Movies movies)
    {
        startPage();
        List<Movies> list = moviesService.selectMoviesList(movies);
        return getDataTable(list);
    }

    /**
     * 导出电影信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:movies:export')")
    @Log(title = "电影信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Movies movies)
    {
        List<Movies> list = moviesService.selectMoviesList(movies);
        ExcelUtil<Movies> util = new ExcelUtil<Movies>(Movies.class);
        util.exportExcel(response, list, "电影信息管理数据");
    }

    /**
     * 获取电影信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:movies:query')")
    @GetMapping(value = "/{movieId}")
    public AjaxResult getInfo(@PathVariable("movieId") Long movieId)
    {
        return success(moviesService.selectMoviesByMovieId(movieId));
    }

    /**
     * 新增电影信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:movies:add')")
    @Log(title = "电影信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Movies movies)
    {
        return toAjax(moviesService.insertMovies(movies));
    }

    /**
     * 修改电影信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:movies:edit')")
    @Log(title = "电影信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Movies movies)
    {
        return toAjax(moviesService.updateMovies(movies));
    }

    /**
     * 删除电影信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:movies:remove')")
    @Log(title = "电影信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieIds}")
    public AjaxResult remove(@PathVariable Long[] movieIds)
    {
        return toAjax(moviesService.deleteMoviesByMovieIds(movieIds));
    }
}
