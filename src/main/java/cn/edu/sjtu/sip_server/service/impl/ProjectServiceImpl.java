package cn.edu.sjtu.sip_server.service.impl;


import cn.edu.sjtu.sip_server.constant.Const;
import cn.edu.sjtu.sip_server.entity.Project;
import cn.edu.sjtu.sip_server.entity.ProjectComment;
import cn.edu.sjtu.sip_server.mapper.ProjectCommentMapper;
import cn.edu.sjtu.sip_server.mapper.ProjectMapper;

import cn.edu.sjtu.sip_server.request.ProjectListRequest;
import cn.edu.sjtu.sip_server.response.ProjectListResponse;
import cn.edu.sjtu.sip_server.service.ProjectService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectCommentMapper commentMapper;

    @Override
    public ProjectListResponse getProjectList(ProjectListRequest request) {
        ProjectListResponse response = new ProjectListResponse();
        Page<Project> page = new Page<>(request.page, Const.PAGE_SIZE);
//        Wrapper wrapper = Condition.create();
//        Map<String, Object> params = new HashMap<>();
//        if (request.area != null) params.put("area", request.area);
//        if (request.type != null) params.put("type", request.type);
//        if (request.status != null) params.put("status", request.status);
//        if (request.name != null) params.put("name", request.name);
//        wrapper.allEq(true, params);
//        if (request.orderBy != null) wrapper = wrapper.orderBy(true, request.orderBy);
        page.setRecords(projectMapper.findProjectList(page, request.area, request.type, request.status, request.name,
                request.releaseTime, request.pageView, request.registerEnd, request.orderBy));
        response.setContent(page);
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public ProjectComment addComment(ProjectComment projectComment) {
        if (commentMapper.insert(projectComment) > 0) {
            return projectComment;
        }
        return null;
    }

    @Override
    public List<ProjectComment> getComments(int projectId) {
        return commentMapper.selectByProject(projectId);
    }


    @Override
    public void updatePageview(int increment, int id) {
        projectMapper.updatePageview(increment, id);
    }

    @Override
    public void updateProjectStatus() {
        projectMapper.updateOngoing();
        projectMapper.updateFinish();
    }

    @Override
    public List<Project> searchProject(String content) {
        return projectMapper.searchProject(content, content, content,
                content, content, content,
                content, content, content,
                content, content, content);
    }

}