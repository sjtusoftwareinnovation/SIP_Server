package cn.edu.sjtu.sip_server.controller;

import cn.edu.sjtu.sip_server.constant.Const;
import cn.edu.sjtu.sip_server.entity.User;
import cn.edu.sjtu.sip_server.interceptor.Access;
import cn.edu.sjtu.sip_server.request.UserLoginRequest;
import cn.edu.sjtu.sip_server.service.UserService;
import cn.edu.sjtu.sip_server.util.EmailUtil;
import cn.edu.sjtu.sip_server.util.Enycryption;
import cn.edu.sjtu.sip_server.util.TResult;
import cn.edu.sjtu.sip_server.util.TResultCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/user")
@Api("用户管理")
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户byId")
    public TResult<User> get(@PathVariable("id") @ApiParam("用户Id") String id) {
        User user = userService.selectById(id);
        TResult<User> t = new TResult<>();
        if (user != null) {
            t.setSuccess(user);
        } else {
            t.setFailure(TResultCode.RESULE_DATA_NONE);
        }
        return t;
    }


    @PostMapping
    @ApiOperation("添加用户")
    public TResult add(@RequestBody @ApiParam("不带Id的用户Body(email username password qq)") User user) {
        log.info("usercontroller:" + user);
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        List<User> userList = userService.selectList(entityWrapper.eq("email", user.getEmail()));
        TResult t = new TResult();
        String email = user.getEmail();
        if (!EmailUtil.isEmail(email)) {
            t.setFailure("invalid email");
            return t;
        }
        if (userList == null) {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        } else if (userList.size() > 0) {
            t.setFailure(user.getEmail() + "已经注册过，请使用邮箱密码登录");
        } else {
            boolean result = userService.insert(user);
            if (result) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", user.getEmail());
                log.debug("jsonObject:" + jsonObject.toString());
                boolean sResult = EmailUtil.sendAdd(user.getEmail(), jsonObject.toString());
                if (sResult) {
                    t.setSuccess("注册邮件已发送到" + user.getEmail());
                } else {
                    t.setFailure("注册邮箱发送到" + user.getEmail() + "失败");
                }
            } else {
                t.setFailure(TResultCode.BUSINESS_ERROR);
            }
        }
        return t;
    }

    @PostMapping("/login")
    @ApiOperation("用户邮箱登录")
    public TResult<User> login(@RequestBody @ApiParam("仅含email和password的request") UserLoginRequest request) {
        String email = request.getEmail();
        TResult result = new TResult();
        if (!EmailUtil.isEmail(email)) {
            result.setFailure("invalid email");
            return result;
        }
        String password = request.getPassword();
        if (email == null || email.isEmpty()) {
            result.setFailure(TResultCode.DATA_IS_WRONG);
            return result;
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        User user = userService.selectOne(ew.eq("email", email));
        if (user == null) {
            result.setFailure(TResultCode.BUSINESS_ERROR);
            return result;
        } else {
            if (user.getRole() == 0) {
                if (user.getPassword().equals(password)) {
                    user.setPassword("***");
                    result.setSuccess(user);
                    session.setAttribute(Const.USER_KEY, user);
                } else {
                    result.setFailure(TResultCode.DATA_IS_WRONG);
                    return result;
                }
            } else {
                result.setFailure(TResultCode.PERMISSION_NO_ACCESS);
                return result;
            }
        }
        return result;
    }

    @GetMapping("/logout")
    @ApiOperation("普通用户登出")
    public TResult logout() {
        TResult result = new TResult();
        session.setAttribute(Const.USER_KEY, null);
        result.setSuccess("用户登出成功");
        return result;
    }


    @PostMapping("/loginForAdmin")
    @ApiOperation("管理员邮箱登录")
    public TResult<User> loginForAdmin(@RequestBody @ApiParam("仅含email和password的request") UserLoginRequest request) {
        String email = request.getEmail();
        TResult result = new TResult();
        if (!EmailUtil.isEmail(email)) {
            result.setFailure("invalid email");
            return result;
        }
        String password = request.getPassword();
        if (email == null || email.isEmpty()) {
            result.setFailure(TResultCode.DATA_IS_WRONG);
            return result;
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        User user = userService.selectOne(ew.eq("email", email));
        if (user == null) {
            result.setFailure(TResultCode.BUSINESS_ERROR);
            return result;
        } else {
            if (user.getRole() > 0) {
                if (user.getPassword().equals(password)) {
                    user.setPassword("***");
                    session.setAttribute(Const.ADMIN_KEY, user);
                    result.setSuccess(user);
                } else {
                    result.setFailure(TResultCode.DATA_IS_WRONG);
                    return result;
                }
            } else {
                result.setFailure(TResultCode.PERMISSION_NO_ACCESS);
                return result;
            }
        }
        return result;
    }

    @GetMapping("/logoutForAdmin")
    @ApiOperation("管理员登出")
    public TResult logoutForAdmin() {
        TResult result = new TResult();
        session.setAttribute(Const.ADMIN_KEY, null);
        result.setSuccess("管理员登出成功");
        return result;
    }

    @GetMapping("/isLogged")
    @ApiOperation("判断是否登录（返回登录用户信息）")
    public TResult<Map<String, User>> getLoggedUser() {
        TResult<Map<String, User>> tResult = new TResult<>();
        Map<String, User> userMap = new HashMap<>();
        User user = (User) session.getAttribute(Const.USER_KEY);
        User admin = (User) session.getAttribute(Const.ADMIN_KEY);
        if (user != null) {
            userMap.put(Const.USER_KEY, user);
        }
        if (admin != null) {
            userMap.put(Const.ADMIN_KEY, admin);
        }
        String return_str = "UserName:" + user + ";AdminName:" + admin;
        log.info("returnStr:" + return_str);
        if (userMap.size() == 0) {
            tResult.setFailure(TResultCode.DATA_IS_WRONG);
        } else {
            tResult.setSuccess(userMap);
        }
        return tResult;
    }


    @GetMapping("/add")
    @ApiOperation("邮箱链接添加用户")
    public String addUser(@RequestParam("data") @ApiParam("base64值") String data) {
        log.debug("add User date:" + data);
        String dataStr = Enycryption.decryptBASE64(data);
        JSONObject jsonObject = new JSONObject(dataStr);
        String email = jsonObject.getString(Const.EMAIL_KEY);
        EntityWrapper<User> ew = new EntityWrapper<>();
        boolean result = userService.updateForSet("  isValid=true ", ew.eq("email", email));
        String host = Const.LOCAL_IP;
        if (result) {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>SJTU Innovation Platform</title>\n" +
                    "</head>\n" +
                    "<script>\n" +
                    "  window.location='http://" + host + "/#/welcome?login=1'\n" +
                    "</script>" +
                    "<body>\n" +
                    "<h1>添加用户" + email + "成功</h1>\n" +
                    "</body>\n" +
                    "</html>";
        } else {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>SJTU Innovation Platform</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>邮箱验证失败</h1>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }

    /**
     * 通过id进行更新用户除密码外的其他信息
     *
     * @param user
     * @return
     */
    @PutMapping
    @ApiOperation("更新用户信息ById(id password不能修改)")
    public TResult update(@RequestBody @ApiParam(value = "带Id的队伍Body", required = true) User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        boolean result = userService.update(user, entityWrapper.eq("id", user.getId()).eq("password", user.getPassword()));
        TResult t = new TResult();
        if (result) {
            t.setSuccess("跟新用户信息成功");
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    /**
     * 通过id进行更新密码
     *
     * @param user
     * @return
     */
    @PutMapping("/updatePassword")
    @ApiOperation("更新用户ById")
    public TResult updatePassword(@RequestBody @ApiParam("用户(id email password必须)") User user) {
//        boolean result = userService.updateById(user);
        TResult t = new TResult();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("password", user.getPassword());
        boolean result = EmailUtil.sendUpdatePassword(user.getEmail(), jsonObject.toString());
        if (result) {
            t.setSuccess("更新密码确认邮件已发送到邮箱：" + user.getEmail());
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }

    @GetMapping("/updatePasswordByLink")
    @ApiOperation("邮箱链接修改密码")
    public String updatePasswordByLink(@RequestParam("data") @ApiParam("邮箱链接数据") String data) {
        log.info("base64:" + data);
        String jsonStr = Enycryption.decryptBASE64(data);
        log.info("jsonStr:" + jsonStr);
        JSONObject jsonObject = new JSONObject(jsonStr);
        int id = jsonObject.getInt("id");
        String password = jsonObject.getString("password");
        EntityWrapper<User> ew = new EntityWrapper<>();
        boolean result = userService.updateForSet(" password=" + password, ew.eq("id", id));
        String host = EmailUtil.getCurrentHostName();
        if (result) {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>双创平台</title>\n" +
                    "</head>\n" +
                    "<script>\n" +
                    "  window.location='http://" + host + ":9000' \n" +
                    "</script>" +
                    "<body>\n" +
                    "<h1>密码更新成功</h1>\n" +
                    "</body>\n" +
                    "</html>";
        } else {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>双创平台</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>密码更新失败</h1>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户byId(把isValid置为0)")
    @Access(roles = {1})
    public TResult delete(@PathVariable("id") @ApiParam("用户Id") int id) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        boolean result = userService.updateForSet("isValid=0", entityWrapper.eq("id", id));
        TResult t = new TResult();
        if (result) {
            t.setSuccess("deleted user:(id)" + id);
        } else {
            t.setFailure(TResultCode.BUSINESS_ERROR);
        }
        return t;
    }


}
