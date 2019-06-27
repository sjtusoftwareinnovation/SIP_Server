package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.ProjectComment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface ProjectCommentMapper extends BaseMapper<ProjectComment> {
    List<ProjectComment> selectByProject(@Param("projectId") int projectId);
}
