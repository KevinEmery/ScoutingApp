package org.usfirst.frc.team4911.scouting.datamodel;

import android.view.ViewGroup;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Anne_ on 1/18/2017.
 * Modified by johansu on 2/19/2017.
 */

public class MatchData
{
    // The pre-game data on how a team sets up for a match.
    @SerializedName("PreGame") PreGame preGame;

    // The autonomous period data, the first 15s of the match
    @SerializedName("AutonomousPeriod") AutonomousPeriod autonomousPeriod;

    // The teleop period data, from 15s to about 30s before match is over
    @SerializedName("TeleopPeriod") TeleopPeriod teleopPeriod;

    // The end game period is typically the last 30s
    @SerializedName("EndGame")  EndGame endGame;

    public MatchData(){
        this.setPreGame(new PreGame());
        this.setAutonomousPeriod(new AutonomousPeriod());
        this.setTeleopPeriod(new TeleopPeriod());
        this.setEndGame(new EndGame());
    }

    public EndGame getEndGame() {
        return endGame;
    }

    public void setEndGame(EndGame endGame) {
        this.endGame = endGame;
    }

    public PreGame getPreGame() {
        return preGame;
    }

    public void setPreGame(PreGame preGame) {
        this.preGame = preGame;
    }

    public AutonomousPeriod getAutonomousPeriod() {
        return autonomousPeriod;
    }

    public void setAutonomousPeriod(AutonomousPeriod autonomousPeriod) {
        this.autonomousPeriod = autonomousPeriod;
    }

    public TeleopPeriod getTeleopPeriod() {
        return teleopPeriod;
    }

    public void setTeleopPeriod(TeleopPeriod teleopPeriod) {
        this.teleopPeriod = teleopPeriod;
    }
}
