package org.usfirst.frc.team4911.scouting.datamodel;

/**
 * Created by Anne_ on 1/28/2017.
 *
 * Extends matchevent to have data particular to collecting game pieces.
 */

public class GamePieceLoadEvent extends MatchEvent {
    private GamePiece gamePiece;

    public GamePiece getGamePiece() {
        return this.gamePiece;
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }
}
