package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.BusinessComment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface BusinessCommentMapper extends BaseMapper<BusinessComment> {
    List<BusinessComment> selectByBusiness(@Param("businessId") int businessId);
}
