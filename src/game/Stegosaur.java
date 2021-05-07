package game;


import edu.monash.fit2099.engine.*;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.


	/**
	 *
	 * @param name the name of this Stegosaur
	 * @param gender the gender of this Stegosaur
	 */
	public Stegosaur(String name, char gender) {
		super(name, 'S', 100, gender);
	}

	/**
	 * this is used to create new attack actions
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}


	/**
	 * Figure out what to do next.
	 * <p>
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 *
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
//	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//		return null;
//
//	}
}
