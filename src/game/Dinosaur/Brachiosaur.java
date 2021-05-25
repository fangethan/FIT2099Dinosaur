package game.Dinosaur;

import edu.monash.fit2099.engine.*;
import game.Action.AttackAction;
import game.Behaviour.Behaviour;
import game.Behaviour.WanderBehaviour;

/**
 * This is the brachiosaur class which extends dinosaur
 */
public class Brachiosaur extends Dinosaur {
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;

    /**
     * Constructor.
     * Each brachisaur is represented by B.
     *
     * @param name the name of this brachiosaur
     *  @param gender the gender of this brachiosaur
     */
    public Brachiosaur(String name, char gender) {
        super(name, 'B', 100, gender);

        behaviour = new WanderBehaviour();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }


//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        Action wander = behaviour.getAction(this, map);
//        if (wander != null)
//            return wander;
//
//        return new DoNothingAction();
//    }
}
