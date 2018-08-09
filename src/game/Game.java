package game;

import character.Hero;
import character.HeroRepository;
import constants.Constants;
import fight.Fight;
import io_helper.OutputHelper;
import player.Player;
import player.PlayerState;
import training.Training;
import utils.Utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/* Game class
 - Class represent a game and control game flow.
* */
public class  Game {

    private Player userPlayer;

    //constructor
    Game() {
        initGame();
    }

    //method to initialise variable necessary for game
    private void initGame() {
        //create player directory if not already exist
        Utils.createPlayerDir();
    }

    //method to clean up when game finished and close application.
    private void finishGame() {
        //save game and exit
        saveGame(userPlayer);
        OutputHelper.sayGoodBye();
        System.exit(0);
    }


    // method to start game
    public void start(){

        //menu to prompt user to choose existing saved player or new player
        showChoosePlayerMenu();

        //show main menu
        showMainMenu();
    }


    private void showChoosePlayerMenu() {

        int choice = OutputHelper.askToChoosePlayer();
        createOrChoosePlayer(choice);
    }

    //create or choose existing player according to user choice.
    private void createOrChoosePlayer(int choice) {
        switch (choice){
            case 1:
                //create new player
                createNewPlayer();
                break;

            case 2:
                // get player from saved player
                chooseExistingPlayer();
                break;


        }
    }


    private void chooseExistingPlayer() {

        String playerName = OutputHelper.askUserForPlayerName();
        File playerFile = checkIfPlayerAlreadyExist(playerName + Constants.SERIALISED_PLAYER_FILE_TYPE);

        if(null != playerFile){
            //player exist
            retrieveSavedPlayer(playerFile);

        }else {
            OutputHelper.showError("No such player exist");
            showChoosePlayerMenu();
        }
    }


    /* Retrieve player saved info
    *  parameters : playerFile(File) - saved file of player
    */
    private void retrieveSavedPlayer(File playerFile) {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(playerFile);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            userPlayer = (Player) objectInputStream.readObject();

            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Check if player with @playerName is already saved
    *   parameters : playerName - name provided by user
    *   return : File - if player saved
    *            null - if no player saved with give name
    * */

    private File checkIfPlayerAlreadyExist(String playerName) {
        //find player in existing player list
        File dir = new File(Constants.SAVED_PLAYER_DIR_NAME);
        File[] playerList = dir.listFiles();

        //extract player
        if (playerList != null) {
            return Arrays.stream(playerList)
                    .filter(file -> playerName.equalsIgnoreCase(file.getName()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    //method to create new player with name asked form user
    private void createNewPlayer() {

        String playerName = OutputHelper.askUserForPlayerName();
        File playerFile = checkIfPlayerAlreadyExist(playerName);

        if(playerFile != null){
            //already exist player with name
            OutputHelper.showError("Player already exist with this name");
            showChoosePlayerMenu();

        }else {
            //create new player with given name
            userPlayer = new Player(playerName);
        }
    }

    //Show main menu to user -
    // Create or choose Hero
    // Train Hero
    // Fight
    // Save and quit
    private void showMainMenu() {

        int choice = OutputHelper.showMainMenu(userPlayer.getPlayerState());
        checkUserChoiceWithGameState(choice);
    }

    /* Check user choice and validate is player can perform chosen tasks and perform task according to choice.
    *  parameters : choice - user choice form main menu
    *
    * */
    private void checkUserChoiceWithGameState(int choice) {
        if(choice == 4){
            //save player and quit.
            finishGame();

        }else {
            //user want to create/choose character or train or fight
            if(choice <= userPlayer.getPlayerState()){
                //user chose allowable action according to state.
                switch (choice){
                    case 1:
                        createCharacter();
                        break;

                    case 2:
                        train();
                        break;

                    case 3:
                        fight();
                        break;
                }

            }else {
                //user choice is not allowed by game state
                switch (userPlayer.getPlayerState()){
                    case PlayerState.PLAYER_STATE_PLAYER_SELECTED:
                        //Player can't train or fight without Hero.
                        OutputHelper.showError("Please choose Hero first");
                        break;

                    case PlayerState.PLAYER_STATE_CHARACTER_SELECTED:
                        //Player can't fight without training.
                        OutputHelper.showError("Please complete the training first");
                        break;
                }

                showMainMenu();
            }
        }
    }

    private void saveGame(Player player) {
        String name = Constants.SAVED_PLAYER_DIR_NAME + "/" + player.getPlayerName() + Constants.SERIALISED_PLAYER_FILE_TYPE;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(name);
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    // create new Hero or choose existing hero for player
    private void createCharacter() {

        int choice = OutputHelper.askPlayerToChooseSuperHero();

        switch (choice){
            case 1:
                showExistingAvailableHeroes();
                showTrainingOption();
                break;

            case 2:
                createNewHero();
                showTrainingOption();
                break;

            case 3:
                showMainMenu();
                break;
        }
    }

    private void showTrainingOption() {
        int choice = OutputHelper.askToTrainHero();
        switch (choice){
            case 1:
                train();
                break;

            case 2:
                showMainMenu();
                break;
        }
    }

    //methods for fight
    private void fight() {
        System.out.println("Fight.");
        Fight fight = new Fight(userPlayer);
        boolean userWon = fight.startFight();

        if(userWon){
            System.out.println("Congrats, You won");
        }else {
            System.out.println("Try Again");
        }

        userPlayer.setPlayerState(PlayerState.PLAYER_STATE_AT_LEAST_ONE_FIGHT_DONE);
        //show main menu
        showMainMenu();
    }

    // method to train User.
    private void train() {

        OutputHelper.showEssentialTrainingList(userPlayer.getHero().getEssentialTrainings());

        //start trainings
        startHeroTraining(userPlayer.getHero().getEssentialTrainings());
        showBattleOption();
    }

    private void showBattleOption() {

        int choice = OutputHelper.askForBattle();


        switch (choice){
            case 1:
                //start battle process
                fight();
                break;

            case 2:
                showMainMenu();
                break;
        }

    }

    private void startHeroTraining(List<Training> essentialTrainings) {
        for(Training training: essentialTrainings){
            //train user
            training.getTrain(userPlayer);
        }
        userPlayer.getHero().setTrainingCompleted(true);
        userPlayer.setPlayerState(PlayerState.PLAYER_STATE_TRAINING_COMPLETED);
    }


    //create a new Hero by asking info from player
    private void createNewHero() {

        Hero.HeroBuilder builder = OutputHelper.getInfoofNewSuperHero();
        Hero newHero = builder.build();
        OutputHelper.showHeroDetails(newHero);
        //add hero to repository
        HeroRepository.getInstance().addHero(newHero);
        //assign hero to player
        userPlayer.setHero(newHero);
    }


    //show existing available heroes
    private void showExistingAvailableHeroes() {

        Hero heroChosen;
        List<String> existingHeroesNames = HeroRepository.getInstance().getActiveHeroesNames();

        String choice = OutputHelper.askUserToChooseHero(existingHeroesNames);
        heroChosen = HeroRepository.getInstance().getHeroByName(choice);

        if(null == heroChosen){
            OutputHelper.showError("Something went wrong . Please try again.");
            showMainMenu();
        }else {
            //show chosen hero details
            OutputHelper.showHeroDetails(heroChosen);
        }
        //assign hero to player
        userPlayer.setHero(heroChosen);
    }

}
