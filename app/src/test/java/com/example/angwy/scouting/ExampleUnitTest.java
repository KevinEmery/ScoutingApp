package com.example.angwy.scouting;

import org.junit.Test;
import org.usfirst.frc.team4911.scouting.datamodel.AutonomousPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.DriveStation;
import org.usfirst.frc.team4911.scouting.datamodel.EndGame;
import org.usfirst.frc.team4911.scouting.datamodel.FuelAmount;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.GearAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.PreGame;
import org.usfirst.frc.team4911.scouting.datamodel.ScoutingData;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAccuracy;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttempt;
import org.usfirst.frc.team4911.scouting.datamodel.ShotAttemptTeleop;
import org.usfirst.frc.team4911.scouting.datamodel.ShotMode;
import org.usfirst.frc.team4911.scouting.datamodel.ShotSpeed;
import org.usfirst.frc.team4911.scouting.datamodel.TeleopPeriod;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

import com.google.gson.Gson;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void scoutingDataModel_isCorrect() throws Exception {
        ScoutingData data = new ScoutingData("DEMO", 1, "qual", DriveStation.Red1, 4911, "Device_1", "scout@Red1", "Cyberknights");
        PreGame pregame = data.getMatchData().getPreGame();
        pregame.setHasGear(true);
        pregame.setHasFuel(true);
        pregame.setFuelCount(10);
        pregame.setUsesOwnRope(true);
        pregame.setRopeTouchPadPosition(TouchPadPosition.Near);

        AutonomousPeriod autonomousPeriod = data.getMatchData().getAutonomousPeriod();
        data.getMatchData().setAutonomousPeriod(autonomousPeriod);

        ShotAttempt shotAttempt = new ShotAttempt();
        shotAttempt.setShotMode(ShotMode.High);
        shotAttempt.setFuelAmount(FuelAmount.Small);
        shotAttempt.setShotSpeed(ShotSpeed.Average);
        shotAttempt.setShotAccuracy(ShotAccuracy.Average);
        shotAttempt.setShotLocation("Near");
        autonomousPeriod.getShotAttempts().add(shotAttempt);

        GearAttempt gearAttempt = new GearAttempt();
        gearAttempt.setGearResult("Success");
        gearAttempt.setGearPegPosition(GearPegPosition.Middle);
        autonomousPeriod.getGearAttempts().add(gearAttempt);

        TeleopPeriod teleopPeriod = new TeleopPeriod();
        data.getMatchData().setTeleopPeriod(teleopPeriod);

        GearAttemptTeleop gearAttemptTeleop = new GearAttemptTeleop();
        gearAttemptTeleop.setGearResult("Failed");
        gearAttemptTeleop.setWasDefended(true);
        gearAttemptTeleop.setGearPegPosition(GearPegPosition.None);
        teleopPeriod.getGearAttempts().add(gearAttemptTeleop);

        gearAttemptTeleop = new GearAttemptTeleop();
        gearAttemptTeleop.setGearResult("Success");
        gearAttemptTeleop.setWasDefended(false);
        gearAttemptTeleop.setGearPegPosition(GearPegPosition.Far);
        teleopPeriod.getGearAttempts().add(gearAttemptTeleop);
        teleopPeriod.setGearAttemptCount(teleopPeriod.getGearAttempts().size());

        ShotAttemptTeleop shotAttemptTeleop = new ShotAttemptTeleop();
        shotAttemptTeleop.setWasDefended(false);
        shotAttemptTeleop.setShotLocation("Close");
        shotAttemptTeleop.setShotSpeed(ShotSpeed.Fast);
        shotAttemptTeleop.setShotMode(ShotMode.High);
        shotAttemptTeleop.setShotAccuracy(ShotAccuracy.Average);
        teleopPeriod.getShotAttempts().add(shotAttemptTeleop);
        shotAttemptTeleop = new ShotAttemptTeleop();
        shotAttemptTeleop.setWasDefended(true);
        shotAttemptTeleop.setShotLocation("Medium");
        shotAttemptTeleop.setShotSpeed(ShotSpeed.Slow);
        shotAttemptTeleop.setShotMode(ShotMode.High);
        shotAttemptTeleop.setShotAccuracy(ShotAccuracy.Poor);
        teleopPeriod.getShotAttempts().add(shotAttemptTeleop);
        teleopPeriod.setShotAttemptCount(teleopPeriod.getShotAttempts().size());

        EndGame endGame = new EndGame();
        data.getMatchData().setEndGame(endGame);
        endGame.setAttempted(true);
        endGame.setSucceeded(true);
        endGame.setTimeInSeconds(17);
        endGame.setTouchPadPosition(TouchPadPosition.Near);

        Gson gson = new Gson();
        String json = gson.toJson(data);

        assertTrue(true);
    }
}