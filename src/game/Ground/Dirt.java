package game.Ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Exit;

import java.util.List;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	/**
	 * This is a constructor
	 * It has a display char of '.'
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * This creates bushes on the ground depending on the probabolity
	 * @param location The location of the Ground
	 */

	public void tick(Location location) {
		boolean hasTree = false;
		int bushNeighbour = 0;
		//Gets all exits around
		List<Exit> exits = location.getExits();
		//Checks to see if there is a tre or bush near by
		for (Exit exit: exits){

			Location destination = exit.getDestination();
			Ground groundInstance = destination.getGround();
			if (groundInstance instanceof Tree){
				hasTree = true;
			}

			if (groundInstance instanceof Bush){
				bushNeighbour++;
			}


		}

		Probability value = new Probability();
		float randomNumber = value.getProbability();

		if (!hasTree) {
			if ((bushNeighbour >= 2&& randomNumber<=0.01) || (randomNumber<0.009)){
				location.setGround(new Bush());

			}
		}

	}

}
