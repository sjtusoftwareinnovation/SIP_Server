package com.amateur.sip.server.mapper;

import com.amateur.sip.server.entity.User;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
