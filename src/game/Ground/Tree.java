package game.Ground;

import edu.monash.fit2099.engine.*;
import game.Player.EcoPoints;
import game.Items.Fruit;

import java.util.Random;

public class Tree extends Ground implements Harvestable {
	/**
	 * age of the tree
	 */
	private int age = 0;

	/**
	 * constructor of the tree
	 */
	public Tree() {
		super('+');
	}

	/**
	 * to see if the tree is fully grown or not as well as add fruits on it by probability
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
		Probability value = new Probability();
		float randomNumber = value.getProbability();
		if (randomNumber <= 0.05) {
			location.addItem(new Fruit());
			EcoPoints.addPoints(1);



		}
	}

	/**
	 * To see if the tree can harvest or not
	 * @param actor The actor that is doing the harvesting
	 * @param location Location of the object being harvested.
	 * @return
	 */
	@Override
	public String harvest(Actor actor, Location location) {
		// Add fruit to inventory randomly
		Random tree = new Random();
		float n = tree.nextFloat();
		if (n<= 0.4){
			actor.addItemToInventory(new Fruit());
			return actor + " harvests fruit from tree";
		}
		else {
			return actor + " does not successfully harvest fruit from tree";

		}
	}
}
