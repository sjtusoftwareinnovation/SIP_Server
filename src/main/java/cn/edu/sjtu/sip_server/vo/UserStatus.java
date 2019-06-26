package cn.edu.sjtu.sip_server.vo;

import cn.edu.sjtu.sip_server.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@XmlAccessorType
@XmlRootElement
@ApiModel
public class UserStatus extends User implements Serializable {
    @ApiModelProperty("用户在对应队伍中的状态-2/removed,-1/rejected,0/apply,1/accepted")
    private int status;
    @ApiModelProperty("用户申请对应队伍的id")
    private int applyId;

    public UserStatus(int id, String avatar, String wechatCode, String username, String email, String password,
                      String qq, int role, int isValid, int status, int applyId) {
        super(id, avatar, wechatCode, username, email, password, qq, role, isValid);
        this.status = status;
        this.applyId = applyId;
    }

    public UserStatus(String avatar, String wechatCode, String username, String password, String qq, int role,
                      int isValid, int status, int applyId) {
        super(avatar, wechatCode, username, password, qq, role, isValid);
        this.status = status;
        this.applyId = applyId;
    }

    public UserStatus(String username, String email, String password, int role, int status) {
        super(username, email, password, role);
        this.status = status;
        this.applyId = applyId;
    }

    public UserStatus(int status, int applyId) {
        this.status = status;
        this.applyId = applyId;
    }

    public UserStatus() {
        super();
    }
}