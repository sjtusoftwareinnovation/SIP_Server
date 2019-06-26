package cn.edu.sjtu.sip_server.request;

public class CompetitionListRequest {
    /**
     * 页数
     */
    public int page;
    /**
     * 发布时间
     */
    public String releaseTime;
    /**
     * 组织者
     */
    public String host;
    /**
     * 竞赛类型
     */
    public String type;
    /**
     * 竞赛状态
     */
    public String status;
    /**
     * 排序方式
     */
    public String orderBy;
    /**
     * 竞赛级别
     */
    public String level;
    /**
     * 竞赛名
     */
    public String name;

    public CompetitionListRequest(int page, String releaseTime, String host, String type, String status, String orderBy, String level, String name) {
        this.page = page;
        this.releaseTime = releaseTime;
        this.host = host;
        this.type = type;
        this.status = status;
        this.orderBy = orderBy;
        this.level = level;
        this.name = name;
    }

    public CompetitionListRequest() {

    }
}
