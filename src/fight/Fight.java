package fight;


import character.Enemy;
import character.EnemyRepository;
import character.Hero;
import io_helper.OutputHelper;
import player.Player;
import power_moves.PowerMove;
import power_moves.PowerMoveRepository;

import java.util.*;
import java.util.stream.Collectors;

// Class represent a fight between Hero nad Enemy.
public class Fight {

    Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final String BLOCK_MOVE_ID = PowerMoveRepository.getInstance().getBlock().getMoveId();
    private Hero hero;
    private Enemy enemy;
    private int heroLife;
    private int enemyLife;
    private int enemyLastMoveEffect;
    private int heroLastMoveEffect;
    private int heroContinuousBlock;
    private int enemyContinuousBlock;
    private Object activeCharacter;
    private List<PowerMove> enemyMovesList;
    private List<PowerMove> heroMovesList;
    private static final int MAX_BLOCK_LIMIT = 2;
    private static final int USER_INPUT_TIME_WINDOW = 3000;
    private String userInput = "";

    //constructor
    public Fight(Player userPlayer) {
        hero = userPlayer.getHero();
        heroLife = 100;
        enemyLife = 100;
        enemyLastMoveEffect = 0;
        heroLastMoveEffect = 0;
        heroContinuousBlock = 0;
        enemyContinuousBlock = 0;
        enemyMovesList = PowerMoveRepository.getInstance().getAvailablePowerMoveList();
        heroMovesList = hero.getEssentialTrainings().stream()
                .flatMap(t -> t.moves.stream())
                .collect(Collectors.toList());
        chooseEnemy(hero);
    }

    /* Method choose enemy according to hero level
    *  parameters : hero - hero selected by user
    *
    * */
    private void chooseEnemy(Hero hero) {
        int level = hero.getLevel();
        List<Enemy> availableEnemies = EnemyRepository.getInstance().getEnemyByLevel(level);

        //choose random enemy
        if(availableEnemies!= null && availableEnemies.size() > 0){
            enemy = availableEnemies.get(random.nextInt(availableEnemies.size()));
        }

        //show enemy details to user
        OutputHelper.showEnemyDetails(enemy);
    }

    /* Start fight.
    *  - show fight instructions.
    *  - perform toss.
    *  - manage current attacker(at a time only one Hero/Enemy can attack).
    *  - update Hero strength according to fight result.
    *
    * */
    public boolean startFight() {
        //show fight rules
        OutputHelper.showFightInstructions();

        //toss to choose who will attack first
        if(OutputHelper.performToss()){
            System.out.println();
            System.out.println("Yes, You won the toss and get chance to hit first\n\n");
            activeCharacter = hero;
        }else {
            System.out.println("You loose the toss and enemy get chance to hit first\n\n");
            activeCharacter = enemy;
        }

        //start fight
        if(activeCharacter instanceof  Enemy){

            while (heroLife > 0 && enemyLife > 0){
                enemyPerformHit();
                heroPerformHit();
                System.out.println("Hero life : " + (heroLife > 0 ? heroLife : 0) + " Enemy life : " + (enemyLife > 0 ? enemyLife : 0));
                System.out.println();
            }
        }else {
            while (heroLife > 0 && enemyLife > 0){
                heroPerformHit();
                enemyPerformHit();
                System.out.println("Hero life : " + (heroLife > 0 ? heroLife : 0) + " Enemy life : " + (enemyLife > 0 ? enemyLife : 0));
                System.out.println();
            }
        }


        // decide winner
        Object winner = null;
        if(heroLife > enemyLife){
            winner = hero;
            //increase hero strength
            hero.addStrength(5); //5 is basic strength increment value
            System.out.println("\nWINNER, yes, You won :)");
            return  true;
        }else {
            winner = enemy;
            //decrease hero strength
            hero.subtractStrength(5); //5 is basic strength increment value
            System.out.println("\nNever mind, you loose. Try hard again");
            return  false;
        }
    }

    //perform enemy attack and calculate enemy life depends on damage by user.
    private void enemyPerformHit() {
        PowerMove move;
        if (activeCharacter instanceof Enemy) {

            //choose random moves from all available moves
            int i = random.nextInt(enemyMovesList.size());
            move = enemyMovesList.get(i);
            System.out.println(move.getMoveSymbol());

            //if block then no damage
            if (Objects.equals(move.getMoveId(), BLOCK_MOVE_ID) && enemyContinuousBlock < MAX_BLOCK_LIMIT) {
                heroLastMoveEffect = 0;
                enemyLastMoveEffect = 0;
                enemyLife -= heroLastMoveEffect;
                enemyContinuousBlock++;

            } else {
                //update Hero's life
                enemyLastMoveEffect = getTotalDamage(move.getMoveDamageLevel(), enemy.getStrength());
                enemyLife -= heroLastMoveEffect;
                enemyContinuousBlock = 0;
            }

            //change activeCharacter
            activeCharacter = hero;
        }
    }

    //perform enemy attack and calculate enemy life depends on damage by enemy.
    // ask Hero for his attacking move.
    // only moves that are mastered in training are valid.
    // delay in attack(> 3 sec.) cause damage to Hero.
    private void heroPerformHit() {
        PowerMove move = null;

        Timer timer = new Timer();
        TimerTask inputTImeWarningTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("You are taking too much time to hit. it will reduce your health.");
                heroLife -= 5;
            }
        };
        timer.schedule(inputTImeWarningTask, USER_INPUT_TIME_WINDOW, USER_INPUT_TIME_WINDOW);

        String choice = "";
        while (true){
            //prompt user for move
            System.out.println("Your move: ");
            userInput = scanner.nextLine();

            //check if move is valid.
            move = heroMovesList.stream()
                    .filter(m -> m.getMoveSymbol().equalsIgnoreCase(userInput))
                    .findFirst()
                    .orElse(null);

            if(move == null){
                OutputHelper.showError("No such move exist. try another one hurry!!");
            }else {
                //valid move
                timer.cancel();
                break;
            }
        }

        //Block can save user from attack(No damage) but more than 2 block consecutive block not effective.
        if(Objects.equals(move.getMoveId(), BLOCK_MOVE_ID) && heroContinuousBlock < MAX_BLOCK_LIMIT){
            enemyLastMoveEffect = 0;
            heroLastMoveEffect = 0;
            heroLife -= enemyLastMoveEffect;
            heroContinuousBlock++;
            if(heroContinuousBlock == MAX_BLOCK_LIMIT){
                System.out.println("Next continuous block will cause you damage");
            }
        }else {
            //update Hero's life
            heroLastMoveEffect = getTotalDamage(move.getMoveDamageLevel(), hero.getStrength());
            heroLife -= enemyLastMoveEffect;
            heroContinuousBlock = 0;
        }
        activeCharacter = enemy;
    }

    //calculate damage by attack depends on move and attacker strength.
    private int getTotalDamage(int moveDamageLevel, int strength) {
        return moveDamageLevel + strength * moveDamageLevel / 10;
    }

}
