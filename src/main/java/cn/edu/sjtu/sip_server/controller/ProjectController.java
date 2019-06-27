package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.entity.Project;
import cn.edu.sjtu.sip_server.entity.ProjectComment;
import cn.edu.sjtu.sip_server.interceptor.Access;
import cn.edu.sjtu.sip_server.request.ProjectListRequest;
import cn.edu.sjtu.sip_server.response.ProjectListResponse;
import cn.edu.sjtu.sip_server.service.ProjectService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
@Api("项目管理")
public class ProjectController {
    private final ProjectService projectService;
    private final HttpSession session;

    @Autowired
    public ProjectController(ProjectService projectService, HttpSession session) {
        this.projectService = projectService;
        this.session = session;
    }

    @ApiOperation("获取项目列表")
    @ApiImplicitParam(value = "列表查询请求", name = "request", dataType = "ProjectListRequest")
    @PostMapping("/page")
    public TResult<ProjectListResponse> getList(@RequestBody ProjectListRequest request) {
        log.info("request:" + request);
        ProjectListResponse response = projectService.getProjectList(request);
        TResult<ProjectListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @ApiOperation(value = "获取项目信息")
    @GetMapping("/{id}")
    public TResult<Project> get(@PathVariable("id") @ApiParam(value = "项目Id", required = true) int id) {
        projectService.updatePageview(1, id);
        Project project = projectService.selectById(id);
        TResult<Project> t = new TResult<>();
        if (project != null) {
            t.setSuccess(project);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @PostMapping
    @ApiOperation("添加项目")
    @Access(roles = {1})
    public TResult add(@RequestBody @ApiParam(value = "不带Id项目body", required = true) Project project) {
        boolean result = projectService.insert(project);
        TResult t = new TResult();
        if (result) {
            t.setSuccess(project);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    /**
     * 通过id进行更新
     *
     * @param project
     * @return
     */
    @PutMapping
    @ApiOperation("更新项目")
    @Access(roles = {1})
    public TResult update(@RequestBody @ApiParam(value = "带Id项目body", required = true) Project project) {
        boolean result = projectService.updateById(project);
        TResult t = new TResult();
        if (result) {
            t.setSuccess("updated project:" + project);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除项目")
    @Access(roles = {1})
    public TResult delete(@PathVariable("id") @ApiParam(value = "项目Id", required = true) int id) {
        boolean result = projectService.deleteById(id);
        TResult t = new TResult();
        if (result) {
            t.setSuccess("deleted project:" + id);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @ApiOperation(value = "新增评论")
    @PostMapping("/addComment")
    public TResult<ProjectComment> addComment(@RequestBody ProjectComment comment) {
        TResult<ProjectComment> t = new TResult<>();
        ProjectComment c = projectService.addComment(comment);
        if (c != null) {
            t.setSuccess(c);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "展示评论")
    @GetMapping("/showComments/{id}")
    public TResult showComments(@PathVariable int id) {
        List<ProjectComment> response = projectService.getComments(id);
        TResult<List<ProjectComment>> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "搜索项目")
    @GetMapping("/search")
    public TResult<List<Project>> showComments(@RequestParam @ApiParam("搜索内容") String content) {
        List<Project> projectList = projectService.searchProject(content);
        TResult<List<Project>> t = new TResult<>();
        if (projectList != null) {
            t.setSuccess(projectList);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }
}