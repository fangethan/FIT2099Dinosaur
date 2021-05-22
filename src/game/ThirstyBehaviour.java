package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;

public class ThirstyBehaviour extends FollowBehaviour{


    /**
     * Constructor
     *
     * @param subject the Actor to follow
     */
    public ThirstyBehaviour(Actor subject) {
        super(subject);
    }
    /**
     * getAction gets the actor that is hungry and the gameMap and sees if the
     * actor current location is adjacent to a food source which could be bush or tree
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the super getAction which is in the FollowBehaviour class which
     * allows the dinosaur to start moving towards its food source if not near any
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        Location destination = getLocation(currentLocation, map);

        Action nextAction = null;

        // to check if target is not null and adjacent to the target
        if (destination != null && adjacent(actor, destination, map)) {
            // If dinosaur is ready to drink
            nextAction = new Drink(destination);
            nextAction.execute(actor,map);
        } else {
            // the else if checks if it is not near its fruit it can move closer towards it
            nextAction = nextMoveAction(actor,map,destination);
        }
        return nextAction;
    }

    public Action nextMoveAction(Actor actor, GameMap map, Location there) {
        Location here = map.locationOf(actor);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }
        return null;
    }


    /**
     * getLocation is used to find the closest food source for the herbivores to eat
     * out of all the bushes/trees around the map
     * @param currentLocation is the current location of the dinosaur
     * @param map is the gameMap of the app
     * @return returns the minimum location of the food source
     */
    public Location getLocation(Location currentLocation, GameMap map) {
        Map<Location, Ground> water = new HashMap<>();
        water = getAllLakes(map);
        Location minimalLocation = null;

        for (Map.Entry<Location, Ground> spot: water.entrySet()) {
            int x = spot.getKey().x();
            int y = spot.getKey().y();
            Location there = map.at(x,y);
            if (minimalLocation == null) {
                minimalLocation = there;
            } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocation)) {
                minimalLocation = there;
            }
        }

        return minimalLocation;
    }


    /**
     * getAllLakes gets all the lakes on the gameMap
     * @param gameMap is the entire gameMap of the app
     * @return returns all the fruits found on the gameMap in a list
     */
    public Map<Location, Ground> getAllLakes(GameMap gameMap) {
        Map<Location, Ground> water = new HashMap<>();
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                Ground ground = location.getGround();
                if(ground instanceof Lake) {
                    water.put(location, ground);
                }
            }
        }
        System.out.println(water.size());
        return water;
    }

    /**
     * adjacent checks if the dinosaur is next to a food source or not so they can eat the fruit
     * @param mate1 is the dinosaur
     * @param lake is the food source
     * @param gameMap is the map of the app
     * @return returns true or false depending if the actors are next to each other
     */
    public boolean adjacent(Actor mate1, Location lake, GameMap gameMap) {
        Location here = gameMap.locationOf(mate1);
        if (here.x() + 1 == lake.x() && here.y() == lake.y()) {
            System.out.println("They're adjacent");
            return true;
        } else if (here.x() - 1 == lake.x() && here.y() == lake.y()) {
            System.out.println("They're adjacent");
            return true;
        } else if (here.x() == lake.x() && here.y() + 1 == lake.y()) {
            System.out.println("They're adjacent");
            return true;
        } else if (here.x() == lake.x() && here.y() - 1== lake.y()) {
            System.out.println("They're adjacent");
            return true;
        }

        return false;
    }

}