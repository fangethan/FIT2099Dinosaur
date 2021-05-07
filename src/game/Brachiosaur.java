package game;

import edu.monash.fit2099.engine.*;

public class Brachiosaur extends Dinosaur {
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;

    /**
     * Constructor.
     * All Brachiosaur are represented by a 'B' and have 100 hit points.
     *
     * @param name the name of this brachiosaur
     */
    public Brachiosaur(String name, char gender) {
        super(name, 'B', 100, gender);

        behaviour = new WanderBehaviour();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        Action wander = behaviour.getAction(this, map);
//        if (wander != null)
//            return wander;
//
//        return new DoNothingAction();
//    }
}
