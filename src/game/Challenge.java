package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

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
    @Override
    protected void processActorTurn(Actor actor){
        if (actor == player){
            turnsCurrently++;
        }
        super.processActorTurn(actor);
    }

}
