package game;

import edu.monash.fit2099.engine.*;

/**
 * This is the death action when a dinsour dies
 */
public class Death  extends Action {
    /**
     * THis is the death constructor
     */
    public Death() {

    }

    /**
     * This finds the location of the actor and adds a new corpse to the location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Item corpse = new Corpse(actor.getDisplayChar());

        map.locationOf(actor).addItem(corpse);

        map.removeActor(actor);

        return actor + " died at " + location.x() + "," + location.y();
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
