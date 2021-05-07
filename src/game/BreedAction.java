package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class BreedAction extends Action {
    private Dinosaur dinosaurMate;
    private Location location;
    private Behaviour wanderBehaviour = new WanderBehaviour();

    public BreedAction(Dinosaur dinosaurMate) {
        this.dinosaurMate = dinosaurMate;
    }


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
