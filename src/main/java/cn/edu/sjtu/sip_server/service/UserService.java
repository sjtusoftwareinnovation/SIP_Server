package com.amateur.sip.server.service;

import com.amateur.sip.server.entity.User;
import com.amateur.sip.server.response.TeamStatusResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface UserService extends IService<User> {
    void deleteInvalidUser();


}
