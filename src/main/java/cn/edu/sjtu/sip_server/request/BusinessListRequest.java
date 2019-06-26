package cn.edu.sjtu.sip_server.request;

public class BusinessListRequest {
    /**
     * 页数
     */
    public int page;
    /**
     * 类型
     */
    public String type;
    /**
     * 类别
     */
    public String category;
    /**
     * 状态
     */
    public String status;
    /**
     * 来源
     */
    public String source;
    /**
     * 学生类型
     */
    public String studentType;
    /**
     * 排序方式
     */
    public String orderBy;
    /**
     * 名字
     */
    public String name;


    public BusinessListRequest() {

    }

    public BusinessListRequest(int page, String type, String category, String status, String source, String studentType, String orderBy, String name) {
        this.page = page;
        this.type = type;
        this.category = category;
        this.status = status;
        this.source = source;
        this.studentType = studentType;
        this.orderBy = orderBy;
        this.name = name;
    }
}
