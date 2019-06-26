package cn.edu.sjtu.sip_server.response;


import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.vo.UserStatus;
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