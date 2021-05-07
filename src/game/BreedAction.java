package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * This class is the breed action which creates an egg after breed behvaviour calls this
 */
public class BreedAction extends Action {
    private Dinosaur dinosaurMate;
    private Location location;
    private Behaviour wanderBehaviour = new WanderBehaviour();

    /**
     * This is the constructor
     * @param dinosaurMate which is the breeding partner
     */
    public BreedAction(Dinosaur dinosaurMate) {
        this.dinosaurMate = dinosaurMate;
    }

    /**
     * This will execute and create eggs
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String egg = actor.toString();
        if (egg.equals("Stegosaur")) {
            location = map.locationOf(actor);
            location.addItem(new StegosaurEgg());
            capability(actor);
            return "Stegosaur egg has been laid";
        } else if (egg.equals("Brachiosaur")) {
            location = map.locationOf(actor);
            location.addItem(new BrachiosaurEgg());
            capability(actor);
            return "Brachiosaur egg has been laid";
        } else {
            location = map.locationOf(actor);
            location.addItem(new AllosaurEgg());
            capability(actor);
            return "Allosaur egg has been laid";
        }

    }

    /**
     * This checks if the actor is capable of breedimg, checking is its a male or demale
     * or if its pregant or not
     * @param actor This is the dinsour
     */
    public void capability(Actor actor) {
        if (actor.hasCapability(Breeding.eligibleMale)) {
            actor.removeCapability(Breeding.eligibleMale);
            dinosaurMate.removeCapability(Breeding.eligibleFemale);
            dinosaurMate.addCapability(Breeding.pregnantFemale);
        } else {
            dinosaurMate.removeCapability(Breeding.eligibleMale);
            actor.removeCapability(Breeding.eligibleFemale);
            actor.addCapability(Breeding.pregnantFemale);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
