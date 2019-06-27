package cn.edu.sjtu.sip_server.service;

import cn.edu.sjtu.sip_server.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface UserService extends IService<User> {
    void deleteInvalidUser();


}
