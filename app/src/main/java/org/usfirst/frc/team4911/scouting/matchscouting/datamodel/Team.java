package org.usfirst.frc.team4911.scouting.matchscouting.datamodel;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Class describing a team.
 */

public class Team {
    private String teamName;
    private int teamNumber;

    public Team(String teamName, int teamNumber) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getTeamNumber() {
        return this.teamNumber;
    }
}
