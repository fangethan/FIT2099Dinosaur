package game;

import edu.monash.fit2099.engine.*;

public class Death  extends Action {

    public Death() {

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Item corpse = new Corpse();

        map.locationOf(actor).addItem(corpse);

        map.removeActor(actor);

        return actor + " died at " + location.x() + "," + location.y();
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
