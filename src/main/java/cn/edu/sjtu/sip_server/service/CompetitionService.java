package  cn.edu.sjtu.sip_server.service;

import cn.edu.sjtu.sip_server.entity.Competition;
import cn.edu.sjtu.sip_server.entity.CompetitionComment;
import cn.edu.sjtu.sip_server.request.CompetitionListRequest;
import cn.edu.sjtu.sip_server.response.CompetitionListResponse;
import cn.edu.sjtu.sip_server.response.CompetitionTeamResponse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface CompetitionService extends IService<Competition> {
    /**
     * 获取竞赛列表
     *
     * @param request
     * @return
     */
    CompetitionListResponse getCompetitionList(CompetitionListRequest request);

    /**
     * 获取竞赛详情
     *
     * @param id
     * @return
     */
    Competition getCompetitionDetail(int id);

    /**
     * 获取竞赛详情(with Team)
     *
     * @param id
     * @return
     */
    CompetitionTeamResponse getCompetitionTeamDetail(int id);

    /**
     * 每天更新竞赛状态
     */
    void updateCompetitionStatus();

    /**
     * 新增竞赛
     *
     * @param competition
     * @return
     */
    Competition insertCompetition(Competition competition);

    /**
     * 修改竞赛
     *
     * @param competition
     * @return
     */
    Competition modifyCompetition(Competition competition);

    /**
     * 搜索竞赛
     *
     * @param name
     * @return
     */
    CompetitionListResponse searchCompetition(String name);

    /**
     * 删除竞赛
     *
     * @param id
     * @return
     */
    boolean deleteCompetition(int id);

    /**
     * 审批竞赛
     *
     * @param id
     * @param isApprove
     * @return
     */
    boolean approveCompetition(int id, int isApprove);

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    CompetitionComment addComment(CompetitionComment comment);

    /**
     * 获得所有评论
     *
     * @param competitionId
     * @return
     */
    List<CompetitionComment> getComments(int competitionId);
}
