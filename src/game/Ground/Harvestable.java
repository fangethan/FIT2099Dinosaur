package game.Ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public interface Harvestable {
    /**
     * Shows the object has been harvested.
     * If harvesting yields an item, it is added to
     * the inventory
     * @param actor The actor that is doing the harvesting
     * @param location Location of the object being harvested.
     */
    String harvest(Actor actor, Location location);
}
