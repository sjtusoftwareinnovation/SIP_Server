package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.Competition;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;

@Api
public class CompetitionListResponse {
    public long totalPages;
    public boolean isLastPage;
    public Page<Competition> content;
}