package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.Team;
import com.amateur.sip.server.response.TeamDetailResponse;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    List<TeamDetailResponse> selectTeamDetail(@Param("teamId") int teamId);

    List<TeamDetailResponse> selectAllTeamDetail();

    List<TeamDetailResponse> selectTeamStatus(@Param("userId") int userId);


    List<TeamStatusResponse> selectCompetitionTeamStatus(@Param("competitionId") int competitionId, @Param("userId") int userId);

}