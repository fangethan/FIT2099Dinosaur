package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.Action.QuittingGame;
import game.Dinosaur.Allosaur;
import game.Dinosaur.Brachiosaur;
import game.Dinosaur.Pterodactyls;
import game.Dinosaur.Stegosaur;
import game.Ground.*;
import game.Items.Fish;
import game.Player.Player;
import game.World.MenuStartup;

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
		//Adding new game

		if (world == null) {
			System.exit(0);
		}

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		GameMap newGameMap = new GameMap(groundFactory, newMap);
		world.addGameMap(newGameMap);

		NumberRange w = gameMap.getXRange();
		NumberRange h = gameMap.getYRange();
		int max_height = h.max();
		int min_height = h.min();
		// Adding access to the other map
		for (int i : w) {
			Location topMap1 = gameMap.at(i, h.min());
			topMap1.addExit(new Exit("next map", newGameMap.at(i, max_height), "8"));
		}

		for (int i : w) {
			Location bottomMap2 = newGameMap.at(i, h.max());
			bottomMap2.addExit(new Exit("next map", gameMap.at(i, min_height), "2"));
		}

		Actor player = new Player("Player", '@', 100, new QuittingGame());
		// for feeding
//		player.addItemToInventory(new Fruit());
		world.addPlayer(player, gameMap.at(9, 4));

		// Place a pair of stegosaurs in the middle of the map
		Stegosaur male = new Stegosaur("Stegosaur", 'M');
		Stegosaur female = new Stegosaur("Stegosaur", 'F');

		//Places 3 brachisaurs on the map
		Brachiosaur brachiosaur1 = new Brachiosaur("Brachiosaur", 'M');
		Brachiosaur brachiosaur2 = new Brachiosaur("Brachiosaur", 'F');

		//Places 3 allosaurs on the map
		Allosaur allosaur1 = new Allosaur("Allosaur", 'M');

		Pterodactyls pterodactyl1 = new Pterodactyls("Pterodactyl", 'M');
		Pterodactyls pterodactyl2 = new Pterodactyls("Pterodactyl", 'F');
		Pterodactyls pterodactyl3 = new Pterodactyls("Pterodactyl", 'F');

		gameMap.at(10, 12).addActor(pterodactyl1);
		gameMap.at(2, 2).addActor(pterodactyl2);
		gameMap.at(32, 12).addActor(pterodactyl3);


		gameMap.at(24, 18).addActor(brachiosaur1);
		gameMap.at(24, 16).addActor(brachiosaur2);

		gameMap.at(10, 20).addActor(male);
		gameMap.at(30, 10).addActor(female);

		gameMap.at(64, 10).addActor(allosaur1);

		// for testing purposes
//		Corpse corpse = new Corpse(female.getDisplayChar());
//		gameMap.at(30,10).addItem(corpse);


		NumberRange widths = gameMap.getXRange();
		NumberRange height = gameMap.getYRange();
		gameMap.at(5, 7).setGround(new VendingMachine());

		// adding fish to the lake
		for (int x : widths) {
			for (int y : height) {
				Location location = gameMap.at(x, y);
				if (location.getGround() instanceof Lake) {
					for (int i = 0; i < 5; i++) {
						location.addItem(new Fish());
					}

				}
			}
		}

		gameMap.at(5, 7).setGround(new VendingMachine());



			world.run();

	}



}
