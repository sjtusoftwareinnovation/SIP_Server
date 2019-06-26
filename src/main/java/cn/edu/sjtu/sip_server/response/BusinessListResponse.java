package cn.edu.sjtu.sip_server.response;

import cn.edu.sjtu.sip_server.entity.BusinessNeed;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;

@Api
public class BusinessListResponse {
    public long totalPages;
    public boolean isLastPage;
    public Page<BusinessNeed> content;
}