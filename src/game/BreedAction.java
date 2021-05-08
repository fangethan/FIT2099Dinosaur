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
     * This will execute and declare which species has mated
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String species = actor.toString();
        if (species.equals("Stegosaur")) {
            capability(actor);
            return "Stegosaur has mated";
        } else if (species.equals("Brachiosaur")) {
            capability(actor);
            return "Brachiosaur has mated";
        } else {
            capability(actor);
            return "Allosaur has mated";
        }

    }

    /**
     * This checks if the actor is capable of breedimg, checking is its a male or demale
     * or if its pregnant or not
     * @param actor This is the dinosaur
     */
    public void capability(Actor actor) {
        if (actor.hasCapability(Breeding.male)) {
            dinosaurMate.addCapability(Breeding.pregnantFemale);
        } else {
            actor.addCapability(Breeding.pregnantFemale);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
