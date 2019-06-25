package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.Team;
import com.amateur.sip.server.vo.UserStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Api
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TeamDetailResponse implements Serializable {
    public Team team;
    @ApiModelProperty("队伍所对应的竞赛名称")
    public String competitionName;
    @ApiModelProperty("在队伍中状态的用户列表")
    public List<UserStatus> userStatusList;
}