package game.Behaviour;

import edu.monash.fit2099.engine.*;
import game.Action.BreedAction;
import game.Dinosaur.Dinosaur;
import game.Ground.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * This class initiates 2 dinosaurs moving together to breed
 *
 */
public class BreedBehaviour extends FollowBehaviour {

    // <?> syntax is java's way to specifying that the generic type is "unbounded" - ie it can be "anything".
    private Enum<?> capability;
    private Breeding breeding;
    private WanderBehaviour wanderBehaviour;
    private Location minimalLocationPterodactyl = null;


    /**
     * This is the Constructor.
     *
     * @param subject the Actor to follow
     * @param capable Checks capability of dinosaur
     */
    public BreedBehaviour(Actor subject, Enum<?> capable) {
        super(subject);
        this.capability = capable;
        this.wanderBehaviour = new WanderBehaviour();
    }

    /**
     * This checks the location and see if it is next to each other
     * If they are adjacent then a breedaction is called
     * If not then it keeps moving towards
     * @param actor
     * @param map
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action nextAction = null;

        Location currentLocation = map.locationOf(actor);
        target = getLocation(currentLocation, map);
        Location tree = getClosestTree(currentLocation,map);

        if (target != null) {
            // for pterodactyl
            if (actor.getDisplayChar() == 'P') {
                if (sameSpecies((Dinosaur) target, (Dinosaur) actor) == true && oppositeGenders((Dinosaur) target, (Dinosaur) actor) == true) {
                    // to check if target is not null and adjacent to the target
                    if (adjacent(actor, target, map) &&
                            map.locationOf(target).x() == tree.x() && map.locationOf(target).y() == tree.y()) {
                        // If both dinosaurs are both ready to mate
                        nextAction = new BreedAction((Dinosaur) target);
                        nextAction.execute(actor, map);
                    } else if (map.locationOf(target).x() != tree.x() && map.locationOf(target).y() != tree.y()) {
                        nextAction = moveTree(target, map, tree);
                    } else {
                        // the else if checks if it is not near its mate and still fertile so it can move closer towards it
                        nextAction = movePteradoctylOnTree(actor,map, target);
                    }
                }
            } else {
                // for other dinosaurs
                if (sameSpecies((Dinosaur) target, (Dinosaur) actor) == true && oppositeGenders((Dinosaur) target, (Dinosaur) actor) == true) {
                    // to check if target is not null and adjacent to the target
                    if (adjacent(actor, target, map)) {
                        // If both dinosaurs are both ready to mate
                        nextAction = new BreedAction((Dinosaur) target);
                        nextAction.execute(actor,map);
                    } else {
                        // the else if checks if it is not near its mate and still fertile so it can move closer towards it
                        nextAction = super.getAction(actor, map);
                    }
                }
            }
        }
            return nextAction;
    }

    public Action movePteradoctylOnTree(Actor actor, GameMap map, Actor target) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

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
     * This gets the minimum distance to an eligible dinosaur
     *
     * @param currentLocation this is the current location of the dinosaur
     * @param map this is the gamemap
     * @return actor at location
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
                if (currentLocation != there) {
                    if (minimalLocation == null) {
                        minimalLocation = there;
                    } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocation)) {
                        minimalLocation = there;
                    }
                }
            }
        }

        return map.getActorAt(minimalLocation);
    }

    /**
     * This checks if the actor has certain capabilities
     * @param actor
     * @return true or false
     */
    public boolean validActor(Actor actor) {
        return actor != null && actor.hasCapability(capability);
    }
    /**
     * This checks if the 2 dinosaurs are the same species
     * @param mate1 1 is the first dinosaur
     * @param mate2 2 is the second dinosaur
     * @return true or false
     */
    public boolean sameSpecies(Dinosaur mate1, Dinosaur mate2) {
        if (mate1.getDisplayChar() == mate2.getDisplayChar()) {
            return true;
        }
        return false;
    }

    public boolean oppositeGenders(Dinosaur mate1, Dinosaur mate2) {
        if (mate1.getGender() != mate2.getGender()) {
            return true;
        }
        return false;
    }


    /**
     * This gets all the dinosaurs on the map
     * @param gameMap is the map
     * @return list of all the dinosaurs
     */
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

    /**
     * THis checks if 2 dinosaurs are adjacent to each other
     * @param mate1 is the first dinosaur
     * @param mate2 is the second dinosaur
     * @param gameMap is the mao
     * @return true or false
     */
    public boolean adjacent(Actor mate1, Actor mate2, GameMap gameMap) {
        Location here = gameMap.locationOf(mate1);

        for (Exit exit: here.getExits()) {
            if (gameMap.getActorAt(exit.getDestination()) == mate2) {
                return true;
            }
        }

        return false;
    }

    public Action moveTree(Actor actor, GameMap map, Location there) {
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
    public Location getClosestTree(Location currentLocation, GameMap map) {
        Map<Location, Ground> treeList = new HashMap<>();
        treeList = getAllTrees(map);

        for (Map.Entry<Location, Ground> spot: treeList.entrySet()) {
            int x = spot.getKey().x();
            int y = spot.getKey().y();
            Location there = map.at(x,y);
            if (minimalLocationPterodactyl == null) {
                minimalLocationPterodactyl = there;
            } else if (super.distance(currentLocation, there) < super.distance(currentLocation, minimalLocationPterodactyl)) {
                minimalLocationPterodactyl = there;
            }
        }
        return minimalLocationPterodactyl;
    }


    /**
     * getAllFruits gets all the fruits on trees or bushes on the gameMap
     * @param gameMap is the entire gameMap of the app
     * @return returns all the fruits found on the gameMap in a list
     */
    public Map<Location, Ground> getAllTrees(GameMap gameMap) {
        Map<Location, Ground> treeList = new HashMap<>();
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                if (location.getGround() instanceof Tree) {
                    treeList.put(location,location.getGround());
                }
            }
        }
//        System.out.println(treeList.size());
        return treeList;
    }


}
