package org.usfirst.frc.team4911.scouting.datamodel;

import java.util.Calendar;
import com.google.gson.annotations.SerializedName;
/**
 * Created by johansu on 2/19/2017.
 *
 * Top-level class that stores all scouted data for a given match.
 */

public class ScoutingData {
    @SerializedName("EventCode") private String eventCode;
    @SerializedName("MatchNumber") private int matchNumber;
    @SerializedName("TournamentLevel") private String tournamentLevel;
    @SerializedName("Station") private DriveStation station;
    @SerializedName("TeamNumber") private int teamNumber;
    @SerializedName("DeviceId") private String deviceId;
    @SerializedName("ScoutName") private String scoutName;
    @SerializedName("ScoutingTeamName") private String scoutingTeamName;
    @SerializedName("TimeStamp") private String timeStamp;
    @SerializedName("MatchData") private MatchData matchData;
    @SerializedName("Qualitative") private Qualitative qualitative;

    public ScoutingData(
        String eventCode,
        int matchNumber,
        String tournamentLevel,
        DriveStation station,
        int teamNumber,
        String deviceId,
        String scoutName,
        String scoutingTeamName)
    {
        this.eventCode = eventCode;
        this.matchNumber = matchNumber;
        this.tournamentLevel = tournamentLevel;
        this.station = station;
        this.teamNumber = teamNumber;
        this.deviceId = deviceId;
        this.scoutName = scoutName;
        this.scoutingTeamName = scoutingTeamName;
        this.timeStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime());
        this.matchData = new MatchData();
        //this.qualitative = new Qualitative();
    }

    public MatchData getMatchData() {
        return this.matchData;
    }

    public void setMatchData(MatchData matchData) {
        this.matchData = matchData;
    }

    public void setTournamentLevel(String tournamentLevel) {
        this.tournamentLevel = tournamentLevel;
    }
}