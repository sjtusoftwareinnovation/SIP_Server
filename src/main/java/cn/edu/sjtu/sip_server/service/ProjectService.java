package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.Project;
import com.amateur.sip.server.entity.ProjectComment;
import com.amateur.sip.server.request.ProjectListRequest;
import com.amateur.sip.server.response.ProjectListResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ProjectService extends IService<Project> {
    /**
     * 获取project的List
     *
     * @param request
     * @return
     */
    ProjectListResponse getProjectList(ProjectListRequest request);

    /**
     * 添加评论
     *
     * @param projectComment
     * @return
     */
    ProjectComment addComment(ProjectComment projectComment);

    /**
     * 获得所有评论
     *
     * @param projectId
     * @return
     */
    List<ProjectComment> getComments(int projectId);


    void updatePageview(int increment, int id);

    void updateProjectStatus();

    List<Project> searchProject(String content);

}