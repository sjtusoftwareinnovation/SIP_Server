package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.BusinessComment;
import com.amateur.sip.server.entity.BusinessNeed;
import com.amateur.sip.server.request.BusinessListRequest;
import com.amateur.sip.server.response.BusinessListResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface BusinessService extends IService<BusinessNeed> {
    /**
     * 获取校企列表
     *
     * @param request
     * @return
     */
    BusinessListResponse getBusinessList(BusinessListRequest request);

    /**
     * 获取校企详情
     *
     * @param id
     * @return
     */
    BusinessNeed getBusinessDetail(int id);

    /**
     * 查看该用户发布的校企
     *
     * @param userId
     * @return
     */
    BusinessListResponse getMyBusiness(int userId, int page);

    /**
     * 每天更新校企项目状态
     */
    void updateBusinessStatus();

    /**
     * 新增校企
     *
     * @param businessNeed
     * @return
     */
    BusinessNeed insertBusinessNeed(BusinessNeed businessNeed);

    /**
     * 修改校企
     *
     * @param businessNeed
     * @return
     */
    BusinessNeed modifyBusinessNeed(BusinessNeed businessNeed);

    /**
     * 删除校企
     *
     * @param id
     * @return
     */
    boolean deleteBusinessNeed(int id);

    /**
     * 添加评论
     *
     * @param businessComment
     * @return
     */
    BusinessComment addComment(BusinessComment businessComment);

    /**
     * 获得所有评论
     *
     * @param businessId
     * @return
     */
    List<BusinessComment> getComments(int businessId);
}
