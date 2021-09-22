package game.World;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;
import game.Action.QuittingGame;

import java.util.ArrayList;

/**
 * THis is the sandbox world
 */
public class Sandbox extends World {

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public Sandbox(Display display) {
        super(display);



    }

    /**
     * CHecks if qutting game is done
     * @return false or keep running
     */
    @Override
    protected boolean stillRunning(){

        if (QuittingGame.boolenEnd){
            return false;
        }

        return super.stillRunning();
    }
}
