package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This is the quitting action for the user
 */
public class QuittingGame extends Action{

public static boolean boolenEnd = false;

    /**
     * This executes the quitting action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //Cal function in new world passing value to exit
        boolenEnd = true;
        return "Game Finished";
    }

    /**
     * This prints out the menu description
     * @param actor The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Exit";
    }

    /**
     * This returns the hotkey
     * @return e
     */
    @Override
    public String hotkey(){
        return "e";
    }
}
