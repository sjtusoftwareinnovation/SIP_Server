package cn.edu.sjtu.sip_server.service;


import cn.edu.sjtu.sip_server.entity.Activity;
import cn.edu.sjtu.sip_server.entity.ActivityJoin;
import cn.edu.sjtu.sip_server.request.ActivityListRequest;
import cn.edu.sjtu.sip_server.request.ActivityPublishRequest;
import cn.edu.sjtu.sip_server.request.ActivityWeightRequest;
import cn.edu.sjtu.sip_server.response.ActivityListResponse;
import cn.edu.sjtu.sip_server.response.RegistrationResponse;

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
