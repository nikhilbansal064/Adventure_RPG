package player;

import character.Hero;

import java.io.Serializable;

//class represent a player
// Each player has to select a hero to r=train and fight.
// playerState - represent action allowed to player
//               - player has to select hero before training.
//               - player can only fight if he has hero and trained it.

public class Player implements Serializable {

    private static int PLAYERS_COUNT = 0;

    private int playerId = ++PLAYERS_COUNT;
    private String playerName;
    private Hero hero;
    private int playerState;

    public Player(String name){
        this.playerName = name;
        playerState = PlayerState.PLAYER_STATE_PLAYER_SELECTED;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        playerState = PlayerState.PLAYER_STATE_CHARACTER_SELECTED;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerState() {
        return playerState;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }
}
