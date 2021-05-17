package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuittingGame extends Action{


    @Override
    public String execute(Actor actor, GameMap map) {
        //Cal function in new world passing value to exit
        return "Game Finished";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Exit";
    }
    @Override
    public String hotkey(){
        return "x";
    }
}
