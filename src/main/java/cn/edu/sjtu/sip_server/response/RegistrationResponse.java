package cn.edu.sjtu.sip_server.response;


import cn.edu.sjtu.sip_server.entity.User;
import io.swagger.annotations.Api;

import java.util.List;

@Api
public class RegistrationResponse {
    public int activityId;
    public List<User> users;
}
