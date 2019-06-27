package cn.edu.sjtu.sip_server.service;

import cn.edu.sjtu.sip_server.entity.TeamUser;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.vo.CompetitionUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface TeamUserService extends IService<TeamUser> {
    List<CompetitionUser> selectCompetionUser(int teamId, int userId, int status);

    TResult<TeamUser> insertTeamUser(TeamUser teamUser);
}