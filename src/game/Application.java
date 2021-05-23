package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		MenuStartup menu = new MenuStartup(new Display());
		World world = menu.initalMenu();
		//World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Lake());
		
		List<String> map = Arrays.asList(
		"..............................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....~~~~~...........................++++++.....................................",
		".....~~~~.............................+++.......................................",
		".....~~~.............................+++........................................",
		".....~~~........................................................................",
		".....~~~~......+++..............................................................",
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
		List<String> newMap = Arrays.asList(
				"..............................................................................",
				".............+..................................................................",
				".............+..................................................................",
				".............++.....................................~~~~~.......................",
				".............+++.....................................~~~~~~~~...................",
				".............+++++....................................~~~~~~~~~~................",
				"...............++++++++............................~~~~~~~~~~~..................",
				"................+++++++++++++++.......................~~~~~.....................",
				"~.........................+++++++...................~~..........................",
				"~~..............................++..............................................",
				"~~~~............................++..............................................",
				"~~~~~~~........................+++++............................................",
				"~~~~~~~~~.......................++++............................................",
				"~~~~~~~~~~~~~...................................................................",
				"~~~~~~~~~~~...............................................++++++................",
				"~~~~~~~~~~~.................................................+++.................",
				"~~~~~~~~~....................................................++.................",
				"~~~~~~..........................................................................",
				"~~~~~...........................................................................",
				"~~..............................................................................",
				"~........................++++++++++++++.........................................",
				"............................++++++++............................................",
				"...............................+++++............................................",
				".................................+..............................................",
				"................................................................................");
		//Adding new game map
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);

		GameMap newGameMap = new GameMap(groundFactory, newMap);
		world.addGameMap(newGameMap);

		NumberRange w = gameMap.getXRange();
		NumberRange h= gameMap.getYRange();
		int max_height = h.max();
        int min_height = h.min();
		// Adding access to the other map
		for (int i:w){
			Location topMap1 = gameMap.at(i, h.min());
			topMap1.addExit(new Exit("next map", newGameMap.at(i, max_height), "8"));
		}

		for (int i : w) {
			Location bottomMap2 = newGameMap.at(i, h.max());
			bottomMap2.addExit(new Exit("next map", gameMap.at(i, min_height), "2"));
		}
		
		Actor player = new Player("Player", '@', 100, new QuittingGame());
		world.addPlayer(player, gameMap.at(3, 2));
		
		// Place a pair of stegosaurs in the middle of the map
		Stegosaur male = new Stegosaur("Stegosaur",'M');
		Stegosaur female = new Stegosaur("Stegosaur",'F');

		//Places 3 brachisaurs on the map
		Brachiosaur brachiosaur1 = new Brachiosaur("Brachiosaur",'M');
		Brachiosaur brachiosaur2 = new Brachiosaur("Brachiosaur",'F');
		Brachiosaur brachiosaur3 = new Brachiosaur("Brachiosaur",'F');

		//Places 3 allosaurs on the map
		Allosaur allosaur1 = new Allosaur("Allosaur", 'M');
		Allosaur allosaur2 = new Allosaur("Allosaur", 'F');
		Allosaur allosaur3 = new Allosaur("Allosaur", 'F');


		Pterodactyls pterodactyl1 = new Pterodactyls("Pterodactyl", 'M');
		Pterodactyls pterodactyl2 = new Pterodactyls("Pterodactyl",'F');
		Pterodactyls pterodactyl3 = new Pterodactyls("Pterodactyl",'F');

		gameMap.at(32, 12).addActor(pterodactyl1);
//		gameMap.at(2, 2).addActor(pterodactyl2);
		gameMap.at(34, 12).addActor(pterodactyl3);

//		BreedBehaviour breedBehaviour = new BreedBehaviour(pterodactyl1, Breeding.male);
//		breedBehaviour.getAction(pterodactyl1,gameMap);
//		breedBehaviour.getAllTrees(gameMap);
//		breedBehaviour.getClosestTree(gameMap.at(32, 12),gameMap);

//		gameMap.at(25, 12).addActor(brachiosaur1);
//		gameMap.at(60, 12).addActor(brachiosaur2);
//		gameMap.at(32, 12).addActor(brachiosaur3);

//		gameMap.at(10, 20).addActor(male);
//		gameMap.at(60, 10).addActor(female);
//		gameMap.at(10, 24).addActor(new Stegosaur("Stegosaur",'F'));

		gameMap.at(64,10).addActor(allosaur1);
		Corpse corpse = new Corpse();
		gameMap.at(30,10).addItem(corpse);
		HuntBehaviour huntBehaviour = new HuntBehaviour(allosaur1);
		huntBehaviour.getAllActors(gameMap);
//		huntBehaviour.getLocation(gameMap.at(64,10),gameMap);

//		gameMap.at(67,10).addActor(allosaur2);

//		gameMap.at(10,10).addActor(brachiosaur1);
//		gameMap.at(13,10).addActor(brachiosaur2);

		Fruit fruit = new Fruit();
		gameMap.at(30,20).addItem(fruit);
		gameMap.at(26,20).addItem(fruit);
		gameMap.at(20,20).addItem(fruit);

//		gameMap.at(0,1).addItem(fruit);
//		gameMap.at(0,2).addItem(fruit);
//		gameMap.at(0,3).addItem(fruit);

//		HungerBehaviour hungerBehaviour = new HungerBehaviour(male);
//		hungerBehaviour.getALLFruits(gameMap);
//		hungerBehaviour.getLocation(gameMap.at(25,20),gameMap);
//		hungerBehaviour.getAction(male,gameMap);



		gameMap.at(5,7).setGround(new VendingMachine());

//		BreedBehaviour breedBehaviour = new BreedBehaviour(male, Breeding.male);
//		breedBehaviour.getAction(male,gameMap);
		//MenuStartup menu = new MenuStartup(new Display(), world);
		//menu.initalMenu();
		world.run();
	}

}
