package training;

import java.util.ArrayList;
import java.util.List;

// repository class for trainings.
public class TrainingRepository {

    private static TrainingRepository INSTANCE;

    //factory method to get instance of Repository
    public static TrainingRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new TrainingRepository();
        }

        return INSTANCE;
    }

    //private constructors for singleton pattern
    private TrainingRepository(){}


    public FlyTraining getFlyTraining(){
        return new FlyTraining();
    }

    public BasicTraining getBasicTraining(){
        return new BasicTraining();
    }

    public List<Training> getIronManTrainings(){
        List trainings = new ArrayList<Training>();
        trainings.add(new FlyTraining());

        return trainings;
    }
}

