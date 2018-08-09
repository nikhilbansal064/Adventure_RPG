package training;

import player.Player;
import power_moves.PowerMove;
import power_moves.PowerMoveRepository;

import java.util.List;
import java.util.Scanner;

//class represent basic training wit basic moves.
public class BasicTraining extends Training {

    private static Scanner scanner = new Scanner( System.in );
    private static String TRAINING_ID = "BASIC_TRAINING";


    public BasicTraining() {
        setTrainingId(TRAINING_ID);
        this.moves = PowerMoveRepository.getInstance().getBasicTrainingMoveList();
    }

    @Override
    public boolean getTrain(Player player) {

        System.out.println("Lets start training");
        System.out.println("So in this training you will going to master following moves.");
        printMoveList(moves);

        boolean allMovesMastered = true;
        for(PowerMove move: moves){
            boolean result = practiceMove(move);
            allMovesMastered = allMovesMastered && result;
        }

        //check if training completed
        if(allMovesMastered){
            isCompleted = true;
        }

        return isCompleted;
    }

    private boolean practiceMove(PowerMove move) {

        boolean moveToNext = false;
        while (!moveToNext){
            System.out.println(move.getMoveId());
            System.out.println(move.getMoveDescription());
            System.out.println("Press [" + move.getMoveSymbol() + "] to master this move ");
            String action =  scanner.nextLine();


            if(action.equalsIgnoreCase(move.getMoveSymbol())) {
                System.out.println("Congrats!! you have mastered this move");
                move.setMoveMastered(true);
                moveToNext = true;
            }else {
                moveToNext = false;
                System.out.println("Don't worry try again.");
            }
        }

        return moveToNext;
    }

    private void printMoveList(List<PowerMove> moveList) {
        int i = 1;
        for(PowerMove move: moveList){
            System.out.println(i++ + ". " + move.getMoveDescription() + ".");
        }
        System.out.println();

    }

    @Override
    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }
}
