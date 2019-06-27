package cn.edu.sjtu.sip_server.mapper;

import cn.edu.sjtu.sip_server.entity.Project;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> findProjectList(Page page, @Param("area") String area, @Param("type") String type,
                                  @Param("status") String status, @Param("name") String name, @Param("releaseTime") Timestamp releaseTime,
                                  @Param("pageView") int pageView, @Param("registerEnd") Timestamp registerEnd, @Param("orderBy") String orderBy);

    void updateOngoing();

    void updateFinish();

    void updatePageview(@Param("increment") int increment, @Param("id") int id);

    List<Project> searchProject(@Param("content") String content,
                                @Param("content") String content1,
                                @Param("content") String content2,
                                @Param("content") String content3,
                                @Param("content") String content4,
                                @Param("content") String content5,
                                @Param("content") String content6,
                                @Param("content") String content7,
                                @Param("content") String content8,
                                @Param("content") String content9,
                                @Param("content") String content10,
                                @Param("content") String content11);
}