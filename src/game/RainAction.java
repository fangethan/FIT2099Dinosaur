package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RainAction {
    private final GameMap map;

    public RainAction(GameMap gamemap) {
        this.map = gamemap;
    }

    public void rain() {
        Map<Actor, Location> dinosaursList = new HashMap<>();
        dinosaursList = getAllActors(map);

        for (Map.Entry<Actor, Location> spot : dinosaursList.entrySet()) {
            Actor actor = spot.getValue().getActor();

            if (actor instanceof Dinosaur) {
                if (((Dinosaur) actor).getWaterLevels() == 0) {
                    ((Dinosaur) actor).setWaterLevels(10);
                    System.out.println("fssf");
                }
            }

        }
    }

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

