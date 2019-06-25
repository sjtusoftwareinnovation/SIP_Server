package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.User;
import io.swagger.annotations.Api;

import java.util.List;

@Api
public class RegistrationResponse {
    public int activityId;
    public List<User> users;
}
