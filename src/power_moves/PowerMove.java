package power_moves;

import java.io.Serializable;

//class represent move which are used in fight
// each move has its damage level.

public class PowerMove implements Serializable {

    public static int MOVE_DAMAGE_NONE = 0;
    public static int MOVe_DAMAGE_LOW = 5;
    public static int MOVE_DAMAGE_NORMAL = 10;
    public static int MOVE_DAMAGE_HIGH = 15;


    private String moveId;
    private String moveDescription = "No description Available";
    private String moveSymbol;
    private int moveDamageLevel;
    private boolean moveMastered = false;

    public PowerMove(String moveId, String moveSymbol, int moveDamageLevel) {
        this.moveId = moveId;
        this.moveSymbol = moveSymbol;
        this.moveDamageLevel = moveDamageLevel;
    }

    PowerMove(String moveId, String moveDescription, String moveSymbol, int moveDamageLevel) {
        this.moveId = moveId;
        this.moveDescription = moveDescription;
        this.moveSymbol = moveSymbol;
        this.moveDamageLevel = moveDamageLevel;
    }

    public String getMoveId() {
        return moveId;
    }

    public String getMoveDescription() {
        return moveDescription;
    }

    public String getMoveSymbol() {
        return moveSymbol;
    }

    public int getMoveDamageLevel() {
        return moveDamageLevel;
    }

    public void setMoveMastered(boolean moveMastered) {
        this.moveMastered = moveMastered;
    }
}
