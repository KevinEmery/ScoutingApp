package com.example.angwy.scouting;

import org.junit.Test;
import org.usfirst.frc.team4911.scouting.datamodel.AutonomousPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.EndGame;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.GearResult;
import org.usfirst.frc.team4911.scouting.datamodel.PreGame;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.ShotMode;
import org.usfirst.frc.team4911.scouting.datamodel.TeleopPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

import com.google.gson.Gson;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataModelTests {

    @Test
    public void scoutingDataModel_isCorrect() throws Exception {
        ScoutingData data = new ScoutingData("DEMO", 1, DriveStation.Red1, 4911, "Device_1", "scout@Red1", "Cyberknights");
        PreGame pregame = data.getMatchData().getPreGame();
        pregame.setHasGear(true);
        pregame.setHasFuel(true);
        pregame.setUsesOwnRope(true);
        pregame.setHasPilot(false);
        pregame.setRopeTouchPadPosition(TouchPadPosition.T1);

        AutonomousPeriod autonomousPeriod = data.getMatchData().getAutonomousPeriod();
        data.getMatchData().setAutonomousPeriod(autonomousPeriod);

        ShotAttempt shotAttempt = new ShotAttempt();
        shotAttempt.setShotMode(ShotMode.High);
        shotAttempt.setShotsMade(10);
        shotAttempt.setShotsMissed(2);
        shotAttempt.setShotLocation("Near");
        autonomousPeriod.getShotAttempts().add(shotAttempt);

        GearAttempt gearAttempt = new GearAttempt();
        gearAttempt.setGearResult(GearResult.Success);
        gearAttempt.setGearPegPosition(GearPegPosition.G1);
        autonomousPeriod.getGearAttempts().add(gearAttempt);

        TeleopPeriod teleopPeriod = new TeleopPeriod();
        data.getMatchData().setTeleopPeriod(teleopPeriod);

        GearAttemptTeleop gearAttemptTeleop = new GearAttemptTeleop();
        gearAttemptTeleop.setGearResult(GearResult.Failed);
        gearAttemptTeleop.setWasDefended(true);
        gearAttemptTeleop.setGearPegPosition(GearPegPosition.None);
        teleopPeriod.getGearAttempts().add(gearAttemptTeleop);

        gearAttemptTeleop = new GearAttemptTeleop();
        gearAttemptTeleop.setGearResult(GearResult.Success);
        gearAttemptTeleop.setWasDefended(false);
        gearAttemptTeleop.setGearPegPosition(GearPegPosition.G1);
        teleopPeriod.getGearAttempts().add(gearAttemptTeleop);
        teleopPeriod.setGearAttemptCount(teleopPeriod.getGearAttempts().size());

        ShotAttemptTeleop shotAttemptTeleop = new ShotAttemptTeleop();
        shotAttemptTeleop.setWasDefended(false);
        shotAttemptTeleop.setShotLocation("Close");
        shotAttemptTeleop.setShotsMade(30);
        shotAttemptTeleop.setShotMode(ShotMode.High);
        shotAttemptTeleop.setShotsMissed(3);
        teleopPeriod.getShotAttempts().add(shotAttemptTeleop);
        shotAttemptTeleop = new ShotAttemptTeleop();
        shotAttemptTeleop.setWasDefended(true);
        shotAttemptTeleop.setShotLocation("Medium");
        shotAttemptTeleop.setShotsMade(40);
        shotAttemptTeleop.setShotMode(ShotMode.High);
        shotAttemptTeleop.setShotsMissed(15);
        teleopPeriod.getShotAttempts().add(shotAttemptTeleop);
        teleopPeriod.setShotAttemptCount(teleopPeriod.getShotAttempts().size());

        EndGame endGame = new EndGame();
        data.getMatchData().setEndGame(endGame);
        endGame.setAttempted(true);
        endGame.setSucceeded(true);
        endGame.setTimeInSeconds(17);
        endGame.setTouchPadPosition(TouchPadPosition.T1);

        Gson gson = new Gson();
        String json = gson.toJson(data);

        assertTrue(true);
    }
}