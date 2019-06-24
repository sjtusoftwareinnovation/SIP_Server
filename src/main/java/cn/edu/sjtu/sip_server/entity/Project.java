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
@TableName("project")
@ApiModel
public class Project implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    /**
     * 主办单位
     * fk:unit.id
     */
    @ApiModelProperty("主办单位fk:unit.id")
    private int host;
    /**
     * 组织单位
     * fk:unit.id
     */
    @ApiModelProperty("组织单位fk:unit.id")
    private int organizer;
    private int source;
    private String type;
    /**
     * 组队地区
     */
    @ApiModelProperty("组队地区")
    private String area;
    /**
     * 封面图片，保存文件名， picture/文件名进行访问
     */
    @ApiModelProperty("封面图片，保存文件名， picture/文件名进行访问")
    private String cover;
    /**
     * 报名开始时间
     */
    @TableField("registerBegin")
    @ApiModelProperty("报名开始时间")
    private Timestamp registerBegin;
    /**
     * 报名截止时间
     */
    @TableField("registerEnd")
    @ApiModelProperty("报名截止时间")
    private Timestamp registerEnd;
    /**
     * 发布时间
     */
    @TableField("releaseTime")
    @ApiModelProperty("发布时间")
    private Timestamp releaseTime;
    /**
     * 项目开始时间
     */
    @TableField("projectBegin")
    @ApiModelProperty("项目开始时间")
    private Timestamp projectBegin;
    /**
     * 项目截止时间
     */
    @TableField("projectEnd")
    @ApiModelProperty("项目截止时间")
    private Timestamp projectEnd;
    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private int pageview;
    /**
     * 2未开始，1正在报名，0已结束
     */
    @ApiModelProperty("项目状态，2未开始，1正在报名，0已结束")
    private int status;
    /**
     * 发布用户,fk:user.id
     */
    @TableField("releaseUser")
    @ApiModelProperty("发布用户,fk:user.id")
    private int releaseUser;
    @ApiModelProperty("描述")
    private String description;
    /**
     * 所需人數，eg:2-3、3
     */
    @TableField("personNum")
    @ApiModelProperty("所需人數，eg:2-3、3")
    private int personNum;
    /**
     * 承办单位,fk:unit.id
     */
    @ApiModelProperty("承办单位,fk:unit.id")
    private int undertaker;
    /**
     * 暂定，发布的学校,fk:unit.id
     */
    @ApiModelProperty("暂定，发布的学校,fk:unit.id")
    private int university;

    @ApiModelProperty("项目联系方式")
    private String contact;

    public Project() {
    }

    public Project(String name, int host, int organizer, int source, String type, String area, String cover, Timestamp registerBegin,
                   Timestamp registerEnd, Timestamp releaseTime, Timestamp projectBegin, Timestamp projectEnd,
                   int pageview, int status, int releaseUser, String description, int personNum, int undertaker,
                   int university, String contact) {
        this.name = name;
        this.host = host;
        this.organizer = organizer;
        this.source = source;
        this.type = type;
        this.area = area;
        this.cover = cover;
        this.registerBegin = registerBegin;
        this.registerEnd = registerEnd;
        this.releaseTime = releaseTime;
        this.projectBegin = projectBegin;
        this.projectEnd = projectEnd;
        this.pageview = pageview;
        this.status = status;
        this.releaseUser = releaseUser;
        this.description = description;
        this.personNum = personNum;
        this.undertaker = undertaker;
        this.university = university;
        this.contact = contact;
    }
}
