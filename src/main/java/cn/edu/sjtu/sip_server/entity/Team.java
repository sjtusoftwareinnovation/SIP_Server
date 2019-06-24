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
import java.sql.Timestamp;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@XmlAccessorType
@XmlRootElement
@TableName("team")
@ApiModel
public class Team implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("队伍Id")
    private int id;
    private String name;
    /**
     * 队长，fk:user.id
     */
    private int leader;
    @ApiModelProperty("队伍描述")
    private String description;
    @TableField("competitionId")
    @ApiModelProperty("所属比赛Id")
    private int competitionId;
    @ApiModelProperty("0失效 1组队中 2组队完成")
    private int status;
    /**
     * 組队截止时间
     */
    @TableField("validTime")
    @ApiModelProperty("组队截止时间")
    private Timestamp validTime;
    /**
     * 队长联系方式
     */
    @ApiModelProperty("队长联系方式(qq)")
    private String qq;

    public Team(String name, int leader, String description,
                int competitionId, int status, Timestamp validTime, String qq) {
        this.name = name;
        this.leader = leader;
        this.description = description;
        this.competitionId = competitionId;
        this.status = status;
        this.validTime = validTime;
        this.qq = qq;
    }

    public Team() {
        this.status = 1;
    }
}