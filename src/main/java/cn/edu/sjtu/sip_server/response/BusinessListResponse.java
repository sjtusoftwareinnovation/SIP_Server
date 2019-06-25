package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.BusinessNeed;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;

@Api
public class BusinessListResponse {
    public long totalPages;
    public boolean isLastPage;
    public Page<BusinessNeed> content;
}