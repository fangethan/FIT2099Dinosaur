package game;

import edu.monash.fit2099.engine.*;

public class Allosaur extends Actor {
    // Will need to change this to a collection if Stegosaur gets additional Behaviours.
    private Behaviour behaviour;

    /**
     * Constructor.
     * All Stegosaurs are represented by a 'A' and have 100 hit points.
     *
     * @param name the name of this allosaur
     */
    public Allosaur(String name) {
        super(name, 'A', 100);

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
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }
}
