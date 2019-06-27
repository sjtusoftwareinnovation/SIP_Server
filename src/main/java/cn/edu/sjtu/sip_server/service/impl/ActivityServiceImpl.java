package cn.edu.sjtu.sip_server.service.impl;


import cn.edu.sjtu.sip_server.entity.Activity;
import cn.edu.sjtu.sip_server.entity.ActivityJoin;
import cn.edu.sjtu.sip_server.entity.ActivityPublish;
import cn.edu.sjtu.sip_server.entity.User;
import cn.edu.sjtu.sip_server.mapper.ActivityJoinMapper;
import cn.edu.sjtu.sip_server.mapper.ActivityMapper;
import cn.edu.sjtu.sip_server.mapper.ActivityPublishMapper;
import cn.edu.sjtu.sip_server.mapper.UserMapper;

import cn.edu.sjtu.sip_server.request.ActivityListRequest;
import cn.edu.sjtu.sip_server.request.ActivityPublishRequest;
import cn.edu.sjtu.sip_server.request.ActivityWeightRequest;
import cn.edu.sjtu.sip_server.response.ActivityListResponse;
import cn.edu.sjtu.sip_server.response.RegistrationResponse;
import cn.edu.sjtu.sip_server.service.ActivityService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    private final static int PAGE_SIZE = 20;
    private final static int TOPPING_WEIGHT = 100;

    @Autowired
    private ActivityMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityPublishMapper publishMapper;
    @Autowired
    private ActivityJoinMapper joinMapper;

    @Override
    public ActivityListResponse getActivityList(ActivityListRequest request) {
        ActivityListResponse response = new ActivityListResponse();
        Page<Activity> page = new Page<>(request.page, PAGE_SIZE);
        response.content = mapper.findActivityList(page, request.status, request.typeId, request.levelId, request.categoryId, request.name, request.orderBy);
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public Activity getActivityDetail(int id) {
        Activity activity = mapper.selectById(id);
        activity.setPageview(activity.getPageview() + 1);
        mapper.updateById(activity);
        return activity;
    }

    @Override
    public Activity publishActivity(ActivityPublishRequest request) {
        ActivityPublish publisher = request.publisher;
        Activity activity = request.activity;
        User user = userMapper.selectById(publisher.getUserId());
        if (user.getIsValid() > 0) {
            int activityId = mapper.insert(request.activity);
            if (activityId > 0) {
                publisher.setActivityId(activityId);
                publishMapper.insert(publisher);
                activity.setId(activityId);
                return activity;
            }
            return null;
        }
        return null;
    }

    @Override
    public Boolean toppingActivity(ActivityWeightRequest request) {
        Activity activity = mapper.selectById(request.activityId);
        if (request.isTopping) {
            activity.setWeight(TOPPING_WEIGHT);
        } else {
            activity.setWeight(0);
        }
        if (mapper.updateById(activity) > 0) {
            return true;
        }
        return null;
    }

    @Override
    public Activity modifyActivity(Activity request) {
        mapper.updateById(request);
        return request;
    }

    @Override
    public Boolean deleteActivity(int id) {
        Activity activity = mapper.selectById(id);
        if (activity != null) {
            activity.setIsValid(0);
            return mapper.updateById(activity) > 0;
        }
        return false;
    }

    @Override
    public ActivityListResponse getMyPublishedList(int userId, int requestPage) {
        ActivityListResponse response = new ActivityListResponse();
        Page<Activity> page = new Page<>(requestPage, PAGE_SIZE);
        response.content = mapper.findPublished(page, userId);
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public ActivityListResponse getMyJoinedList(int userId, int requestPage) {
        ActivityListResponse response = new ActivityListResponse();
        Page<Activity> page = new Page<>(requestPage, PAGE_SIZE);
        response.content = mapper.findJoined(page, userId);
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public Boolean joinActivity(ActivityJoin request) {
        return joinMapper.insert(request) > 0;
    }

    @Override
    public RegistrationResponse getActivityRegistration(int activityId) {
        RegistrationResponse response = new RegistrationResponse();
        response.activityId = activityId;
        response.users = mapper.findRegistration(activityId);
        return response;
    }
}
