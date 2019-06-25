package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.Team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Api
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TeamStatusResponse implements Serializable {
    public Team team;
    @ApiModelProperty("用户在该队伍中的状态-2/removed,-1/rejected,0/apply,1/accepted")
    public int tstatus;
    @ApiModelProperty("用户申请对应队伍的id")
    public int applyId;
}