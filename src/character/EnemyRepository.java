package character;

import java.util.ArrayList;
import java.util.List;

/* Repository for enemies
 - Contains predefined enemies(Enemy DB) and their getter
 - Singleton class
* */
public class EnemyRepository {

    private static EnemyRepository INSTANCE;

    //factory method to get instance of Repository
    public static EnemyRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new EnemyRepository();
        }
        return INSTANCE;
    }


    private List<Enemy> activeEnemies = new ArrayList<>();
    private List<String> activeEnemiesNames = new ArrayList<>();

    //private constructor
    private EnemyRepository() {
        this.activeEnemiesNames.add("Hydra");
        this.activeEnemiesNames.add("Winter Soldier");
        this.activeEnemiesNames.add("Thanos");
        this.activeEnemiesNames.add("Ultron");
        this.activeEnemiesNames.add("Loki");
        this.activeEnemiesNames.add("Kill Monger");
    }


    public List<String> getActiveEnemiesNames() {
        return activeEnemiesNames;
    }

    public Enemy getKillMonger() {
        return new Enemy.EnemyBuilder("Kill Monger")
                .setAge(35)
                .setGender("M")
                .setHeight(5.8F)
                .setWeight(69)
                .setRealName("Erik")
                .setPunchLine("Wakanda is mine")
                .setStrength(15)
                .build();
    }

    public Enemy getUltron() {
        return new Enemy.EnemyBuilder("Ultron")
                .setAge(40)
                .setGender("M")
                .setHeight(5.8F)
                .setWeight(300)
                .setRealName("ultron")
                .setPunchLine("Kill Avengers")
                .setStrength(20)
                .build();
    }

    public Enemy getLoki() {
        return new Enemy.EnemyBuilder("Loki")
                .setAge(200)
                .setGender("M")
                .setHeight(5.8F)
                .setWeight(100)
                .setRealName("loki")
                .setPunchLine("I am king of Asgard")
                .setStrength(40)
                .build();
    }

    public Enemy getHydra() {
        return new Enemy.EnemyBuilder("Hydra")
                .setAge(200)
                .setGender("M")
                .setHeight(6.2F)
                .setWeight(0)
                .setRealName("hydra")
                .setPunchLine("Hail Hydra")
                .setStrength(25)
                .build();
    }

    private Enemy getWinterSoldier() {
        return  new Enemy.EnemyBuilder("Winter Soldier")
                .setAge(32)
                .setGender("M")
                .setHeight(6.0F)
                .setWeight(68)
                .setRealName("Bucky Barns")
                .setPunchLine("I forgot my punchline :P.")
                .setStrength(10)
                .build();
    }

    private Enemy getThanos() {
        return new Enemy.EnemyBuilder("Thanos")
                .setAge(500)
                .setGender("M")
                .setHeight(10.6F)
                .setWeight(923)
                .setRealName("Dione")
                .setStrength(70)
                .setPunchLine("I will kill half of the world.")
                .build();
    }

    //method to get character.Enemy by strength
    public List<Enemy>  getEnemyByLevel(int level){
        List<Enemy> enemies = new ArrayList<>();

        switch (level){
            case 1:
                enemies.add(getWinterSoldier());
                enemies.add(getUltron());
                enemies.add(getKillMonger());

            case 2:
                enemies.add(getHydra());
                enemies.add(getLoki());

            case 3:
                enemies.add(getThanos());

        }
        return enemies;
    }
}

