package cn.edu.sjtu.sip_server.util;

import cn.edu.sjtu.sip_server.service.BusinessService;
import cn.edu.sjtu.sip_server.service.CompetitionService;
import cn.edu.sjtu.sip_server.service.ProjectService;
import cn.edu.sjtu.sip_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTask {
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @Scheduled(cron = "0 1 0 * * ?")
    public void updateCompetitionStatus() {
        competitionService.updateCompetitionStatus();
        businessService.updateBusinessStatus();
        projectService.updateProjectStatus();
        log.info("Updating status");
    }

    @Scheduled(cron = "0 1 0 * * ?")
    public void deleteInvalidUser() {
        userService.deleteInvalidUser();
        log.info("delete invalid user");
    }
}
