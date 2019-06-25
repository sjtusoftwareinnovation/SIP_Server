package com.amateur.sip.server.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ---筛选条件---
 * 项目类型：type
 * 来源范围：source
 * 组队地区：area
 * 项目状态：status（暂未报名/正在报名/进行中/已结束）
 * <p>
 * ---排序方式---
 * 发布时间：releaseTime
 * 浏览量：pageview
 * 报名截止日期：registerEnd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ProjectListRequest implements Serializable {
    @ApiModelProperty("页数")
    public int page;
    @ApiModelProperty("来源范围")
    public String area;
    @ApiModelProperty("项目类型")
    public String type;
    /**
     * 竞赛状态
     */
    @ApiModelProperty("竞赛状态")
    public String status;
    @ApiModelProperty("项目名称")
    public String name;
    @ApiModelProperty("发布时间")
    public Timestamp releaseTime;
    @ApiModelProperty("浏览量")
    public int pageView;
    @ApiModelProperty("注册截止时间")
    public Timestamp registerEnd;
    @ApiModelProperty("排序方式")
    public String orderBy;

}