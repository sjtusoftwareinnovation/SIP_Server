package cn.edu.sjtu.sip_server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType
@XmlRootElement
@TableName("user")
@ApiModel
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户Id")
    protected int id;
    /**
     * 头像，上传头像
     */
    @ApiModelProperty("头像url（一般为picture/*.***）")
    protected String avatar;
    /**
     * wechat用户唯一标识符
     */
    @TableField("wechatCode")
    protected String wechatCode;
    @ApiModelProperty("用户名")
    protected String username;
    /**
     * not null and unique
     */
    protected String email;
    protected String password;
    /**
     * 联系方式(qq)，可选
     */
    protected String qq;
    @ApiModelProperty("用户角色，0普通1管理员2超级管理员")
    protected int role;
    @TableField("isValid")
    @ApiModelProperty("邮箱是否激活，未激活不可用")
    protected int isValid;

    public User(String avatar, String wechatCode, String username, String password, String qq, int role, int isValid) {
        this.avatar = avatar;
        this.wechatCode = wechatCode;
        this.username = username;
        this.password = password;
        this.qq = qq;
        this.role = role;
        this.isValid = isValid;
    }

    public User(String username, String email, String password, int role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, String qq) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.qq = qq;
        this.role = 0;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = 0;
    }

    public User() {
        this.role = 0;
    }
}
