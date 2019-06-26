package cn.edu.sjtu.sip_server.response;


import cn.edu.sjtu.sip_server.entity.Competition;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;

@Api
public class CompetitionListResponse {
    public long totalPages;
    public boolean isLastPage;
    public Page<Competition> content;
}