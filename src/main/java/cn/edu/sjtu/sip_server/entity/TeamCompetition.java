package cn.edu.sjtu.sip_server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@ApiModel
@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType
@TableName("team_competition")
public class TeamCompetition implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("competitionId")
    private int competitionId;
    @TableField("teamId")
    private int teamId;
}