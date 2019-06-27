package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.TeamUser;
import com.amateur.sip.server.vo.CompetitionUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamUserMapper extends BaseMapper<TeamUser> {
    List<CompetitionUser> selectCompetitionUser(@Param("teamId") int teamId, @Param("userId") int userId,
                                                @Param("status")int status);
}