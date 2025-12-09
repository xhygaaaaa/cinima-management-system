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
import com.ruoyi.system.domain.Feedbacks;
import com.ruoyi.system.service.IFeedbacksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户评价反馈Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/system/feedbacks")
public class FeedbacksController extends BaseController
{
    @Autowired
    private IFeedbacksService feedbacksService;

    /**
     * 查询用户评价反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:list')")
    @GetMapping("/list")
    public TableDataInfo list(Feedbacks feedbacks)
    {
        startPage();
        List<Feedbacks> list = feedbacksService.selectFeedbacksList(feedbacks);
        return getDataTable(list);
    }

    /**
     * 导出用户评价反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:export')")
    @Log(title = "用户评价反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Feedbacks feedbacks)
    {
        List<Feedbacks> list = feedbacksService.selectFeedbacksList(feedbacks);
        ExcelUtil<Feedbacks> util = new ExcelUtil<Feedbacks>(Feedbacks.class);
        util.exportExcel(response, list, "用户评价反馈数据");
    }

    /**
     * 获取用户评价反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:query')")
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId)
    {
        return success(feedbacksService.selectFeedbacksByFeedbackId(feedbackId));
    }

    /**
     * 新增用户评价反馈
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:add')")
    @Log(title = "用户评价反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Feedbacks feedbacks)
    {
        return toAjax(feedbacksService.insertFeedbacks(feedbacks));
    }

    /**
     * 修改用户评价反馈
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:edit')")
    @Log(title = "用户评价反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Feedbacks feedbacks)
    {
        return toAjax(feedbacksService.updateFeedbacks(feedbacks));
    }

    /**
     * 删除用户评价反馈
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:remove')")
    @Log(title = "用户评价反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds)
    {
        return toAjax(feedbacksService.deleteFeedbacksByFeedbackIds(feedbackIds));
    }
}
