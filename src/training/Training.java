package training;

import player.Player;
import power_moves.PowerMove;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//abstract class represent training
// Each training give set of moves to player
//Training is mandatory for fight.
// More trainings can be created by extending this class.
public abstract class Training implements Serializable{

    public  String trainingId;
    public Boolean isCompleted = false;
    public List<PowerMove> moves = new ArrayList();

    //method to create training procedure.
    public abstract boolean getTrain(Player player);

    public abstract void setTrainingId(String trainingId);

    public String getTrainingId() {
        return trainingId;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }
}
