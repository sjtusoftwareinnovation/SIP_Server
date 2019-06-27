package cn.edu.sjtu.sip_server.util;

import cn.edu.sjtu.sip_server.response.TeamStatusResponse;

import java.util.List;

public class TeamUtil {

    /**
     * 判断List中对象是否至少含有一个该status的对象
     *
     * @param teamStatusResponseList
     * @param status
     * @return
     */
    public static boolean atLeastOne(List<TeamStatusResponse> teamStatusResponseList, int status) {
        if (teamStatusResponseList == null) {
            return false;
        }
        for (TeamStatusResponse teamStatusResponse : teamStatusResponseList) {
            if (teamStatusResponse.tstatus == status) {
                return true;
            }
        }
        return false;
    }
}