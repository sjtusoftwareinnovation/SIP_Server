package cn.edu.sjtu.sip_server.service.impl;

import cn.edu.sjtu.sip_server.entity.User;
import cn.edu.sjtu.sip_server.mapper.UserMapper;
import cn.edu.sjtu.sip_server.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
