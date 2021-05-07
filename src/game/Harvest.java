package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;



public class Harvest extends Action {
    /**
     * harvestable variable is used to see if player can pick up a fruit
     */
    private Harvestable harvestable;

    /**
     * harvestable constructor creates a harvest action
     * @param harvestable is a harvestable being harvest
     */
    public Harvest(Harvestable harvestable) {
        this.harvestable = harvestable;
    }

    /**
     * execute method makes the player pick up a fruit
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return harvestable.harvest(actor, map.locationOf(actor));
    }

    /**
     * returns a string saying actor has harvest a fruit
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests " + harvestable.getClass().getSimpleName();
    }
}

