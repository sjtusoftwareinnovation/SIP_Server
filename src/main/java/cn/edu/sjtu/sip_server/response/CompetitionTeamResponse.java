package com.amateur.sip.server.response;

import com.amateur.sip.server.entity.Competition;
import com.amateur.sip.server.entity.Team;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Api
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionTeamResponse implements Serializable {
    public Competition competition;
    public List<Team> teamList;
}