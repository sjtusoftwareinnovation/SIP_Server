package cn.edu.sjtu.sip_server.response;


import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.entity.Team;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Api
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionTeamResponse implements Serializable {
    public Competition competition;
    public List<Team> teamList;
}