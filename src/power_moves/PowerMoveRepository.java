package power_moves;

import java.util.ArrayList;
import java.util.List;

//repository class for power moves
public class PowerMoveRepository {

    private static PowerMoveRepository INSTANCE;
    private ArrayList<PowerMove> availablePowerMoveList;

    //factory method to get instance of Repository
    public static PowerMoveRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new PowerMoveRepository();
        }

        return INSTANCE;
    }


    //private constructor
    private PowerMoveRepository() {
    }

    public List<PowerMove> getAvailablePowerMoveList() {
        this.availablePowerMoveList = new ArrayList<>();
        availablePowerMoveList.add(getPunch());
        availablePowerMoveList.add(getKick());
        availablePowerMoveList.add(getFly());
        availablePowerMoveList.add(getFlyingKick());
        availablePowerMoveList.add(getFlyingPunch());
        return availablePowerMoveList;
    }

    public List<PowerMove> getFlyingTrainingMoveList() {
        List<PowerMove> flyingTrainingMoves = new ArrayList<>();
        flyingTrainingMoves.add(getFly());
        flyingTrainingMoves.add(getFlyingKick());
        flyingTrainingMoves.add(getFlyingPunch());
        return flyingTrainingMoves;
    }

    public List<PowerMove> getBasicTrainingMoveList() {
        List<PowerMove> basicTrainingMoves = new ArrayList<>();
        basicTrainingMoves.add(getKick());
        basicTrainingMoves.add(getPunch());
        basicTrainingMoves.add(getBlock());
        return basicTrainingMoves;
    }

    public PowerMove getBlock() {
        return new PowerMove("MOVE_BLOCK", "basic defence block", "B", PowerMove.MOVE_DAMAGE_NONE);
    }

    private PowerMove getFly() {
        return new PowerMove("MOVE_FLY", "fly in air", "F", PowerMove.MOVE_DAMAGE_NONE);
    }

    private PowerMove getKick() {
        return new PowerMove("MOVE_KICK", "basic kick", "K", PowerMove.MOVe_DAMAGE_LOW);
    }

    private PowerMove getPunch() {
        return new PowerMove("MOVE_PUNCH", "basic punch", "P", PowerMove.MOVe_DAMAGE_LOW);
    }

    private PowerMove getFlyingKick() {
        return new PowerMove("MOVE_FLY_KICK", "flying kick", "FK", PowerMove.MOVE_DAMAGE_NORMAL);
    }

    private PowerMove getFlyingPunch() {
        return new PowerMove("MOVE_FLY_PUNCH", "flying punch", "FP", PowerMove.MOVE_DAMAGE_NORMAL);
    }
}
