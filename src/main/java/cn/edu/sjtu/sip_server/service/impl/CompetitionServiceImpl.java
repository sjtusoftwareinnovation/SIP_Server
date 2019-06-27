package cn.edu.sjtu.sip_server.service.impl;


import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.entity.CompetitionComment;
import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.mapper.CompetitionCommentMapper;
import cn.edu.sjtu.sip_server.mapper.CompetitionMapper;

import cn.edu.sjtu.sip_server.request.CompetitionListRequest;
import cn.edu.sjtu.sip_server.response.CompetitionListResponse;
import cn.edu.sjtu.sip_server.response.CompetitionTeamResponse;
import cn.edu.sjtu.sip_server.service.CompetitionService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {
    private final static int PAGE_SIZE = 20;

    @Autowired
    private CompetitionMapper mapper;

    @Autowired
    private CompetitionCommentMapper commentMapper;

    @Override
    public CompetitionListResponse getCompetitionList(CompetitionListRequest request) {
        CompetitionListResponse response = new CompetitionListResponse();
        Page<Competition> page = new Page<>(request.page, PAGE_SIZE);
        page.setRecords(mapper.findCompetitionList(page, request.host, request.releaseTime, request.status,
                request.type, request.level, request.name, request.orderBy));
        response.content = page;
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public Competition getCompetitionDetail(int id) {
        mapper.updatePageview(1, id);
        return mapper.findDetailById(id);
    }

    @Override
    public CompetitionTeamResponse getCompetitionTeamDetail(int id) {
        List<CompetitionTeamResponse> competitionTeamResponseList = mapper.selectCompetitionTeam(id);
        Map<Integer, Integer> map = new HashMap<>();
        List<CompetitionTeamResponse> responses = new ArrayList<>();
        for (int i = 0; i < competitionTeamResponseList.size(); i++) {
            CompetitionTeamResponse teamDetailResponse = competitionTeamResponseList.get(i);
            int teamId = teamDetailResponse.getCompetition().getId();
//            log.info("teamId:" + teamId + " userId:" + teamDetailResponse.getUserList().get(0).getId());
            if (!map.containsKey(teamId)) {
//                log.info("map put " + teamId + " " + responses.size() + " " + teamDetailResponse.getUserList().get(0).getId());
                map.put(teamId, responses.size());
                responses.add(teamDetailResponse);
            } else {
//                log.info("add userId:" + teamDetailResponse.getUserList().get(0).getId() + " " + teamId + " "
//                        + teamDetailResponseList.get(map.get(teamId)).getTeam().getId());
                List<Team> userList = responses.get(
                        map.get(teamId)).getTeamList();
                userList.add(teamDetailResponse.getTeamList().get(0));
            }
        }
        if (responses.isEmpty()) {
            return null;
        } else {
            return responses.get(0);
        }
    }

    @Override
    public void updateCompetitionStatus() {
        mapper.updateNotBegin();
        mapper.updateOngoing();
    }

    @Override
    public Competition insertCompetition(Competition competition) {
        if (mapper.insert(competition) > 0) {
            return competition;
        }
        return null;
    }

    @Override
    public Competition modifyCompetition(Competition competition) {
        if (updateById(competition)) {
            return selectById(competition.getId());
        } else {
            return null;
        }
    }

    @Override
    public CompetitionListResponse searchCompetition(String name) {
        //TODO
        return null;
    }

    @Override
    public boolean deleteCompetition(int id) {
        Competition c = selectById(id);
        c.setState(0);
        return updateById(c);
    }

    @Override
    public boolean approveCompetition(int id, int isApprove) {
        Competition c = new Competition(id);
        c.setState(isApprove);
        return updateById(c);
    }

    @Override
    public CompetitionComment addComment(CompetitionComment comment) {
        if (commentMapper.insert(comment) > 0) {
            return comment;
        }
        return null;
    }

    @Override
    public List<CompetitionComment> getComments(int competitionId) {
        return commentMapper.selectByCompetition(competitionId);
    }
}
