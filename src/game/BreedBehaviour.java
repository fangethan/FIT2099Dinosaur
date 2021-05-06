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




        int currentDistance = distance(here, minimalLocation);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, minimalLocation);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        System.out.println("Breed action begins");
        return super.getAction(actor, map);
    }

    public Location getLocation(Location here, GameMap map) {
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
                } else if (super.distance(here, there) < super.distance(here, minimalLocation)) {
                    minimalLocation = there;
                    int minimumDistance = super.distance(here,there);
                    System.out.println("Min location is: " + minimumDistance);
                }
            }
        }



        return minimalLocation;
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
