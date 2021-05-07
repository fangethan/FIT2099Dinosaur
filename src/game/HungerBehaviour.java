package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;

public class HungerBehaviour extends FollowBehaviour{

    public HungerBehaviour(Actor subject) {
        super(subject);
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        Actor destination = getLocation(currentLocation, map);

//        int movingDistance = super.distance(currentLocation,map.locationOf(destination));

        while (target != null && adjacent(actor,destination,map) == false) {

            return super.getAction(actor, map);
        }

        return super.getAction(actor, map);
    }

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
