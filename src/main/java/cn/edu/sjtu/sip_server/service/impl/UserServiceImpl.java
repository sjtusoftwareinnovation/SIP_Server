package com.amateur.sip.server.service.impl;

import com.amateur.sip.server.entity.User;
import com.amateur.sip.server.mapper.UserMapper;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.amateur.sip.server.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void deleteInvalidUser() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        userMapper.delete(entityWrapper.eq("isValid", 0));
    }

}
