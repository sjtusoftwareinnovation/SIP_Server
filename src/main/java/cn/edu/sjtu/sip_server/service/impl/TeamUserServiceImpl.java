package com.amateur.sip.server.service.impl;

import com.amateur.sip.server.entity.Team;
import com.amateur.sip.server.entity.TeamUser;
import com.amateur.sip.server.mapper.TeamMapper;
import com.amateur.sip.server.mapper.TeamUserMapper;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.amateur.sip.server.service.TeamUserService;
import com.amateur.sip.server.util.TResult;
import com.amateur.sip.server.util.TResultCode;
import com.amateur.sip.server.util.TeamUtil;
import com.amateur.sip.server.vo.CompetitionUser;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamUserServiceImpl extends ServiceImpl<TeamUserMapper, TeamUser> implements TeamUserService {
    @Autowired
    private TeamUserMapper teamUserMapper;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<CompetitionUser> selectCompetionUser(int teamId, int userId, int status) {
        return teamUserMapper.selectCompetitionUser(teamId, userId, status);
    }

    @Override
    public TResult<TeamUser> insertTeamUser(TeamUser teamUser) {
        TResult<TeamUser> tr = new TResult<>();
        if (teamUser == null) {
            tr.setFailure(TResultCode.DATA_IS_WRONG);
            return tr;
        }
        Team team = teamMapper.selectById(teamUser.getTeamId());
        if (team == null) {
            tr.setFailure("申请的队伍不存在");
            return tr;
        }
        if (team.getStatus() == 0) {
            tr.setFailure("您所申请的队伍已处于失效状态");
            return tr;
        }
        EntityWrapper<TeamUser> entityWrapper = new EntityWrapper<>();
        List<TeamStatusResponse> teamStatusResponseList = teamMapper.selectCompetitionTeamStatus(team.getCompetitionId(), teamUser.getUserId());
        if (TeamUtil.atLeastOne(teamStatusResponseList, 1) == true) {
            tr.setFailure("您已经在队伍所处的竞赛中参加了其他的队伍");
            return tr;
        }
        List<TeamUser> teamUserList = teamUserMapper.selectList(entityWrapper.eq("teamId", teamUser.getTeamId())
                .eq("userId", teamUser.getUserId())
//                ge表示大于等于
                .ge("status", 0));
        if (teamUserList == null) {
            tr.setFailure(TResultCode.BUSINESS_ERROR);
            return tr;
        } else {
            if (!teamUserList.isEmpty()) {
                log.info(+teamUserList.size() + " 获得的teamUserList:" + teamUserList);
                tr.setFailure("该用户已经在队伍中或还有申请该队伍未处理");
                return tr;
            }
        }
//        返回成功的条数
        int result = teamUserMapper.insert(teamUser);
        if (result > 0) {
            tr.setSuccess(teamUser);
        } else {
            tr.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return tr;
    }
}