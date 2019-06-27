package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.entity.BusinessComment;
import cn.edu.sjtu.sip_server.entity.BusinessNeed;
import cn.edu.sjtu.sip_server.interceptor.Access;
import cn.edu.sjtu.sip_server.request.BusinessListRequest;
import cn.edu.sjtu.sip_server.response.BusinessListResponse;
import cn.edu.sjtu.sip_server.service.BusinessService;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/business")
@Slf4j
public class BusinessNeedController {
    @Autowired
    private BusinessService service;
    private final HttpSession session;

    @Autowired
    public BusinessNeedController(HttpSession session) {
        this.session = session;
    }

    @ApiOperation(value = "查看校企项目列表")
    @ApiImplicitParam(name = "request", value = "校企列表请求参数", required = true, dataType = "BusinessListRequest")
    @PostMapping("/page")
    public TResult<BusinessListResponse> getList(@RequestBody BusinessListRequest request) {
        BusinessListResponse response = service.getBusinessList(request);
        TResult<BusinessListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看校企页面")
    @GetMapping("/detail/{id}")
    public TResult<BusinessNeed> getDetail(@PathVariable int id) {
        BusinessNeed response = service.getBusinessDetail(id);
        TResult<BusinessNeed> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看校企评论")
    @GetMapping("/comment/{id}")
    public TResult getComments(@PathVariable int id) {
        List<BusinessComment> response = service.getComments(id);
        TResult<List<BusinessComment>> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "查看我发布的校企活动")
    @GetMapping("/my/{page}")
    public TResult<BusinessListResponse> getMyBusiness(@PathVariable int page) {
        //int userId = (int) session.getAttribute("userId");
        BusinessListResponse response = service.getMyBusiness(1, page);
        TResult<BusinessListResponse> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

    @ApiOperation(value = "新增校企")
    @PostMapping("/add")
    @Access(roles = {1})
    public TResult<BusinessNeed> addBusiness(@RequestBody BusinessNeed businessNeed) {
        TResult<BusinessNeed> t = new TResult<>();
        BusinessNeed c = service.insertBusinessNeed(businessNeed);
        if (c != null) {
            t.setSuccess(c);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "修改校企")
    @PostMapping("/modify")
    @Access(roles = {1})
    public TResult<BusinessNeed> modifyBusiness(@RequestBody BusinessNeed businessNeed) {
        TResult<BusinessNeed> t = new TResult<>();
        BusinessNeed result = service.modifyBusinessNeed(businessNeed);
        if (result != null) {
            t.setSuccess(businessNeed);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "删除校企")
    @DeleteMapping("/delete")
    @Access(roles = {1})
    public TResult deleteBusiness(@RequestParam int id) {
        TResult t = new TResult();
        if (service.deleteBusinessNeed(id)) {
            t.setSuccess(null);
        } else {
            t.setFailure(TResultCode.FAILURE);
        }
        return t;
    }

    @ApiOperation(value = "新增评论")
    @PostMapping("/addComment")
    public TResult<BusinessComment> addComment(@RequestBody BusinessComment comment) {
        TResult<BusinessComment> t = new TResult<>();
        BusinessComment c = service.addComment(comment);
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
        List<BusinessComment> response = service.getComments(id);
        TResult<List<BusinessComment>> t = new TResult<>();
        if (response != null) {
            t.setSuccess(response);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }

}
