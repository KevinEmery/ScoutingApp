package com.example.angwy.scoutingapp.matchscouting.datamodel;

import java.util.Date;
import java.util.List;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Match data class to send to server,
 */

/**
 * The object that gathers together all our match data.
 */
public class MatchData {
    public String scout;
    public Date date;
    public int driveStation;
    public Team team;
    public Alliance alliance;
    public boolean deadBot;
    public boolean noShow;
    public List<MatchEvent> autoMatchEvents;
    public List<MatchEvent> teleOpMatchEvents;
    public DefensiveRating defensiveRating;
    public Role role;
}
