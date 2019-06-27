package cn.edu.sjtu.sip_server.service;

import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.response.TeamDetailResponse;
import cn.edu.sjtu.sip_server.response.TeamStatusResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface TeamService extends IService<Team> {
    TeamDetailResponse selectTeamDetail(int id);

    List<TeamDetailResponse> selectAllTeamDetail();

    List<TeamDetailResponse> selectTeamStatus(int id);

    List<TeamStatusResponse> selectCompetitionTeamStatus(int competitoinId, int userId);

}