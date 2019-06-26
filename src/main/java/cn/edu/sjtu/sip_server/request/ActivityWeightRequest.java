package cn.edu.sjtu.sip_server.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityWeightRequest {
    @ApiModelProperty(value = "是否置顶")
    public boolean isTopping;
    public int activityId;
}
