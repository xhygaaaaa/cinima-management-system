package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FeedbacksMapper;
import com.ruoyi.system.domain.Feedbacks;
import com.ruoyi.system.service.IFeedbacksService;

/**
 * 用户评价反馈Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class FeedbacksServiceImpl implements IFeedbacksService 
{
    @Autowired
    private FeedbacksMapper feedbacksMapper;

    /**
     * 查询用户评价反馈
     * 
     * @param feedbackId 用户评价反馈主键
     * @return 用户评价反馈
     */
    @Override
    public Feedbacks selectFeedbacksByFeedbackId(Long feedbackId)
    {
        return feedbacksMapper.selectFeedbacksByFeedbackId(feedbackId);
    }

    /**
     * 查询用户评价反馈列表
     * 
     * @param feedbacks 用户评价反馈
     * @return 用户评价反馈
     */
    @Override
    public List<Feedbacks> selectFeedbacksList(Feedbacks feedbacks)
    {
        return feedbacksMapper.selectFeedbacksList(feedbacks);
    }

    /**
     * 新增用户评价反馈
     * 
     * @param feedbacks 用户评价反馈
     * @return 结果
     */
    @Override
    public int insertFeedbacks(Feedbacks feedbacks)
    {
        return feedbacksMapper.insertFeedbacks(feedbacks);
    }

    /**
     * 修改用户评价反馈
     * 
     * @param feedbacks 用户评价反馈
     * @return 结果
     */
    @Override
    public int updateFeedbacks(Feedbacks feedbacks)
    {
        return feedbacksMapper.updateFeedbacks(feedbacks);
    }

    /**
     * 批量删除用户评价反馈
     * 
     * @param feedbackIds 需要删除的用户评价反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbacksByFeedbackIds(Long[] feedbackIds)
    {
        return feedbacksMapper.deleteFeedbacksByFeedbackIds(feedbackIds);
    }

    /**
     * 删除用户评价反馈信息
     * 
     * @param feedbackId 用户评价反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbacksByFeedbackId(Long feedbackId)
    {
        return feedbacksMapper.deleteFeedbacksByFeedbackId(feedbackId);
    }
}
