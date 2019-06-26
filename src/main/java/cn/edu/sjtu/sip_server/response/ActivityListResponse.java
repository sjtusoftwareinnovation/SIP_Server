package cn.edu.sjtu.sip_server.response;


import cn.edu.sjtu.sip_server.entity.Activity;
import io.swagger.annotations.Api;

import java.util.List;

@Api
public class ActivityListResponse {
    public long totalPages;
    public boolean isLastPage;
    public List<Activity> content;
}