package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.TeamUser;
import com.amateur.sip.server.util.TResult;
import com.amateur.sip.server.vo.CompetitionUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface TeamUserService extends IService<TeamUser> {
    List<CompetitionUser> selectCompetionUser(int teamId, int userId, int status);

    TResult<TeamUser> insertTeamUser(TeamUser teamUser);
}