package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Returns actions to search out for herbivore meals and then eat it.
 */
public class HungerBehaviour extends FollowBehaviour{

    private Location minimalLocation = null;


    /**
     * The HungerBehaviour constructor
     * @param subject the actor that is hungry
     */
    public HungerBehaviour(Actor subject) {
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

        // make sure destination isn't null
        if (destination != null) {
            // to check if target is not null and adjacent to the target
            if (sameSpot(actor, destination, map)) {
                // If dinosaur is ready to eat fruit
                nextAction = new Eat(destination);
                nextAction.execute(actor,map);
            } else {
                // the else if checks if it is not near its fruit it can move closer towards it
                nextAction = nextMoveAction(actor,map,destination);
            }
        }

        return nextAction;
    }

    /**
     * nextMoveAction is like the super getAction in follow behaviour
     * but this method is a replacement which allows for actors to follow to a location aka an item
     * so they can eat it
     * @param actor is the dinosaur
     * @param map is the map of the app
     * @param there is the food location
     * @return
     */
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
        Map<Location, Item> foodList = new HashMap<>();
        Actor actor = currentLocation.getActor();
        foodList = getFood(map);
        Location minimalLocation = null;

        // if there is nothing in the food list
        if (foodList.size() > 0) {
            for (Map.Entry<Location, Item> spot: foodList.entrySet()) {
                // get the location of food and the food name
                int x = spot.getKey().x();
                int y = spot.getKey().y();
                char foodName = spot.getValue().getDisplayChar();
                // sees what the dinosaur species is first
                if (actor.getDisplayChar() == 'P' || actor.getDisplayChar() == 'p') {
                    // checks if the food is allowed for dinosaur to eat
                    if (foodName == 'c' || foodName == '%') {
//                    System.out.println(foodName + ": " + x + ',' + y);
                        minimalLocation = minimumLocation(x,y,map,currentLocation);
                    }
                } else if (actor.getDisplayChar() == 'S' || actor.getDisplayChar() == 's' ||
                        actor.getDisplayChar() == 'B' || actor.getDisplayChar() == 'b') {
                    if (foodName == 'f') {
//                    System.out.println(foodName + ": " + x + ',' + y);
                        minimalLocation = minimumLocation(x,y,map,currentLocation);
                    }
                }

            }
        }

        return minimalLocation;
    }

    /**
     * finds the minimum location implemented to save effiency in the
     * getLocation so there will be less repeated
     * lines in the if else inside the for each loop
     * @param x the x coordinate
     * @param y the y coordinate
     * @param map is the map on the app
     * @param currentLocation is the actor current location
     * @return the minimalLocation of the closest food
     */
    public Location minimumLocation(int x, int y, GameMap map, Location currentLocation) {
        Location there = map.at(x,y);
        if (minimalLocation == null) {
            minimalLocation = there;
        } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocation)) {
            minimalLocation = there;
        }
        return minimalLocation;
    }


    /**
     * getFood gets all the food items on the gameMap
     * @param gameMap is the entire gameMap of the app
     * @return returns all the fruits found on the gameMap in a list
     */
    public Map<Location, Item> getFood(GameMap gameMap) {
        Map<Location, Item> foodList = new HashMap<>();
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                for (Item item: location.getItems()) {
                    foodList.put(location,item);
                }
            }
        }
        return foodList;
    }


    /**
     * adjacent checks if the dinosaur is next to a food source or not so they can eat the fruit
     * @param mate1 is the dinosaur
     * @param food is the food source
     * @param gameMap is the map of the app
     * @return returns true or false depending if the actors are next to each other
     */
    public boolean sameSpot(Actor mate1, Location food, GameMap gameMap) {
        Location here = gameMap.locationOf(mate1);
        if (here == food) {
             System.out.println("They're same spot");
             return true;
        }

        return false;
    }
}
