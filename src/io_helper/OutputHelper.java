package io_helper;

import character.Enemy;
import character.Hero;
import player.PlayerState;
import player.SuperPower;
import training.Training;
import utils.Utils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

//IOHelper class mainly used to prompt user and take input
public class OutputHelper {

    private static final Random random = new Random();
    private static Scanner scanner = new Scanner( System.in );
    private static String[] invalidInputErrors = new String[]{"Please Enter valid Choice ",
                            "No, No messing around, be good and try again",
                            "This behaviour is not acceptable, Come again with right choice",
                            "The whole world is counting on you, save the world with valid option",
                            "You are a hero, don't be bad by giving wrong choices",
                            "Wrong choices can cost a lot, Don't do it again."};

    public static void showBackStory() {
        System.out.println("!!! Welcome to Infinity War !!!\n");
        System.out.println("In previous episode...");
        System.out.println("See if you wanna skip the fun part and start the game directly, you can skip");

        boolean skip;
        String questionPrompt = "Do you wanna skip the story or continue?";
        String[] options = new String[] {"1. Skip","2. Continue"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Say it : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        skip = choice.equalsIgnoreCase("1");

        if(!skip){
            System.out.println("\nSo, The whole universe is at stake coz the mightiest villain ever has " +
                    "been arrived ... THANOS - the great titan.");
            scanner.nextLine();
            System.out.println("\nThanos alone defeated the avengers (smashed them in an way) and acquired all the Infinity Stones");
            scanner.nextLine();
            System.out.println("Then he did what he wanted to do with just one SNAP(assume the sound effect yourself, We are on CLI)");
            scanner.nextLine();
            System.out.println("So in short, remaining avengers are very tensed and are looking for a recruit.\nNOTE - There will be a interview of course :P");

        }else {
            System.out.println("You seems to be in hurry. Its ok. Lest start.");
        }

        scanner.nextLine();
        System.out.println("By the way I am JARVIS, naam to suna hi hoga.");
    }

    public static boolean askToStartGame(){
        boolean startGame;

        String questionPrompt = "So are you ready to SAVE the WORLD - ";
        String[] options = new String[] {"1.Continue","2.Quit"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Your choice : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        startGame = choice.equalsIgnoreCase("1");

        return startGame;
    }

    public static void sayGoodBye() {
        System.out.print("I don't want to let you go but anyways Good Bye. Have a good Day or Night :P");
    }


    private static String showOptions(String questionPrompt, String[] options, String answerPrompt, String[] validInputOptions){
        String choice;
        StringBuilder optionStrBuilder = new StringBuilder("\n" + questionPrompt + "\n");
        for (String str: options){
            optionStrBuilder.append(str).append("\n");
        }
        optionStrBuilder.append(answerPrompt);
        String optionStr = optionStrBuilder.toString();

        while (true){
            System.out.print(optionStr);
            choice = scanner.next();
            //validate input
            if(Utils.isValidInput(validInputOptions, choice)){
                break;
            }else {
                System.out.println(invalidInputErrors[random.nextInt(invalidInputErrors.length)]);
            }
        }

        return choice;
    }

    public static int askToChoosePlayer() {

        String questionPrompt = "Do you play as new player or existing saved player?";
        String[] options = new String[] {"1. New Player","2. Existing Player"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Answer : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }

    public static String askUserForPlayerName() {
        System.out.println("\nSo what is awesome name of your new player? :");
        return scanner.next();
    }

    public static void showError(String error) {
        System.err.println(error);
    }

    public static int showMainMenu(int playerState) {

        String questionPrompt = "\nMain Menu";
        String createCharacterOptionsStr;
        if(playerState < PlayerState.PLAYER_STATE_CHARACTER_SELECTED){
            createCharacterOptionsStr = "1. Create or choose super hero";
        }else {
            createCharacterOptionsStr = "1. Change super hero";
        }
        String[] options = new String[] {createCharacterOptionsStr,"2. Training", "3. Fight", "4. Save and Quit"};
        String[] validInputOptions = new String[] {"1","2", "3", "4"};
        String answerPrompt = "What you want to do? : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }

    public static int askPlayerToChooseSuperHero() {

        String questionPrompt = "Hmm... in this case,  you have two choices - ";
        String[] options = new String[] {"1. Choose existing superhero","2. Create new superHero", "3. Main menu"};
        String[] validInputOptions = new String[] {"1","2", "3"};
        String answerPrompt = "Choose : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);

    }

    public static String askUserToChooseHero(List<String> existingHeroesNames) {
        int choiceIndex;
        while (true){
            //prompt existing heroes list
            showHeroesList(existingHeroesNames);
            String choice = scanner.next();
            if(Utils.isValidInput(1, existingHeroesNames.size(), choice)){
                choiceIndex = Integer.parseInt(choice);
                break;
            }else {
                //not a valid response . prompt again
                System.out.println("Don't you wanna be Hero? choose again.");
            }
        }

        return existingHeroesNames.get(choiceIndex - 1);
    }


    private static void showHeroesList(List<String> existingHeroesNames) {
        System.out.println("\nHere is list of great Heroes for you. Choose wisely");
        int i = 1;
        for(String heroName : existingHeroesNames){
            System.out.println(i + "." + heroName);
            i++;
        }
        System.out.println("Answer");
    }

    public static void showHeroDetails(Hero heroChosen) {
        System.out.println("\n");
        System.out.println(heroChosen.getName().toUpperCase());
        System.out.println("Strength - " + heroChosen.getStrength());
        System.out.println("Level - " + heroChosen.getLevel());
        StringBuilder powers = new StringBuilder("Powers - ");
        for(SuperPower power: heroChosen.getSuperPowers()){
            powers.append(power.getName()).append(",");
        }
        System.out.println(powers);
        System.out.println("\n");
    }

    public static Hero.HeroBuilder getInfoofNewSuperHero() {
        Hero.HeroBuilder builder;

        System.out.println("Lets get some details for your hero.");

        System.out.print("What is hero your name? : ");
        String name = scanner.nextLine();
        builder = new Hero.HeroBuilder(name);

        System.out.print("What is your real name? : ");
        String realName = scanner.nextLine();
        builder.setRealName(realName);

        System.out.print("What is your Gender ? [M/F] : ");
        String gender = scanner.nextLine();
        builder.setGender(gender);

        System.out.print("What is your height? : ");
        String height = scanner.nextLine();
        builder.setHeight(Float.parseFloat(height));

        System.out.print("What is your wight? : ");
        String weight = scanner.nextLine();
        builder.setAge(Integer.parseInt(weight));

        System.out.print("What is your punch line? : ");
        String punchLine = scanner.nextLine();
        builder.setPunchLine(punchLine);

        System.out.print("What are your super power name? : ");
        String spName = scanner.nextLine();

        System.out.print("What are your super power level? [L/M/H]: ");
        String spLevel = scanner.nextLine();

        SuperPower sp = new SuperPower(spName, SuperPower.PowerCategory.HIGH);
        builder.setSuperPowers(new SuperPower[]{sp});

        return builder;
    }

    public static int askToTrainHero() {

        String questionPrompt = "Are you ready for training (You must be, Good for battle) ?";
        String[] options = new String[] {"1. I am born ready. Let's do the training ","2. Main menu "};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Answer : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);

    }

    public static void showEssentialTrainingList(List<Training> essentialTrainings) {

        System.out.println("Nice choice my friend...");
        System.out.println("Great Heroes comes with great responsibilities :P\nSo you have to train hard.");

        //show all required trainings
        for(Training t: essentialTrainings){
            System.out.println(t.trainingId);
        }
    }

    public static int askForBattle() {

        String questionPrompt = "Good job. You have completed the training, Now you are ready for battle";
        String[] options = new String[] {"1. Start Battle","2. Main menu"};
        String[] validInputOptions = new String[] {"1","2"};
        String answerPrompt = "Your Choice : ";
        String choice = showOptions(questionPrompt, options, answerPrompt, validInputOptions);

        return Integer.parseInt(choice);
    }

    public static void showEnemyDetails(Enemy enemy) {
        System.out.println("\n");
        System.out.println(enemy.getName().toUpperCase());
        System.out.println("Strength - " + enemy.getStrength());
        System.out.println("Level - " + enemy.getLevel());
        System.out.println("\n");
    }

    public static void showFightInstructions() {
        System.out.println("So, let me tell you some rules first.");
        System.out.println(" - Both (Hero/Ememy) will get 100 initial health.");
        System.out.println(" - The one whose health will reduced to zero first will loose.");
        System.out.println(" - This is one to one combat, i.e each player will attack one by one like");
        System.out.println(" - You are only have the moves that you mastered in your training(That's why trainings are good)");
        System.out.println(" - Damage will depends on your choice of move and your strength( choose your move wisely)");
        System.out.println(" - Damage will depends on your choice of move and your strength( choose your move wisely)");
        System.out.println(" - Block move is defence move. It will save you from damage.");
        System.out.println(" - Only Continuous 2 Blocks are effective, after that you will get harmed");
        System.out.println(" - You have window of 3 seconds to attack, after that your life will start to reduce.");
        System.out.println(" - Most important, Read all rules carefully :P\n");
    }

    public static boolean performToss() {
        String tossChoice;

        while(true){
            System.out.println("Heads[H] or Tails[T]:");
            tossChoice = scanner.nextLine();

            if(Utils.isValidInput(new String[]{"h", "t"}, tossChoice.toLowerCase())){
                break;
            }else {
                System.out.println("How could you do that, come again.");
            }
        }

        return random.nextBoolean();
    }
}
