package org.usfirst.frc.team4911.scouting.datamodel;

import java.util.Calendar;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
/**
 * Created by johansu on 2/19/2017.
 */

public class ScoutingData {
    @SerializedName("EventCode") String eventCode;
    @SerializedName("MatchNumber")int matchNumber;
    @SerializedName("TournamentLevel") String tournamentLevel;
    @SerializedName("Station") DriveStation station;
    @SerializedName("TeamNumber") int teamNumber;
    @SerializedName("DeviceId") String deviceId;
    @SerializedName("ScoutName") String scoutName;
    @SerializedName("ScoutingTeamName") String scoutingTeamName;
    @SerializedName("TimeStamp") String timeStamp;
    @SerializedName("MatchData") MatchData matchData;
    @SerializedName("Qualitative") Qualitative qualitative;

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
}