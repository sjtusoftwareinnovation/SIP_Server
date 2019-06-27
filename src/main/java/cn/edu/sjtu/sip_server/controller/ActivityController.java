package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.entity.Activity;
import cn.edu.sjtu.sip_server.entity.ActivityJoin;
import cn.edu.sjtu.sip_server.request.ActivityListRequest;
import cn.edu.sjtu.sip_server.request.ActivityPublishRequest;
import cn.edu.sjtu.sip_server.request.ActivityWeightRequest;
import cn.edu.sjtu.sip_server.response.ActivityListResponse;
import cn.edu.sjtu.sip_server.response.RegistrationResponse;
import cn.edu.sjtu.sip_server.service.ActivityService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService service;

    @ApiOperation(value = "查看活动列表")
    @ApiImplicitParam(name = "request", value = "活动列表请求参数", required = true, dataType = "ActivityListRequest")
    @PostMapping("/page")
    public TResult<ActivityListResponse> getList(@RequestBody ActivityListRequest request) {
        ActivityListResponse response = service.getActivityList(request);
        TResult<ActivityListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看活动详情")
    @GetMapping("/detail/{id}")
    public TResult<Activity> getDetail(@PathVariable int id) {
        Activity response = service.getActivityDetail(id);
        TResult<Activity> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "发布活动")
    @ApiImplicitParam(name = "request", value = "发布活动请求参数", required = true, dataType = "ActivityPublishRequest")
    @PostMapping("/add")
    public TResult<Activity> publishActivity(@RequestBody ActivityPublishRequest request) {
        Activity response = service.publishActivity(request);
        TResult<Activity> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.PERMISSION_NO_ACCESS);
        }
        return t;
    }

    @ApiOperation(value = "活动置顶/取消活动置顶")
    @ApiImplicitParam(name = "request", value = "活动置顶参数", required = true, dataType = "ActivityWeightRequest")
    @PostMapping("/weight")
    public TResult<Boolean> publishActivity(@RequestBody ActivityWeightRequest request) {
        Boolean response = service.toppingActivity(request);
        TResult<Boolean> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "修改活动")
    @ApiImplicitParam(name = "request", value = "活动参数", required = true, dataType = "Activity")
    @PostMapping("/modify")
    public TResult<Activity> publishActivity(@RequestBody Activity request) {
        Activity response = service.modifyActivity(request);
        TResult<Activity> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "删除活动")
    @DeleteMapping("/delete")
    public TResult<Boolean> deleteActivity(@RequestParam int id) {
        Boolean response = service.deleteActivity(id);
        TResult<Boolean> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看我发布的活动")
    @GetMapping("/published")
    public TResult<ActivityListResponse> findMyPublishedActivity(@RequestParam int userId, @RequestParam int page) {
        ActivityListResponse response = service.getMyPublishedList(userId,page);
        TResult<ActivityListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看我参加的活动")
    @GetMapping("/joined")
    public TResult<ActivityListResponse> findMyJoinedActivity(@RequestParam int userId, @RequestParam int page) {
        ActivityListResponse response = service.getMyJoinedList(userId,page);
        TResult<ActivityListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "参加活动")
    @ApiImplicitParam(name = "request", value = "参加活动请求参数", required = true, dataType = "ActivityJoinRequest")
    @PostMapping("/join")
    public TResult<Boolean> publishActivity(@RequestBody ActivityJoin request) {
        Boolean response = service.joinActivity(request);
        TResult<Boolean> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看活动报名表")
    @GetMapping("/registration/{activityId}")
    public TResult<RegistrationResponse> getActivityRegistration(@PathVariable int activityId) {
        RegistrationResponse response = service.getActivityRegistration(activityId);
        TResult<RegistrationResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }
}
