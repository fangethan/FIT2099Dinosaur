package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Exit;
import java.util.List;
import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}


	public void tick(Location location) {
		boolean hasTree = false;
		int bushNeighbour = 0;

		List<Exit> exits = location.getExits();

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
			if ((bushNeighbour >= 2&& randomNumber<=0.1) || (randomNumber<0.01)){
				location.setGround(new Bush());
			}
		}

	}

}
