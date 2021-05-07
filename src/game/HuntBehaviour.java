package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;

/**
 * HuntBehaviour returns an action to hunt another actor
 */
public class HuntBehaviour extends FollowBehaviour{
    /**
     * HuntBehaviour constructor
     * @param subject the actor that wants to hunt for other actors
     */
    public HuntBehaviour(Actor subject) {
        super(subject);
    }

    /**
     * getAction gets the actor that wants to hunt and the gameMap and sees if the
     * actor current location is adjacent to a food source which would be another actor
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the super getAction which is in the FollowBehaviour class which
     * allows the dinosaur to start moving towards its food source if not near any
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        target = getLocation(currentLocation, map);

        if (target != null && adjacent(actor, target, map)) {
            return new AttackAction(target);
        }

        return super.getAction(actor, map);
    }
    /**
     * getLocation is used to find the closest food source for the carnivores to eat
     * out of all the actors around the map
     * @param currentLocation is the current location of the dinosaur
     * @param map is the gameMap of the app
     * @return returns the minimum location of the food source
     */
    public Actor getLocation(Location currentLocation, GameMap map) {
        Map<Actor, Location> dinosaursList = new HashMap<>();
        dinosaursList = getAllActors(map);
        Location minimalLocation = null;

        for (Map.Entry<Actor, Location> spot: dinosaursList.entrySet()) {
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
     * getAllActors gets all the actors on the gameMap
     * @param gameMap is the entire gameMap of the app
     * @return returns all the actors found on the gameMap in a list
     */
    public Map<Actor, Location> getAllActors(GameMap gameMap) {

        Map<Actor, Location> dinosaursList = new HashMap<>();
        int count = 0;
        String text = "";
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                Actor actor = gameMap.getActorAt(location);
                if (location.containsAnActor()) {
                    dinosaursList.put(actor,location);
                    count++;
                    text += actor.getDisplayChar();
                }
            }
        }
        System.out.println("Dinosaur list" + count + text);
        return dinosaursList;
    }
    /**
     * adjacent checks if the dinosaur is next to a food source or not so they can attack/eat
     * @param mate1 is the dinosaur
     * @param mate2 is the target food source
     * @param gameMap is the map of the app
     * @return returns true or false depending if the actors are next to each other
     */
    public boolean adjacent(Actor mate1, Actor mate2, GameMap gameMap) {
        Location currentLocation = gameMap.locationOf(mate1);

        for (Exit exit: currentLocation.getExits()) {
            if (gameMap.getActorAt(exit.getDestination()) == mate2) {
                System.out.println("They're adjacent");
                return true;
            }
        }

        return false;
    }
}
