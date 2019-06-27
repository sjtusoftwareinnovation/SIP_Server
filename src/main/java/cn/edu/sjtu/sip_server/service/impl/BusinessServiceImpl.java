package cn.edu.sjtu.sip_server.service.impl;


import cn.edu.sjtu.sip_server.entity.BusinessComment;
import cn.edu.sjtu.sip_server.entity.BusinessNeed;
import cn.edu.sjtu.sip_server.mapper.BusinessCommentMapper;
import cn.edu.sjtu.sip_server.mapper.BusinessNeedMapper;

import cn.edu.sjtu.sip_server.request.BusinessListRequest;
import cn.edu.sjtu.sip_server.response.BusinessListResponse;
import cn.edu.sjtu.sip_server.service.BusinessService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessNeedMapper, BusinessNeed> implements BusinessService {
    @Autowired
    private BusinessNeedMapper mapper;
    @Autowired
    private BusinessCommentMapper commentMapper;
    private final static int PAGE_SIZE = 20;

    @Override
    public BusinessListResponse getBusinessList(BusinessListRequest request) {
        BusinessListResponse response = new BusinessListResponse();
        Page<BusinessNeed> page = new Page<>(request.page, PAGE_SIZE);
        Wrapper wrapper = Condition.create();
        Map<String, Object> params = new HashMap<>();
        if (request.category != null) params.put("category", request.category);
        if (request.source != null) params.put("source", request.source);
        if (request.studentType != null) params.put("studentType", request.studentType);
        if (request.status != null) params.put("status", request.status);
        if (request.type != null) params.put("type", request.type);
        params.put("state", 1);
        wrapper.allEq(true, params);
        if (request.name != null) wrapper.like("name", "%" + request.name + "%");
        if (request.orderBy != null) wrapper.orderBy(true, request.orderBy);
        page.setRecords(mapper.findBusinessList(page, wrapper));
        response.content = page;
        response.isLastPage = !page.hasNext();
        response.totalPages = page.getPages();
        return response;
    }

    @Override
    public BusinessNeed getBusinessDetail(int id) {
        mapper.updatePageview(1, id);
        return mapper.findDetailById(id);
    }

    @Override
    public BusinessListResponse getMyBusiness(int userId, int page) {
        BusinessListResponse response = new BusinessListResponse();
        Page<BusinessNeed> pages = new Page<>(page, PAGE_SIZE);
        Wrapper wrapper = Condition.create();
        wrapper.eq("releaseUser", userId);
        pages.setRecords(mapper.findBusinessList(pages, wrapper));
        response.content = pages;
        response.isLastPage = !pages.hasNext();
        response.totalPages = pages.getPages();
        return response;
    }

    @Override
    public void updateBusinessStatus() {
        mapper.updateNotBegin();
        mapper.updateOngoing();
    }

    @Override
    public BusinessNeed insertBusinessNeed(BusinessNeed businessNeed) {
        if (mapper.insert(businessNeed) > 0) {
            return businessNeed;
        }
        return null;
    }

    @Override
    public BusinessNeed modifyBusinessNeed(BusinessNeed businessNeed) {
        if (updateById(businessNeed)) {
            return selectById(businessNeed.getId());
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteBusinessNeed(int id) {
        BusinessNeed bn = selectById(id);
        bn.setState(0);
        return updateById(bn);
    }

    @Override
    public BusinessComment addComment(BusinessComment businessComment) {
        if (commentMapper.insert(businessComment) > 0) {
            return businessComment;
        }
        return null;
    }

    @Override
    public List<BusinessComment> getComments(int businessId) {
        return commentMapper.selectByBusiness(businessId);
    }
}
