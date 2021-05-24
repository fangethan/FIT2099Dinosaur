package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the rainAction class which intiates rain
 * It also makes the dinasours conscious if it rains
 */
public class RainAction {
    private final GameMap map;

    public RainAction(GameMap gamemap) {
        this.map = gamemap;
    }

    /**
     * This class checks all the dinsours and if there water level is 0, it makes it 10
     */
    public void rain() {
        Map<Actor, Location> dinosaursList = new HashMap<>();
        dinosaursList = getAllActors(map);

        for (Map.Entry<Actor, Location> spot : dinosaursList.entrySet()) {
            Actor actor = spot.getValue().getActor();

            if (actor instanceof Dinosaur) {
                if (((Dinosaur) actor).getWaterLevels() == 0) {
                    ((Dinosaur) actor).setWaterLevels(10);

                }
            }

        }
    }

    /**
     * Gets all the actors on the map
     * @param gameMap
     * @return list of all actors
     */
    public Map<Actor, Location> getAllActors(GameMap gameMap) {

        Map<Actor, Location> dinosaursList = new HashMap<>();
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                Actor actor = gameMap.getActorAt(location);
                if (location.containsAnActor()) {
                    dinosaursList.put(actor,location);
                }
            }
        }
        return dinosaursList;
    }
}

