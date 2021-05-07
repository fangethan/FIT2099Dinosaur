package game;


import edu.monash.fit2099.engine.*;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;
	private int foodLevels = 2;
	private int age;
	private char gender;
	private int tick = 0;

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 * 
	 * @param name the name of this Stegosaur
	 */
//	public Stegosaur(String name, int foodLevels) {
//		super(name, 'S', 50);
//		behaviour = new WanderBehaviour();
//		this.foodLevels = foodLevels;
//	}

	public Stegosaur(String name, int foodLevels, int age, char gender) {
		super(name, 'S', 100);
		behaviour = new WanderBehaviour();
		this.foodLevels = foodLevels;
		this.age = age;
		this.gender = gender;

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
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action wander = behaviour.getAction(this, map);
		//Reduces food level each turn

		/*if (wander != null)
			return wander;

		return new DoNothingAction();
		*/

		Action nextAction;

		if (!isConscious()) {
			behaviour = new BreedBehaviour(this,Breeding.male);
			nextAction = behaviour.getAction(this,map);
			return nextAction;
		}

		// checks if animal is dead and tick thing where tick = 20, then it dies
		this.foodLevels -= 1;
		System.out.println(this.foodLevels);
		if (this.foodLevels <= 0) {
			this.tick++;

			if (this.tick == 20) {
				Death deathAction = new Death();
				this.tick =0;
				return deathAction;


			}

		}
		return wander;
	}
}
