package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Wall extends Ground {
	/**
	 * constructor of wall
	 */
	public Wall() {
		super('#');
	}

	/**
	 * tells actor can't enter into a wall
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * locks thrown objects but not movement
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
