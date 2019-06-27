package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.entity.TeamUser;
import cn.edu.sjtu.sip_server.interceptor.Access;
import cn.edu.sjtu.sip_server.response.TeamDetailResponse;
import cn.edu.sjtu.sip_server.response.TeamStatusResponse;
import cn.edu.sjtu.sip_server.service.CompetitionService;
import cn.edu.sjtu.sip_server.service.TeamService;
import cn.edu.sjtu.sip_server.service.TeamUserService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/team")
@Api("队伍管理控制器")
public class TeamController {
    private final TeamService teamService;
    private final TeamUserService teamUserService;
    private final CompetitionService competitionService;
    private final HttpSession session;

    @Autowired
    public TeamController(TeamService teamService, TeamUserService teamUserService,
                          CompetitionService competitionService, HttpSession session) {
        this.teamService = teamService;
        this.teamUserService = teamUserService;
        this.competitionService = competitionService;
        this.session = session;
    }

    @ApiOperation("获取队伍信息byId")
    @GetMapping("/{id}")
    public TResult<TeamDetailResponse> get(@PathVariable("id") @ApiParam(value = "队伍Id", required = true)
                                                   int id) {
        TeamDetailResponse teamDetailResponse = teamService.selectTeamDetail(id);
        TResult<TeamDetailResponse> t = new TResult<>();
        if (teamDetailResponse != null) {
            t.setSuccess(teamDetailResponse);
        } else {
            Team team = teamService.selectById(id);
            if (team == null) {
                t.setFailure(TResultCode.RESULE_DATA_NONE);
            } else {
                Competition competition = competitionService.selectById(team.getCompetitionId());
                String competitionName = null;
                if (competition != null) {
                    competitionName = competition.getName();
                }
                TeamDetailResponse teamDetailResponse1 = new TeamDetailResponse(team, null, new ArrayList<>());
                t.setSuccess(teamDetailResponse1);
            }
        }
        return t;
    }

    @ApiOperation("获取所有队伍信息")
    @GetMapping("/all")
    public TResult<List<TeamDetailResponse>> getAll() {
        List<TeamDetailResponse> teamDetailResponseList = teamService.selectAllTeamDetail();
        TResult<List<TeamDetailResponse>> t = new TResult<>();
        if (teamDetailResponseList != null) {
            t.setSuccess(teamDetailResponseList);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }


    @GetMapping("/teamStatus/{userId}")
    @ApiOperation("获取所有与用户相关的TeamStatus byUserId")
    public TResult<List<TeamDetailResponse>> getTeamStatus(@PathVariable("userId") @ApiParam("用户Id") int id) {
        List<TeamDetailResponse> teamStatusResponseList = teamService.selectTeamStatus(id);
        TResult<List<TeamDetailResponse>> t = new TResult<>();
        if (teamStatusResponseList != null) {
            t.setSuccess(teamStatusResponseList);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }


    @GetMapping("/competitionTeam")
    @ApiOperation("获取比赛中所在队伍(根据竞赛Id和用户Id)")
    public TResult<List<TeamStatusResponse>> getTeamIdFromCompetition(@RequestParam("competitionId")
                                                                      @ApiParam(value = "比赛Id", required = true) int competitionId,
                                                                      @RequestParam("userId")
                                                                      @ApiParam(value = "用户Id", required = true) int userId) {
        List<TeamStatusResponse> teamStatusResponseList = teamService.selectCompetitionTeamStatus(competitionId, userId);
        TResult<List<TeamStatusResponse>> result = new TResult<>();
        if (teamStatusResponseList == null) {
            result.setFailure(TResultCode.RESULE_DATA_NONE);
        } else {
            result.setSuccess(teamStatusResponseList);
        }
        return result;
    }


//    @ApiOperation("添加队员")
//    @PostMapping("/user")
//    public TResult addUser(@RequestBody @ApiParam(value = "不带Id的TeamUserBody", required = true) TeamUser teamUser) {
//        boolean result = teamUserService.insert(teamUser);
//        TResult tResult = new TResult();
//        if (result) {
//            tResult.setSuccess("team:" + teamUser.getTeamId() + " add user:" + teamUser.getUserId());
//        } else {
//            tResult.setFailure(TResultCode.BUSINESS_ERROR);
//        }
//        return tResult;
//    }

    @ApiOperation("添加队伍")
    @PostMapping
    @Transactional
    public TResult<Team> add(@RequestBody @ApiParam(value = "不带Id的队伍Body(competitionId leader require)", required = true) Team team) {
        TResult<Team> t = new TResult();
        EntityWrapper<Team> entityWrapper = new EntityWrapper<>();
        List<Team> teamList = teamService.selectList(entityWrapper.eq("competitionId", team.getCompetitionId())
                .eq("leader", team.getLeader()));
        if (teamList == null) {
            t.setFailure(TResultCode.BUSINESS_ERROR);
            return t;
        } else {
            if (!teamList.isEmpty()) {
                t.setFailure("该用户在该竞赛下已经创建过队伍");
                return t;
            }
        }
        boolean result = teamService.insert(team);
        if (result) {
            TeamUser teamUser = new TeamUser(team.getId(), team.getLeader(), 1);
            TResult<TeamUser> result1 = teamUserService.insertTeamUser(teamUser);
            if (result1.getCode() == TResultCode.SUCCESS.getCode()) {
                t.setSuccess(team);
            } else {
                t.setFailure("add team->leader's team_user failed");
            }
            log.info("added team.id:" + team.getId());
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    /**
     * 通过id进行更新
     *
     * @param team
     * @return
     */
    @PutMapping
    @ApiOperation("更新队伍信息")
    public TResult update(@RequestBody @ApiParam(value = "带Id的队伍Body", required = true) Team team) {
        boolean result = teamService.updateById(team);
        TResult t = new TResult();
        if (result) {
            t.setSuccess("updated team:" + team);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除队伍ById")
    @Access(roles = {1})
    public TResult delete(@PathVariable("id") @ApiParam(value = "不带Id的队伍Body", required = true) int id) {
        EntityWrapper<Team> entityWrapper = new EntityWrapper<>();
        boolean result = teamService.updateForSet(" status=" + 0, entityWrapper.eq("id", id));
        TResult t = new TResult();
        if (result) {
//            EntityWrapper<TeamUser> ew = new EntityWrapper<>();
//            boolean r2 = teamUserService.updateForSet("status=-2", ew.eq("teamId", id));
//            if (r2) {
            t.setSuccess("deleted team success:" + id);
//            } else {
//                t.setFailure(TResultCode.BUSINESS_ERROR);
//            }
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

}