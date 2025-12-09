package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Feedbacks;

/**
 * 用户评价反馈Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface IFeedbacksService 
{
    /**
     * 查询用户评价反馈
     * 
     * @param feedbackId 用户评价反馈主键
     * @return 用户评价反馈
     */
    public Feedbacks selectFeedbacksByFeedbackId(Long feedbackId);

    /**
     * 查询用户评价反馈列表
     * 
     * @param feedbacks 用户评价反馈
     * @return 用户评价反馈集合
     */
    public List<Feedbacks> selectFeedbacksList(Feedbacks feedbacks);

    /**
     * 新增用户评价反馈
     * 
     * @param feedbacks 用户评价反馈
     * @return 结果
     */
    public int insertFeedbacks(Feedbacks feedbacks);

    /**
     * 修改用户评价反馈
     * 
     * @param feedbacks 用户评价反馈
     * @return 结果
     */
    public int updateFeedbacks(Feedbacks feedbacks);

    /**
     * 批量删除用户评价反馈
     * 
     * @param feedbackIds 需要删除的用户评价反馈主键集合
     * @return 结果
     */
    public int deleteFeedbacksByFeedbackIds(Long[] feedbackIds);

    /**
     * 删除用户评价反馈信息
     * 
     * @param feedbackId 用户评价反馈主键
     * @return 结果
     */
    public int deleteFeedbacksByFeedbackId(Long feedbackId);
}
