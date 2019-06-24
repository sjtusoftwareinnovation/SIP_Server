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
@TableName("business_need")
@ApiModel
public class BusinessNeed {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 校企名称
     */
    private String name;
    /**
     * 项目描述
     */
    @ApiModelProperty("项目描述")
    private String description;
    /**
     * 来源范围
     */
    @ApiModelProperty("来源范围")
    private String source;
    /**
     * 项目类型
     */
    @ApiModelProperty("项目类型")
    private String type;
    /**
     * 学生类型：本硕博
     */
    @ApiModelProperty("学生类型：本硕博")
    @TableField("studentType")
    private String studentType;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    @TableField("releaseTime")
    private Timestamp releaseTime;
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
     * 浏览量
     */
    private long pageview;
    /**
     * 类别
     */
    private String category;
    /**
     * 状态：正在报名/已结束
     */
    @ApiModelProperty("状态：正在报名/已结束")
    private String status;
    /**
     * 发布单位
     */
    @ApiModelProperty("发布单位")
    @TableField("releaseUnit")
    private String releaseUnit;
    /**
     * 发布用户
     */
    @ApiModelProperty("发布用户")
    @TableField("releaseUser")
    private Integer releaseUser;
    /**
     * 待定
     */
    private Integer university;
    /**
     * 封面图片
     */
    @ApiModelProperty("封面图片")
    private String img;
    /**
     * 报名链接
     */
    @ApiModelProperty("报名链接")
    @TableField("registerUrl")
    private String registerUrl;
    /**
     * 所需人数
     */
    @ApiModelProperty("所需人数")
    @TableField("peopleNum")
    private long peopleNum;
    /**
     * 校企简介
     */
    @ApiModelProperty("校企简介")
    private String introduction;
    /**
     * 表示是否删除
     */
    @ApiModelProperty("表示是否删除")
    private int state;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
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

    public long getPageview() {
        return pageview;
    }

    public void setPageview(long pageview) {
        this.pageview = pageview;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public Integer getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(Integer releaseUser) {
        this.releaseUser = releaseUser;
    }

    public Integer getUniversity() {
        return university;
    }

    public void setUniversity(Integer university) {
        this.university = university;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public long getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(long peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
