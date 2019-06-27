package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.entity.CompetitionComment;
import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.interceptor.Access;
import cn.edu.sjtu.sip_server.request.CompetitionListRequest;
import cn.edu.sjtu.sip_server.response.CompetitionListResponse;
import cn.edu.sjtu.sip_server.response.CompetitionTeamResponse;
import cn.edu.sjtu.sip_server.service.CompetitionService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/competition")
@Slf4j
public class CompetitionController {
    @Autowired
    private CompetitionService service;

    @ApiOperation(value = "查看竞赛列表")
    @ApiImplicitParam(name = "request", value = "竞赛列表请求参数", required = true, dataType = "CompetitionListRequest")
    @PostMapping("/page")
    public TResult<CompetitionListResponse> getList(@RequestBody CompetitionListRequest request) {
        CompetitionListResponse response = service.getCompetitionList(request);
        TResult<CompetitionListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看竞赛详情")
    @GetMapping("/detail/{id}")
    public TResult<Competition> getDetail(@PathVariable int id) {
        Competition response = service.getCompetitionDetail(id);
        TResult<Competition> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看竞赛详情(带Team)")
    @GetMapping("/detailTeam/{id}")
    public TResult<CompetitionTeamResponse> getDetailWithTeam(@PathVariable int id) {
        CompetitionTeamResponse response = service.getCompetitionTeamDetail(id);
        TResult<CompetitionTeamResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            Competition response2 = service.getCompetitionDetail(id);
            if (response2 != null) {
                CompetitionTeamResponse competitionTeamResponse = new CompetitionTeamResponse();
                competitionTeamResponse.setCompetition(response2);
                List<Team> teamList = new ArrayList<>();
                competitionTeamResponse.setTeamList(teamList);
                t.setSuccess(competitionTeamResponse);
            } else {
                t.setFailure(TResultCode.RESULE_DATA_NONE);
            }
        }
        return t;
    }

    @ApiOperation(value = "新增竞赛")
    @PostMapping("/add")
    @Access(roles = {1})
    public TResult<Competition> addCompetition(@RequestBody Competition competition) {
        TResult<Competition> t = new TResult<>();
        Competition c = service.insertCompetition(competition);
        if (c != null) {
            t.setSuccess(c);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "修改竞赛")
    @PostMapping("/modify")
    @Access(roles = {1})
    public TResult<Competition> modifyCompetition(@RequestBody Competition competition) {
        TResult<Competition> t = new TResult<>();
        Competition c = service.modifyCompetition(competition);
        if (c != null) {
            t.setSuccess(c);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "删除竞赛")
    @DeleteMapping("/delete")
    @Access(roles = {1})
    public TResult deleteCompetition(@RequestParam int id) {
        TResult t = new TResult();
        if (service.deleteCompetition(id)) {
            t.setSuccess(null);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "审批竞赛")
    @GetMapping("/approval")
    @Access(roles = {1})
    public TResult approveCompetition(@RequestParam int id, @RequestParam int isApprove) {
        TResult t = new TResult();
        if (service.approveCompetition(id, isApprove)) {
            t.setSuccess(null);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "新增评论")
    @PostMapping("/addComment")
    public TResult<CompetitionComment> addComment(@RequestBody CompetitionComment comment) {
        TResult<CompetitionComment> t = new TResult<>();
        CompetitionComment c = service.addComment(comment);
        if (c != null) {
            t.setSuccess(c);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "展示评论")
    @GetMapping("/showComments/{id}")
    public TResult showComments(@PathVariable int id) {
        List<CompetitionComment> response = service.getComments(id);
        TResult<List<CompetitionComment>> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }
}
