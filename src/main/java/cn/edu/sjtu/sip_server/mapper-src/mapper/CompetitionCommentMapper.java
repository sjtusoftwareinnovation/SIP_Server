package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.CompetitionComment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CompetitionCommentMapper extends BaseMapper<CompetitionComment> {
    List<CompetitionComment> selectByCompetition(@Param("competitionId") int competitionId);
}
