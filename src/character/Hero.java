package character;

import player.SuperPower;
import training.Training;
import training.TrainingRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Class represent Hero ans its characteristics
// strength - Increase and decrease on each win or loose in fight.
//          - Decide damage level of attacks.
//          - Hero level depends on it.
public class Hero implements Serializable {

    private static final int BASIC_TRAINING_COMPLETE_STRENGTH = 5;

    private String name;
    private String realName;
    private String Gender;
    private String punchLine;
    private Float height;
    private int Weight;
    private int age;
    private int strength;
    private int level;
    private boolean trainingCompleted;
    private SuperPower[] superPowers;
    private List<Training> essentialTrainings = new ArrayList<>();


    //constructor
    private Hero(HeroBuilder heroBuilder){
        this.name = heroBuilder.name;
        this.realName = heroBuilder.realName;
        this.Gender = heroBuilder.Gender;
        this.punchLine = heroBuilder.punchLine;
        this.height = heroBuilder.height;
        this.Weight = heroBuilder.Weight;
        this.age = heroBuilder.age;
        this.superPowers = heroBuilder.superPowers;
        //add basic training
        this.essentialTrainings.add(TrainingRepository.getInstance().getBasicTraining());
        this.essentialTrainings.addAll(heroBuilder.trainings);
        trainingCompleted = false;
        updateLevel();

    }



    //getters and setters
    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
        updateLevel();
    }

    public void subtractStrength(int strength) {
        this.strength = this.strength - strength;
        updateLevel();
    }

    public void addStrength(int strength) {
        this.strength =  this.strength + strength;
        updateLevel();
    }

    public boolean isTrainingCompleted() {
        return trainingCompleted;
    }

    public void setTrainingCompleted(boolean trainingCompleted) {
        this.trainingCompleted = trainingCompleted;
        if(strength < BASIC_TRAINING_COMPLETE_STRENGTH){
            setStrength(BASIC_TRAINING_COMPLETE_STRENGTH);
        }

    }

    //update user level depends on its strength
    private void updateLevel(){
        if(strength >= 0 && strength <= 10){
            level = CharacterLevel.LEVEL_BASIC;
        }else if(strength > 10 && strength <= 30){
            level =  CharacterLevel.LEVEL_MASTER;
        }else {
            level =  CharacterLevel.LEVEL_PRO;
        }
    }

    public List<Training> getEssentialTrainings() {
        return essentialTrainings;
    }

    public void addTraining(Training training) {
        this.essentialTrainings.add(training);
    }

    public int getTrainingCount() {
        return essentialTrainings.size();
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getGender() {
        return Gender;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public Float getHeight() {
        return height;
    }

    public int getWeight() {
        return Weight;
    }

    public int getAge() {
        return age;
    }

    public SuperPower[] getSuperPowers() {
        return superPowers;
    }



    @Override
    public String toString() {
        return "character.Hero{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", punchLine='" + punchLine + '\'' +
                ", height=" + height +
                ", Weight=" + Weight +
                ", age=" + age;
    }

    //builder class to create new Hero
    public static class HeroBuilder {

        private final String DEFAULT_PUNCH_LINE = "SAVE THE WORLD";

        private String name;
        private String realName;
        private String Gender;
        private String punchLine;
        private Float height;
        private int Weight;
        private int age;
        private SuperPower[] superPowers;
        private List<Training> trainings = new ArrayList<>();

        //constructors
        public HeroBuilder(String name){
            this.name = name;
        }
        public HeroBuilder(){}


        public Hero build(){
            return new Hero(this);
        }

        public HeroBuilder setRealName(String realname){
            this.realName = realname;
            return this;
        }

        public HeroBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public HeroBuilder setGender(String gender) {
            Gender = gender;
            return this;
        }

        public HeroBuilder setPunchLine(String punchLine) {

            if(punchLine.isEmpty()){
                punchLine = DEFAULT_PUNCH_LINE;
            }
            this.punchLine = punchLine;
            return this;
        }

        public HeroBuilder setHeight(Float height) {
            this.height = height;
            return this;
        }

        public HeroBuilder setWeight(int weight) {
            Weight = weight;
            return this;
        }

        public HeroBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public HeroBuilder setSuperPowers(SuperPower[] superPowers) {
            this.superPowers = superPowers;
            return this;
        }

        public HeroBuilder addTrainings(List<Training> trainings) {
            if(trainings != null && trainings.size() > 0)
            this.trainings.addAll(trainings);
            return this;
        }
    }


}
