package com.amateur.sip.server.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityListRequest {
    @ApiModelProperty(value = "页数")
    public int page;
    @ApiModelProperty(value = "未开始/进行中/已结束")
    public Integer status;
    @ApiModelProperty(value = "排序方式")
    public String orderBy;
    @ApiModelProperty(value = "级别Id")
    public Integer levelId;
    @ApiModelProperty(value = "类型Id")
    public Integer typeId;
    @ApiModelProperty(value = "类别Id")
    public Integer categoryId;
    @ApiModelProperty(value = "活动名称")
    public String name;

    public ActivityListRequest(int page, int status, String orderBy, int levelId, int typeId, int categoryId, String name) {
        this.page = page;
        this.status = status;
        this.orderBy = orderBy;
        this.levelId = levelId;
        this.typeId = typeId;
        this.categoryId = categoryId;
        this.name = name;
    }

    public ActivityListRequest() {

    }
}
