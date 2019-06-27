package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.Activity;
import com.amateur.sip.server.entity.ActivityJoin;
import com.amateur.sip.server.request.ActivityListRequest;
import com.amateur.sip.server.request.ActivityPublishRequest;
import com.amateur.sip.server.request.ActivityWeightRequest;
import com.amateur.sip.server.response.ActivityListResponse;
import com.amateur.sip.server.response.RegistrationResponse;

public interface ActivityService {
    /**
     * 获取活动列表
     *
     * @param request
     * @return
     */
    ActivityListResponse getActivityList(ActivityListRequest request);

    /**
     * 查看活动详情
     *
     * @param id
     * @return
     */
    Activity getActivityDetail(int id);

    /**
     * 发布活动
     *
     * @param request
     * @return
     */
    Activity publishActivity(ActivityPublishRequest request);

    /**
     * 活动置顶/取消置顶
     *
     * @param request
     * @return
     */
    Boolean toppingActivity(ActivityWeightRequest request);

    /**
     * 修改活动信息
     *
     * @param request
     * @return
     */
    Activity modifyActivity(Activity request);

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    Boolean deleteActivity(int id);

    /**
     * 查看我发布过的活动
     *
     * @param userId
     * @return
     */
    ActivityListResponse getMyPublishedList(int userId, int page);

    /**
     * 查看我参加过的活动
     *
     * @param userId
     * @return
     */
    ActivityListResponse getMyJoinedList(int userId, int page);

    /**
     * 参加活动
     *
     * @param request
     * @return
     */
    Boolean joinActivity(ActivityJoin request);

    /**
     * 查看活动报名表
     *
     * @param activityId
     * @return
     */
    RegistrationResponse getActivityRegistration(int activityId);

}
