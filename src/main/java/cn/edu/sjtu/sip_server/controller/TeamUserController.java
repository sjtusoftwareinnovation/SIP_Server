package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.constant.Const;
import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.entity.TeamUser;
import cn.edu.sjtu.sip_server.entity.User;
import cn.edu.sjtu.sip_server.service.TeamService;
import cn.edu.sjtu.sip_server.service.TeamUserService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import cn.edu.sjtu.sip_server.vo.CompetitionUser;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teamUser")
@Api("申请队伍管理")
public class TeamUserController {
    private final TeamUserService teamUserService;
    private final TeamService teamService;
    private final HttpSession session;

    @Autowired
    public TeamUserController(TeamUserService teamUserService, TeamService teamService, HttpSession session) {
        this.teamUserService = teamUserService;
        this.teamService = teamService;
        this.session = session;
    }

    @ApiOperation("获取队伍申请信息byTeamId(competitionId required)")
    @GetMapping("/{teamId}")
    public TResult<List<TeamUser>> get(@PathVariable("teamId")
                                       @ApiParam(value = "teamId", required = true) int teamId) {
        EntityWrapper<TeamUser> ew = new EntityWrapper<>();
        List<TeamUser> teamUserList = teamUserService.selectList(ew.eq("teamId", teamId).ge("status", 0));
        TResult<List<TeamUser>> result = new TResult<>();
        if (teamUserList == null) {
            result.setFailure(TResultCode.BUSINESS_ERROR);
        } else if (teamUserList.isEmpty()) {
            result.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        result.setSuccess(teamUserList);
        return result;
    }

    @ApiOperation("添加申请信息")
    @PostMapping
    @Transactional
    public TResult<TeamUser> add(@RequestBody @ApiParam(value = "不带Id的申请", required = true) TeamUser teamUser) {
        return teamUserService.insertTeamUser(teamUser);
    }

    /**
     * 通过id进行更新
     *
     * @param teamUser
     * @return
     */
    @PutMapping
    @ApiOperation("更新申请信息")
    public TResult update(@RequestBody @ApiParam(value = "带Id的TeamUserBody", required = true) TeamUser teamUser) {
        TResult t = new TResult();
        if (teamUser.getStatus() == 1) {
            List<CompetitionUser> competitionUserList = teamUserService.selectCompetionUser(teamUser.getTeamId(),
                    teamUser.getUserId(), teamUser.getStatus());
            if (competitionUserList == null) {
                t.setFailure(TResultCode.BUSINESS_ERROR);
                return t;
            } else {
                if (!competitionUserList.isEmpty()) {
                    t.setFailure("该用户已参加该竞赛中其他队伍了");
                    return t;
                }
            }
        }
        boolean result = teamUserService.updateById(teamUser);
        if (result) {
            t.setSuccess("updated team:" + teamUser);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @PutMapping("/deleteMember")
    @ApiOperation("移除已经在队伍中的一个队员")
    public TResult deleteMember(@RequestParam("teamId")
                                @ApiParam(value = "队伍Id", required = true) int teamId,
                                @RequestParam("userId")
                                @ApiParam(value = "队员Id", required = true) int userId) {
        TResult t = new TResult();
        Team team = teamService.selectById(teamId);
        if (team == null) {
            t.setFailure(TResultCode.BUSINESS_ERROR);
            return t;
        }
        User leader = (User) session.getAttribute(Const.USER_KEY);
//        管理员和队长均有权限移除一名队员
        if (leader == null) {
            t.setFailure(TResultCode.USER_NOT_LOGGED_IN);
            return t;
        }
        if ((!(leader.getRole() >= 1) || team.getLeader() == leader.getId())) {
            t.setFailure(TResultCode.PERMISSION_NO_ACCESS);
            return t;
        }
        if (userId == leader.getId()) {
            t.setFailure("队长:" + userId + ",不能被移除队伍！");
            return t;
        }
        EntityWrapper<TeamUser> entityWrapper = new EntityWrapper<>();
        boolean result = teamUserService.updateForSet("status=-2", entityWrapper.eq("teamId", teamId).eq("userId", userId).eq("status", 1));
        if (result == false) {
            t.setFailure(TResultCode.BUSINESS_ERROR);
            return t;
        }
        t.setSuccess("队员：" + userId + "已被移除队伍:" + teamId);
        return t;
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除申请信息ById(真删除，删除库里数据，用于取消申请)")
//    @Access(roles = {1})
    public TResult cancel(@PathVariable("id") @ApiParam(value = "id", required = true) int id) {
        TResult t = new TResult();
        log.info("删除申请数据方法（当且申请处于未审批状态）");
        TeamUser teamUser = teamUserService.selectById(id);
        if (teamUser == null) {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
            return t;
        }
        if (teamUser.getStatus() != 0) {
            t.setFailure("只有处于未审批的记录才能被删除");
            return t;
        }
        User user = (User) session.getAttribute(Const.USER_KEY);
        if (user == null) {
            t.setFailure("用户未登录");
            return t;
        }
        if (user.getId() != teamUser.getUserId()) {
            t.setFailure("用户只能删除自身发起的申请");
            return t;
        }
        boolean result = teamUserService.deleteById(id);
        if (result) {
            t.setSuccess("申请已取消，用户Id:" + id);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
            return t;
        }
        return t;
    }


}