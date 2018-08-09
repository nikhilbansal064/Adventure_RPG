package utils;

import constants.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

//class for utility methods
public class Utils {

    public static boolean isValidInput(String[] validInputs, String userInput){

        if(null != userInput && null != validInputs && validInputs.length > 0){
            return Arrays.asList(validInputs).contains(userInput);
        }else {
            return false;
        }
    }


    public static boolean isValidInput(int start, int end, String userInput){

        int choice;
        if(null != userInput && !userInput.isEmpty()){
            try {
                choice = Integer.parseInt(userInput);
            }catch (NumberFormatException e){
                return false;
            }

            if(choice >= start && choice <= end){
                return true;
            }
        }
        return false;
    }



    public static void createPlayerDir() {
        //create a dir to save player game.
        String fileName = Constants.SAVED_PLAYER_DIR_NAME;
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
