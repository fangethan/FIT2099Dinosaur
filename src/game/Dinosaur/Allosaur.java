package game.Dinosaur;

import edu.monash.fit2099.engine.*;
import game.Action.AttackAction;
import game.Behaviour.Behaviour;
import game.Behaviour.WanderBehaviour;

/**
 * This class is a type of dinsour and it extends the dinosaur class. This dinsosaur is a carnivorus
 */
public class Allosaur extends Dinosaur {
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;

    /**
     * Constructor.
     * Each allosaur is represented by A
     *
     * @param name the name of this allosaur
     * @param gender the gender of the allosaur
     */
    public Allosaur(String name, char gender) {
        super(name, 'A', 100, gender);

        behaviour = new WanderBehaviour();
    }

    /**
     * THis is for attacking a dinosaur
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
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
