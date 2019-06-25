package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.Activity;
import io.swagger.annotations.Api;

import java.util.List;

@Api
public class ActivityListResponse {
    public long totalPages;
    public boolean isLastPage;
    public List<Activity> content;
}