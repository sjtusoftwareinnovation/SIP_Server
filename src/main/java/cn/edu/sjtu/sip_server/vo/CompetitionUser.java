package cn.edu.sjtu.sip_server.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 比赛和用户之间的关系
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@XmlAccessorType
@XmlRootElement
@ApiModel
public class CompetitionUser implements Serializable {
    private int competitionId;
    private int teamId;
    private int userId;
    private int status;
}