package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public interface Harvestable {
    String harvest(Actor actor, Location location);
}
