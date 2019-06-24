package cn.edu.sjtu.sip_server.entity;

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
@TableName("activity")
@ApiModel
public class Activity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    @ApiModelProperty(value = "未开始/进行中/已结束")
    private Integer status;
    @ApiModelProperty(value = "报名人数限制")
    private Integer numberLimit;
    @ApiModelProperty(value = "报名开始时间")
    private Timestamp registerBegin;
    @ApiModelProperty(value = "报名结束时间")
    private Timestamp registerEnd;
    @ApiModelProperty(value = "主办方")
    private String host;
    @ApiModelProperty(value = "封面图片链接")
    private String coverPic;
    @ApiModelProperty(value = "活动地点")
    private String location;
    @ApiModelProperty(value = "收藏数")
    private Integer likeNum;
    @ApiModelProperty(value = "活动开始时间")
    private Timestamp activityEnd;
    @ApiModelProperty(value = "活动结束时间")
    private Timestamp activityBegin;
    @ApiModelProperty(value = "软删除标记")
    private Integer isValid;
    @ApiModelProperty(value = "发布时间")
    private Timestamp releaseTime;
    @ApiModelProperty(value = "浏览量")
    private Integer pageview;
    @ApiModelProperty(value = "权重")
    private Integer weight;
    @ApiModelProperty(value = "竞赛/项目/企业等")
    private Integer typeId;
    @ApiModelProperty(value = "国家级/省级等")
    private Integer levelId;
    @ApiModelProperty(value = "生物竞赛/化学竞赛等")
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Timestamp getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Timestamp activityEnd) {
        this.activityEnd = activityEnd;
    }

    public Timestamp getActivityBegin() {
        return activityBegin;
    }

    public void setActivityBegin(Timestamp activityBegin) {
        this.activityBegin = activityBegin;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
