package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

/**
 * This is the challlenge world
 */
public class Challenge extends World {

    private final int turn;
    private final int points;
    int turnsCurrently=0;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public Challenge(Display display, int turn, int points) {
        super(display);
        this.turn = turn;
        this.points = points;


    }

    /**
     * THis checks if the end action is called or eco points are greater than the input
     * also if the turns are greater than the current number of turns
     * @return false or keep going
     */
    @Override
    protected boolean stillRunning(){
        Player player = (Player) this.player;

        if (QuittingGame.boolenEnd){
            return false;
        }
        if (player.getEcoPoints() >= points)
            return false;
        else if (turnsCurrently >= turn && player.getEcoPoints() < points){
            return false;
        }

        return super.stillRunning();
    }

    /**
     * This adds the turns
     * @param actor the Actor whose turn it is.
     */
    @Override
    protected void processActorTurn(Actor actor){
        if (actor == player){
            turnsCurrently++;
        }
        super.processActorTurn(actor);
    }

}
