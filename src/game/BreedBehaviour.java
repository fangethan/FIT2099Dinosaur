package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BreedBehaviour extends FollowBehaviour{

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
        Map<Character, Location> dinosaursList = new HashMap<>();
        int distance = 0;
        NumberRange width = map.getXRange();
        NumberRange length = map.getYRange();
        int minimalDistance = width.max() + length.max();
        Location here = map.locationOf(actor);
        Location minimalLocation = null;
        Dinosaur destinationDinosaur = null;

        for (int x: width) {
            for (int y: length) {
                Location location = map.at(x,y);
                destinationDinosaur = (Dinosaur) map.getActorAt(location);
                if (location.containsAnActor()) {
                    dinosaursList.put(actor.getDisplayChar(),location);
                }
            }
        }



        for (int i = 0; i < dinosaursList.size(); i++) {
            if (actor.getDisplayChar() == destinationDinosaur.getDisplayChar()) {
                if (destinationDinosaur.isConscious() && destinationDinosaur.getGender() == 'M' &&
                        destinationDinosaur.getAge() > 5 && destinationDinosaur.getFoodLevel() >= 50) {
                    Location there = map.locationOf(actor);
                    distance = super.distance(here, there);
                    if (distance < minimalDistance) {
                        minimalDistance = distance;
                        minimalLocation = map.locationOf(actor);
                    }

                }
            }

        }

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

//    @Override
//    public Action getAction(Actor actor, map map, Dinosaur dinosaur) {
//        Map<Character, Location> dinosaursList = new HashMap<>();
//        int distance = 0;
//        NumberRange width = map.getXRange();
//        NumberRange length = map.getYRange();
//        int minimalDistance = width.max() + length.max();
//        Location here = map.locationOf(dinosaur);
//        Location minimalLocation = null;
//
//        for (int x: width) {
//            for (int y: length) {
//                Location location = map.at(x,y);
//                actor = map.getActorAt(location);
//                if (location.containsAnActor()) {
//                    dinosaursList.put(actor.getDisplayChar(),location);
//                }
//            }
//        }
//
//
//
//        for (int i = 0; i < dinosaursList.size(); i++) {
//            if (actor.getDisplayChar() == dinosaur.getDisplayChar()) {
//                if (dinosaur.isConscious() && dinosaur.getGender() == 'M' &&
//                        dinosaur.getAge() > 5 && dinosaur.getFoodLevel() >= 50) {
//                    Location there = map.locationOf(actor);
//                    distance = super.distance(here, there);
//                    if (distance < minimalDistance) {
//                        minimalDistance = distance;
//                        minimalLocation = map.locationOf(actor);
//                    }
//
//                }
//            }
//
//        }
//
//        while (minimalDistance > 1) {
//            int currentDistance = distance(here, minimalLocation);
//            for (Exit exit : here.getExits()) {
//                Location destination = exit.getDestination();
//                if (destination.canActorEnter(actor)) {
//                    int newDistance = distance(destination, minimalLocation);
//                    if (newDistance < currentDistance) {
//                        return new MoveActorAction(destination, exit.getName());
//                    }
//                }
//            }
//
//            minimalDistance--;
//        }
//
//        System.out.println("Breed Action begins");
//
//        return null;
//
//
//    }

}
