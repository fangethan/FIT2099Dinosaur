package game.Player;

import edu.monash.fit2099.engine.*;
import game.Action.Harvest;
import game.Ground.Harvestable;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
	private final Action quitAction;
	/**
	 *  the ecoPoints of the player
	 */
	private int ecoPoints;
	/**
	 * the menu of the player
	 */
	private Menu menu = new Menu();

	/**
	 * Constructor for player
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Action actionExit) {
		super(name, displayChar, hitPoints);
		EcoPoints.setPlayer(this);
		ecoPoints = 0;
		this.quitAction = actionExit;
	}

	/**
	 * Each turn, the player will move
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		actions.add(this.quitAction);
		Location current = map.locationOf(this);
		Ground currentGround = current.getGround();
		if (currentGround instanceof Harvestable) {
			actions.add(new Harvest((Harvestable) currentGround));
		}
		System.out.println("Current Eco-Points: " + this.ecoPoints);
		return menu.showMenu(this, actions, display);
	}

	/**
	 * getEcoPoint retrieves the eco points of player
	 * @return the eco points
	 */
	public int getEcoPoints() {
		return this.ecoPoints;
	}

	/**
	 * addPoints adds more points to the players eco points
	 * @param points adds that amount of points towards its ecoPoints
	 */
	public void addPoints(int points) {
		this.ecoPoints += points;
	}

	/**
	 * deductEcoPoints deducts points off the players eco points
	 * @param points deducts that amount of point towards its ecoPoints
	 */
	public void deductEcoPoints(int points){
		this.ecoPoints -= points;
	}
}
