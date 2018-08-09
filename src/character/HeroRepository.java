package character;


import player.SuperPower;
import training.TrainingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* Repository for heroes
 - Contains predefined heroes(Hero DB) and their getter
 - Singleton class
* */
public class HeroRepository {

    private static HeroRepository INSTANCE;

    //factory method to get instance of Repository
    public static HeroRepository getInstance(){
        if(null == INSTANCE){
            INSTANCE = new HeroRepository();
        }

        return INSTANCE;
    }

    private List<Hero> activeHeroes = new ArrayList<>();
    private List<String> activeHeroesNames = new ArrayList<>();

    //private constructor
    private HeroRepository() {
        this.activeHeroesNames.add("Hulk");
        this.activeHeroesNames.add("Iron Man");
        this.activeHeroesNames.add("Captain America");
        this.activeHeroesNames.add("Thor");
        this.activeHeroesNames.add("Black Widow");

    }

    //method to add new hero
    public void addHero(Hero hero){
        if(null != hero){
            activeHeroesNames.add(hero.getName());
            this.activeHeroes.add(hero);
        }
    }


    //method to get heroes lists
    public List<Hero> getActiveHeroes() {
        return activeHeroes;
    }

    //method to get heroes name lists
    public List<String> getActiveHeroesNames() {
        return activeHeroesNames;
    }


    //method to get hero by name
    public Hero getHeroByName(String name){
        switch (name){
            case "Hulk":
                return getHulk();

            case "Iron Man":
                return getIronMan();

            case "Captain America":
                return getCaptainAmerica();

            case "Thor":
                return getThor();

            case "Black Widow":
                return getBlackWidow();

        }
        return null;
    }

    private Hero getThor() {
        return new Hero.HeroBuilder("Thor")
                .setAge(150)
                .setGender("M")
                .setHeight(5.9F)
                .setWeight(120)
                .setRealName("Thor")
                .setPunchLine("I am God of Thunder")
                .setSuperPowers(new SuperPower[]{new SuperPower("HAMMER", SuperPower.PowerCategory.HIGH), new SuperPower("THUNDER", SuperPower.PowerCategory.HIGH) } )
                .build();
    }

    private Hero getBlackWidow() {
        return new Hero.HeroBuilder("Black Widow")
                .setAge(35)
                .setGender("F")
                .setHeight(5.4F)
                .setWeight(60)
                .setRealName("Natasha Romanoff")
                .setPunchLine("I will kill you.")
                .setSuperPowers(new SuperPower[]{new SuperPower("HAND_COMBAT", SuperPower.PowerCategory.LOW)} )
                .build();
    }

    private Hero getHulk() {
        return new Hero.HeroBuilder("Hulk")
                .setAge(45)
                .setGender("M")
                .setHeight(16.2F)
                .setWeight(500)
                .setRealName("Bruce Banner")
                .setPunchLine("OOOAAAAAAA")
                .setSuperPowers(new SuperPower[]{new SuperPower("HULK_SMASH", SuperPower.PowerCategory.HIGH), new SuperPower("SUPER_STRENGTH", SuperPower.PowerCategory.MEDIUM) } )
                .build();
    }

    private Hero getIronMan() {
        return new Hero.HeroBuilder("Iron Man")
                .setAge(45)
                .setGender("M")
                .setHeight(5.9F)
                .setWeight(68)
                .setRealName("Tony Stark")
                .setPunchLine("I am genius and rich too.")
                .setSuperPowers(new SuperPower[]{new SuperPower("IRON_SUIT", SuperPower.PowerCategory.HIGH), new SuperPower("WEALTH", SuperPower.PowerCategory.HIGH) } )
                .addTrainings(TrainingRepository.getInstance().getIronManTrainings())
                .build();
    }

    private Hero getCaptainAmerica() {
        return new Hero.HeroBuilder("Captain America")
                .setAge(100)
                .setGender("M")
                .setHeight(6.2F)
                .setWeight(88)
                .setRealName("Steve Rogers")
                .setPunchLine("Avengers Assemble!!")
                .setSuperPowers(new SuperPower[]{new SuperPower("MARTIAL_ART", SuperPower.PowerCategory.MEDIUM), new SuperPower("SHIELD", SuperPower.PowerCategory.HIGH) } )
                .build();
    }
}

