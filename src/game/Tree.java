package game;

import edu.monash.fit2099.engine.*;

import java.util.Map;
import java.util.Random;

public class Tree extends Ground implements Harvestable {
	private int age = 0;

	public Tree() {
		super('+');
	}

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
