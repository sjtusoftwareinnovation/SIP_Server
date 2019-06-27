package cn.edu.sjtu.sip_server.mapper;


import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.response.CompetitionTeamResponse;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface CompetitionMapper extends BaseMapper<Competition> {
    List<Competition> findCompetitionList(Page page, @Param("host") String host, @Param("releaseTime") String releaseTime, @Param("status") String status,
                                          @Param("type") String type, @Param("level") String level, @Param("name") String name, @Param("orderBy") String orderBy);

    Competition findDetailById(int id);

    void updateNotBegin();

    void updateOngoing();

    void updatePageview(@Param("increment") int increment, @Param("id") int id);

    List<CompetitionTeamResponse> selectCompetitionTeam(@Param("id") int id);
}
