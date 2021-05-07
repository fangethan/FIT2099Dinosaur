package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BreedBehaviour extends FollowBehaviour{

    // <?> syntax is java's way to specifying that the generic type is "unbounded" - ie it can be "anything".
    private Enum<?> capability;
    private Breeding breeding;
    private WanderBehaviour wanderBehaviour;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public BreedBehaviour(Actor subject, Enum<?> capable) {
        super(subject);
        this.capability = capable;
        this.wanderBehaviour = new WanderBehaviour();
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        target = getLocation(currentLocation, map);

        Action nextAction = null;
        // to check if target is not null and adjacent to the target
        if (target != null && adjacent(actor, target, map)) {
            // If both dinosaurs are both ready to mate
//            if () {
                nextAction = new BreedAction((Dinosaur) target);
//            }
        } else {
            // the else if checks if it is not near its mate and still fertile so it can move closer towards it
            nextAction = super.getAction(actor, map);
        }

            return nextAction;

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
//                if (validActor(actor)) {
//                    System.out.println("Valid dino");
//
//                }
                if (currentLocation != there) {
                    if (minimalLocation == null) {
                        minimalLocation = there;
                    } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocation)) {
                        minimalLocation = there;
                        int minimumDistance = super.distance(currentLocation,there);
                        System.out.println("Min location is: " + minimumDistance);
                    }
                }
            }
        }



        return map.getActorAt(minimalLocation);
    }

    public boolean validActor(Actor actor) {

        return actor != null && actor.hasCapability(capability);
    }

    public boolean sameSpecies(Dinosaur mate1, Dinosaur mate2) {
        if (mate1.getGender() == mate2.getGender()) {
            return true;
        }
        return false;
    }

//    public boolean checkEligibility(Dinosaur dinosaur) {
//        if(!dinosaur.hasCapability(Breeding.baby) && dinosaur.getFoodLevel() > 50) {
//            dinosaur.addCapability(breeding.findMate());
//        }
//        return false;
//    }



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
                }
            }
        }
        return dinosaursList;
    }

    public boolean adjacent(Actor mate1, Actor mate2, GameMap gameMap) {
        Location here = gameMap.locationOf(mate1);

        for (Exit exit: here.getExits()) {
            if (gameMap.getActorAt(exit.getDestination()) == mate2) {
                return true;
            }
        }

        return false;
    }


}
