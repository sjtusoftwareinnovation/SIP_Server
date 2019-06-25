package com.amateur.sip.server.response;


import com.amateur.sip.server.entity.Project;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Api
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListResponse implements Serializable {
    public long totalPages;
    public boolean isLastPage;
    public Page<Project> content;
}