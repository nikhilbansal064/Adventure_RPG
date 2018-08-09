package game;

import io_helper.OutputHelper;

/* Narrator class
 - Starting point of application.
 - Prompt Game story to User and ask to start Game.
*/
public class Narrator {

    public static void main(String[] arg){

        OutputHelper.showBackStory();
        boolean startGame = OutputHelper.askToStartGame();

        if(startGame){
            //start game
            Game game = new Game();
            game.start();
        }else {
            //close application.
            OutputHelper.sayGoodBye();
            System.exit(0);
        }

    }

}
