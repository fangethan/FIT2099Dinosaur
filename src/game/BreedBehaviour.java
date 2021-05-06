package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BreedBehaviour extends FollowBehaviour{

    private Enum<?> capability;
    private WanderBehaviour wanderBehaviour;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public BreedBehaviour(Actor subject) {
        super(subject);
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        Actor destination = getLocation(currentLocation, map);

//        int movingDistance = super.distance(currentLocation,map.locationOf(destination));

        while (adjacent(actor,destination,map) == false) {
            return super.getAction(actor, map);
        }

        return super.getAction(actor, map);
    }

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

    public boolean validActor(Actor actor) {
        return actor != null && actor.hasCapability(capability);
    }


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