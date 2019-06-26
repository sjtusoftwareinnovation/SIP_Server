package cn.edu.sjtu.sip_server.request;


import cn.edu.sjtu.sip_server.entity.Activity;
import cn.edu.sjtu.sip_server.entity.ActivityPublish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityPublishRequest {
    @ApiModelProperty(value = "活动本身相关信息")
    public Activity activity;
    @ApiModelProperty(value = "活动发布者信息")
    public ActivityPublish publisher;
}
