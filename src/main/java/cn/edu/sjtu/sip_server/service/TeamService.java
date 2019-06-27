package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.Team;
import com.amateur.sip.server.response.TeamDetailResponse;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface TeamService extends IService<Team> {
    TeamDetailResponse selectTeamDetail(int id);

    List<TeamDetailResponse> selectAllTeamDetail();

    List<TeamDetailResponse> selectTeamStatus(int id);

    List<TeamStatusResponse> selectCompetitionTeamStatus(int competitoinId, int userId);

}