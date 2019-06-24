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

@ApiModel
@Data
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType
@TableName("team_user")
public class TeamUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("队伍用户对应关系的Id")
    private int id;
    @TableField("teamId")
    private int teamId;
    @TableField("userId")
    private int userId;
    /**
     * -2/removed,-1/reject,0/apply,1/accept
     */
    @ApiModelProperty("-2/removed,-1/reject,0/apply,1/accept")
    private int status;

    public TeamUser(int teamId, int userId, int status) {
        this.teamId = teamId;
        this.userId = userId;
        this.status = status;
    }

    public TeamUser(int teamId, int userId) {
        this.teamId = teamId;
        this.userId = userId;
        this.status = 0;
    }

    public TeamUser() {
        this.status = 0;
    }

    @Override
    public boolean equals(Object object) {
        TeamUser teamUser = (TeamUser) object;
        if (teamUser.getTeamId() == this.teamId
                && teamUser.getUserId() == this.userId
                && teamUser.getStatus() == this.status) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.teamId;
        result = 31 * result + this.userId;
        result = 31 * result + this.status;
        return result;
    }

}