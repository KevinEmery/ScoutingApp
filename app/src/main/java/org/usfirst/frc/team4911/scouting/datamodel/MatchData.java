package org.usfirst.frc.team4911.scouting.datamodel;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Match data class to send to server,
 */

public class MatchData {
    private String scout;
    private String dateStamp;
    private int driveStation;
    private Team team;
    private Alliance alliance;
    private boolean deadBot;
    private boolean noShow;
    private List<MatchEvent> autoMatchEvents;
    private List<MatchEvent> teleOpMatchEvents;
    private DefensiveRating defensiveRating;
    private Role role;
    private PenaltyType penalty;

    public MatchData(String scout, int driveStation, Team team, Alliance alliance) {
        this.scout = scout;
        this.driveStation = driveStation;
        this.team = team;
        this.alliance = alliance;

        this.dateStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime());
        this.autoMatchEvents = new ArrayList<>();
        this.teleOpMatchEvents = new ArrayList<>();
    }

    public String getScout() {
        return this.scout;
    }

    public String getDate() {
        return this.dateStamp;
    }

    public int getDriveStation() {
        return this.driveStation;
    }

    public Team getTeam() {
        return this.team;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public boolean getDeadbot() {
        return this.deadBot;
    }

    public void setDeadBot(boolean deadBot) {
        this.deadBot = deadBot;
    }

    public boolean getNoShow() {
        return this.noShow;
    }

    public void setNoShow(boolean noShow) {
        this.noShow = noShow;
    }

    public List<MatchEvent> getAutoMatchEvents() {
        return this.autoMatchEvents;
    }

    public void addAutoMatchEvent(MatchEvent matchEvent) {
        this.autoMatchEvents.add(matchEvent);
    }

    public List<MatchEvent> getTeleOpMatchEvents() {
        return this.teleOpMatchEvents;
    }

    public void addTeleOpMatchEvent(MatchEvent matchEvent) {
        this.teleOpMatchEvents.add(matchEvent);
    }

    public DefensiveRating getDefensiveRating() {
        return this.defensiveRating;
    }

    public void setDefensiveRating(DefensiveRating defensiveRating) {
        this.defensiveRating = defensiveRating;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public PenaltyType getPenalty() {
        return this.penalty;
    }

    public void setPenalty(PenaltyType penalty) {
        this.penalty = penalty;
    }
}
