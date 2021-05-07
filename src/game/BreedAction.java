package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BreedAction extends Action {
    private Dinosaur dinosaur;

    public BreedAction(Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Breeding.eligibleMale)) {

        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
