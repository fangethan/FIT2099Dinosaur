package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Returns actions to search out for herbivore meals and then eat it.
 */
public class HungerBehaviour extends FollowBehaviour{

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

        Actor destination = getLocation(currentLocation, map);

        while (target != null && adjacent(actor,destination,map) == false) {
            return super.getAction(actor, map);
        }

        return super.getAction(actor, map);
    }

    /**
     * getLocation is used to find the closest food source for the herbivores to eat
     * out of all the bushes/trees around the map
     * @param currentLocation is the current location of the dinosaur
     * @param map is the gameMap of the app
     * @return returns the minimum location of the food source
     */
    public Actor getLocation(Location currentLocation, GameMap map) {
        Map<Fruit, Location> herbivoreList = new HashMap<>();
        herbivoreList = getALLFruits(map);
        Location minimalLocation = null;

        for (Map.Entry<Fruit, Location> spot: herbivoreList.entrySet()) {
            Actor actor = spot.getValue().getActor();
            int x = spot.getValue().x();
            int y = spot.getValue().y();
            Location there = map.at(x,y);
            if (actor instanceof Dinosaur) {
                System.out.println("actor is dinosaur");
//                if (validActor(actor)) {
//                    System.out.println("Valid dino");
//                }
                if (minimalLocation == null) {
                    minimalLocation = there;
                } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocation)) {
                    minimalLocation = there;
                    int minimumDistance = super.distance(currentLocation,there);
                    System.out.println("Min location is: " + minimumDistance);
                }
            }
        }



        return map.getActorAt(minimalLocation);
    }


    /**
     * getAllFruits gets all the fruits on trees or bushes on the gameMap
     * @param gameMap is the entire gameMap of the app
     * @return returns all the fruits found on the gameMap in a list
     */
    public Map<Fruit, Location> getALLFruits(GameMap gameMap) {

        Map<Fruit, Location> herbivoreList = new HashMap<>();
        int count = 0;
        String text = "";
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
//                if (location.getGround() == ) {
////                    herbivoreList.put(actor,location);
////                    count++;
////                    text += actor.getDisplayChar();
//                }
            }
        }
        System.out.println("Fruit list" + count + text);
        return herbivoreList;
    }

//    public boolean groundHasFruit(Location location) {
//        Ground ground = location.getGround();
//
//        if () {
//
//        }
//
//    }

    /**
     * adjacent checks if the dinosaur is next to a food source or not so they can eat the fruit
     * @param mate1 is the dinosaur
     * @param mate2 is the food source
     * @param gameMap is the map of the app
     * @return returns true or false depending if the actors are next to each other
     */
    public boolean adjacent(Actor mate1, Actor mate2, GameMap gameMap) {
        Location here = gameMap.locationOf(mate1);

        for (Exit exit: here.getExits()) {
            if (gameMap.getActorAt(exit.getDestination()) == mate2) {
                System.out.println("They're adjacent");
                return true;
            }
        }

        return false;
    }
}
