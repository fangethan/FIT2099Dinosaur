package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
	private int ecoPoints;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		ecoPoints = 1000;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		Location current = map.locationOf(this);
		Ground currentGround = current.getGround();
		if (currentGround instanceof Harvestable) {
			actions.add(new Harvest((Harvestable) currentGround));
		}
		System.out.println("Current Eco-Points: " + this.ecoPoints);
		return menu.showMenu(this, actions, display);
	}

	public int getEcoPoints() {
		return this.ecoPoints;
	}
	public void deductEcoPoints(int points){
		this.ecoPoints -= points;
	}
}
