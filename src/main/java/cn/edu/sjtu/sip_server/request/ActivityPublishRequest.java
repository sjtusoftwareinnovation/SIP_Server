package com.amateur.sip.server.request;

import com.amateur.sip.server.entity.Activity;
import com.amateur.sip.server.entity.ActivityPublish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityPublishRequest {
    @ApiModelProperty(value = "活动本身相关信息")
    public Activity activity;
    @ApiModelProperty(value = "活动发布者信息")
    public ActivityPublish publisher;
}
