package character;

//class represent enemy
public class Enemy {

    private String name;
    private String realName;
    private String Gender;
    private String punchLine;
    private Float height;
    private int Weight;
    private int age;
    private int strength;
    private int level;


    //constructor
    private Enemy(EnemyBuilder enemyBuilder){
        this.name = enemyBuilder.name;
        this.realName = enemyBuilder.realName;
        this.Gender = enemyBuilder.Gender;
        this.punchLine = enemyBuilder.punchLine;
        this.height = enemyBuilder.height;
        this.Weight = enemyBuilder.Weight;
        this.age = enemyBuilder.age;
        this.strength = enemyBuilder.strength;
        updateLevel();

    }

    //getters and setters
    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        updateLevel();
    }


    private void updateLevel(){
        if(strength > 0 && strength <= 20){
            level = CharacterLevel.LEVEL_BASIC;
        }else if(strength > 20 && strength <= 50){
            level = CharacterLevel.LEVEL_MASTER;
        }else {
            level = CharacterLevel.LEVEL_PRO;
        }
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





    @Override
    public String toString() {
        return "character.Enemy{" +
                "name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", punchLine='" + punchLine + '\'' +
                ", height=" + height +
                ", Weight=" + Weight +
                ", age=" + age +
                '}';
    }

    //builder class to create Enemy
    public static class EnemyBuilder {

        private final String DEFAULT_PUNCH_LINE = "DESTROY THE WORLD";

        private String name;
        private String realName;
        private String Gender;
        private String punchLine;
        private Float height;
        private int Weight;
        private int age;
        private int strength;


        //constructors
        EnemyBuilder(String name){
            this.name = name;
        }
        public EnemyBuilder(){}


        public Enemy build(){
            return new Enemy(this);
        }

        public EnemyBuilder setRealName(String realname){
            this.realName = realname;
            return this;
        }

        public EnemyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EnemyBuilder setGender(String gender) {
            Gender = gender;
            return this;
        }

        public EnemyBuilder setPunchLine(String punchLine) {

            if(punchLine.isEmpty()){
                punchLine = DEFAULT_PUNCH_LINE;
            }
            this.punchLine = punchLine;
            return this;
        }

        public EnemyBuilder setHeight(Float height) {
            this.height = height;
            return this;
        }

        public EnemyBuilder setWeight(int weight) {
            Weight = weight;
            return this;
        }

        public EnemyBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public EnemyBuilder setStrength(int strength) {
            this.strength = strength;
            return this;
        }
    }


}