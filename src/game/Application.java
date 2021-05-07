package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Place a pair of stegosaurs in the middle of the map
		Stegosaur male = new Stegosaur("Stegosaur",'M');
		Stegosaur female = new Stegosaur("Stegosaur",'F');

		//Places 3 brachisaurs on the map
		Brachiosaur brachiosaur1 = new Brachiosaur("Brachiosaur",'M');
		Brachiosaur brachiosaur2 = new Brachiosaur("Brachiosaur",'F');
		Brachiosaur brachiosaur3 = new Brachiosaur("Brachiosaur",'F');

		//Places 3 allosaurs on the mpa
		Allosaur allosaur1 = new Allosaur("Allosaur", 'M');
		Allosaur allosaur2 = new Allosaur("Allosaur", 'F');
		Allosaur allosaur3 = new Allosaur("Allosaur", 'F');


		gameMap.at(25, 12).addActor(brachiosaur1);
		gameMap.at(60, 12).addActor(brachiosaur2);
		gameMap.at(32, 12).addActor(brachiosaur3);

		gameMap.at(25, 20).addActor(male);
		gameMap.at(60, 10).addActor(female);
		gameMap.at(32, 20).addActor(new Stegosaur("Stegosaur",'F'));


		gameMap.at(5,7).setGround(new VendingMachine());

//		BreedBehaviour breedBehaviour = new BreedBehaviour(male, Breeding.male);
//		breedBehaviour.getAction(male,gameMap);

		world.run();
	}
}
