package cn.edu.sjtu.sip_server.service.impl;


import cn.edu.sjtu.sip_server.entity.Team;
import cn.edu.sjtu.sip_server.mapper.TeamMapper;
import cn.edu.sjtu.sip_server.response.TeamDetailResponse;
import cn.edu.sjtu.sip_server.response.TeamStatusResponse;
import cn.edu.sjtu.sip_server.service.TeamService;
import cn.edu.sjtu.sip_server.vo.UserStatus;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
    @Autowired
    TeamMapper teamMapper;

    @Override
    public TeamDetailResponse selectTeamDetail(int id) {
        List<TeamDetailResponse> teamDetailResponseList = teamMapper.selectTeamDetail(id);
        Map<Integer, Integer> map = new HashMap<>();
        List<TeamDetailResponse> responses = new ArrayList<>();
        for (int i = 0; i < teamDetailResponseList.size(); i++) {
            TeamDetailResponse teamDetailResponse = teamDetailResponseList.get(i);
            int teamId = teamDetailResponse.getTeam().getId();
            if (!map.containsKey(teamId)) {
//                log.info("map put " + teamId + " " + responses.size() + " " + teamDetailResponse.getUserStatusList().get(0).getId());
                map.put(teamId, responses.size());
                responses.add(teamDetailResponse);
            } else {
//                log.info("add userId:" + teamDetailResponse.getUserStatusList().get(0).getId() + " " + teamId + " "
//                        + teamDetailResponseList.get(map.get(teamId)).getTeam().getId());
                List<UserStatus> userStatusList = responses.get(
                        map.get(teamId)).getUserStatusList();
                userStatusList.add(teamDetailResponse.getUserStatusList().get(0));
            }
        }
        if (responses.isEmpty()) {
            return null;
        } else {
            return responses.get(0);
        }
    }

    @Override
    public List<TeamDetailResponse> selectAllTeamDetail() {
        List<TeamDetailResponse> teamDetailResponseList = teamMapper.selectAllTeamDetail();
        Map<Integer, Integer> map = new HashMap<>();
        List<TeamDetailResponse> responses = new ArrayList<>();
        for (int i = 0; i < teamDetailResponseList.size(); i++) {
            TeamDetailResponse teamDetailResponse = teamDetailResponseList.get(i);
            int teamId = teamDetailResponse.getTeam().getId();
//            log.info("teamId:" + teamId + " userId:" + teamDetailResponse.getUserStatusList().get(0).getId());
            if (!map.containsKey(teamId)) {
//                log.info("map put " + teamId + " " + responses.size() + " " + teamDetailResponse.getUserStatusList().get(0).getId());
                map.put(teamId, responses.size());
                responses.add(teamDetailResponse);
            } else {
//                log.info("add userId:" + teamDetailResponse.getUserStatusList().get(0).getId() + " " + teamId + " "
//                        + teamDetailResponseList.get(map.get(teamId)).getTeam().getId());
                List<UserStatus> userStatusList = responses.get(
                        map.get(teamId)).getUserStatusList();
                userStatusList.add(teamDetailResponse.getUserStatusList().get(0));
            }
        }
        return responses;
    }


    @Override
    public List<TeamDetailResponse> selectTeamStatus(int userId) {
        List<TeamDetailResponse> teamDetailResponseList = teamMapper.selectTeamStatus(userId);
        Map<Integer, Integer> map = new HashMap<>();
        List<TeamDetailResponse> responses = new ArrayList<>();
        for (int i = 0; i < teamDetailResponseList.size(); i++) {
            TeamDetailResponse teamDetailResponse = teamDetailResponseList.get(i);
            int teamId = teamDetailResponse.getTeam().getId();
//            log.info("teamId:" + teamId + " userId:" + teamDetailResponse.getUserStatusList().get(0).getId());
            if (!map.containsKey(teamId)) {
//                log.info("map put " + teamId + " " + responses.size() + " " + teamDetailResponse.getUserStatusList().get(0).getId());
                map.put(teamId, responses.size());
                responses.add(teamDetailResponse);
            } else {
//                log.info("add userId:" + teamDetailResponse.getUserStatusList().get(0).getId() + " " + teamId + " "
//                        + teamDetailResponseList.get(map.get(teamId)).getTeam().getId());
                List<UserStatus> userStatusList = responses.get(
                        map.get(teamId)).getUserStatusList();
                userStatusList.add(teamDetailResponse.getUserStatusList().get(0));
            }
        }
        return responses;
    }


    @Override
    public List<TeamStatusResponse> selectCompetitionTeamStatus(int competitoinId, int userId) {
        return teamMapper.selectCompetitionTeamStatus(competitoinId, userId);
    }


}