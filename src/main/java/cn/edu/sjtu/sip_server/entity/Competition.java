package cn.edu.sjtu.sip_server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@Data
@XmlAccessorType
@XmlRootElement
@TableName("competition")
@ApiModel
public class Competition {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    /**
     * 竞赛名称
     */
    private String name;
    /**
     * 竞赛类别，工科理科等
     */
    @ApiModelProperty("竞赛类别，工科理科等")
    private String category;
    /**
     * 竞赛类型
     */
    @ApiModelProperty("竞赛类型")
    private String type;
    /**
     * 来源
     */
    @ApiModelProperty("来源")
    private String source;
    /**
     * 竞赛级别
     */
    @ApiModelProperty("竞赛级别")
    private String level;
    /**
     * 总决赛地
     */
    @ApiModelProperty("总决赛地")
    @TableField("finalPos")
    private String finalPos;
    /**
     * 报名开始时间
     */
    @ApiModelProperty("报名开始时间")
    @TableField("registerBegin")
    private Timestamp registerBegin;
    /**
     * 报名结束时间
     */
    @ApiModelProperty("报名结束时间")
    @TableField("registerEnd")
    private Timestamp registerEnd;
    /**
     * 竞赛开始时间
     */
    @ApiModelProperty("竞赛开始时间")
    @TableField("competitionBegin")
    private Timestamp competitionBegin;
    /**
     * 竞赛结束时间
     */
    @ApiModelProperty("竞赛结束时间")
    @TableField("competitionEnd")
    private Timestamp competitionEnd;
    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    @TableField("releaseTime")
    private Timestamp releaseTime;
    /**
     * 浏览量
     */
    private Integer pageview;
    /**
     * 状态：正在报名/已结束
     */
    @ApiModelProperty("状态：正在报名/已结束")
    private String status;
    /**
     * 组织单位
     */
    @ApiModelProperty("组织单位")
    private String organizer;
    /**
     * 承办单位
     */
    @ApiModelProperty("承办单位")
    private String undertaker;
    /**
     * 主办单位
     */
    @ApiModelProperty("主办单位")
    private String host;
    /**
     * 发布用户
     */
    @ApiModelProperty("发布用户")
    @TableField("releaseUser")
    private Integer releaseUser;
    /**
     * 奖金
     */
    @ApiModelProperty("奖金")
    private Double bonus;
    /**
     * 竞赛描述
     */
    @ApiModelProperty("竞赛描述")
    private String description;
    /**
     * 暂定
     */
    private Integer university;
    /**
     * 重要程度
     */
    @ApiModelProperty("重要程度")
    private Double importance;
    /**
     * 热度
     */
    @ApiModelProperty("热度")
    private Double heat;
    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String img;
    /**
     * 待审批/审批通过/审批未通过
     */
    @ApiModelProperty("待审批/审批通过/审批未通过")
    private Integer state;
    /**
     * 报名链接
     */
    @ApiModelProperty("报名链接")
    private String url;
    /**
     * 参加竞赛的团队数
     */
    @ApiModelProperty("参加竞赛的团队数")
    @TableField(exist = false)
    private Integer teamNum;

    public Competition(int id, String name, String category, String type, String source, String level, String finalPos, Timestamp registerBegin, Timestamp registerEnd, Timestamp competitionBegin, Timestamp competitionEnd, Timestamp releaseTime, Integer pageview, String status, String organizer, String undertaker, String host, Integer releaseUser, Double bonus, String description, Integer university, Double importance, Double heat, String img, Integer state, String url) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.source = source;
        this.level = level;
        this.finalPos = finalPos;
        this.registerBegin = registerBegin;
        this.registerEnd = registerEnd;
        this.competitionBegin = competitionBegin;
        this.competitionEnd = competitionEnd;
        this.releaseTime = releaseTime;
        this.pageview = pageview;
        this.status = status;
        this.organizer = organizer;
        this.undertaker = undertaker;
        this.host = host;
        this.releaseUser = releaseUser;
        this.bonus = bonus;
        this.description = description;
        this.university = university;
        this.importance = importance;
        this.heat = heat;
        this.img = img;
        this.state = state;
        this.url = url;
    }

    public Competition(String name, String category, String type, String source,
                       String level, String finalPos, Timestamp registerBegin,
                       Timestamp registerEnd, Timestamp competitionBegin,
                       Timestamp competitionEnd, Timestamp releaseTime,
                       Integer pageview, String status, String organizer,
                       String undertaker, String host, Integer releaseUser,
                       Double bonus, String description, Integer university,
                       Double importance, Double heat, String img, Integer state,
                       String url, Integer teamNum) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.source = source;
        this.level = level;
        this.finalPos = finalPos;
        this.registerBegin = registerBegin;
        this.registerEnd = registerEnd;
        this.competitionBegin = competitionBegin;
        this.competitionEnd = competitionEnd;
        this.releaseTime = releaseTime;
        this.pageview = pageview;
        this.status = status;
        this.organizer = organizer;
        this.undertaker = undertaker;
        this.host = host;
        this.releaseUser = releaseUser;
        this.bonus = bonus;
        this.description = description;
        this.university = university;
        this.importance = importance;
        this.heat = heat;
        this.img = img;
        this.state = state;
        this.url = url;
        this.teamNum = teamNum;
    }

    public Competition(Integer id) {
        this.id = id;
    }

    public Competition(String name, String category, String type, String source, String level, String finalPos, Timestamp registerBegin, Timestamp registerEnd, Timestamp competitionBegin, Timestamp competitionEnd, String organizer, String undertaker, String host, Integer releaseUser, Double bonus, String description, Double importance, String img, String url) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.source = source;
        this.level = level;
        this.finalPos = finalPos;
        this.registerBegin = registerBegin;
        this.registerEnd = registerEnd;
        this.competitionBegin = competitionBegin;
        this.competitionEnd = competitionEnd;
        this.organizer = organizer;
        this.undertaker = undertaker;
        this.host = host;
        this.releaseUser = releaseUser;
        this.bonus = bonus;
        this.description = description;
        this.importance = importance;
        this.img = img;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFinalPos() {
        return finalPos;
    }

    public void setFinalPos(String finalPos) {
        this.finalPos = finalPos;
    }

    public Timestamp getRegisterBegin() {
        return registerBegin;
    }

    public void setRegisterBegin(Timestamp registerBegin) {
        this.registerBegin = registerBegin;
    }

    public Timestamp getRegisterEnd() {
        return registerEnd;
    }

    public void setRegisterEnd(Timestamp registerEnd) {
        this.registerEnd = registerEnd;
    }

    public Timestamp getCompetitionBegin() {
        return competitionBegin;
    }

    public void setCompetitionBegin(Timestamp competitionBegin) {
        this.competitionBegin = competitionBegin;
    }

    public Timestamp getCompetitionEnd() {
        return competitionEnd;
    }

    public void setCompetitionEnd(Timestamp competitionEnd) {
        this.competitionEnd = competitionEnd;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(Integer releaseUser) {
        this.releaseUser = releaseUser;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUniversity() {
        return university;
    }

    public void setUniversity(Integer university) {
        this.university = university;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getHeat() {
        return heat;
    }

    public void setHeat(Double heat) {
        this.heat = heat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
