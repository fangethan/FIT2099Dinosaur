package game.Dinosaur;

import edu.monash.fit2099.engine.*;
import game.Action.Death;
import game.Action.Feed;
import game.Action.RainAction;
import game.Behaviour.*;
import game.Ground.Probability;
import game.Items.Eggs;
import game.Items.Food;
import game.Player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the dinosaur abstract class
 * This extends the actor
 * This foodlevels, age and gender
 */
public abstract class Dinosaur extends Actor {
    public char gender;
    public int foodLevels = 30;
    public int waterLevels = 30;
    public int age = 32;
    private int tick = 0;
    private int rainTick = 0;
    private int hatchTick = 0;
    private int pregnant = 0;
    private Behaviour behaviour;
    private Behaviour wanderBehaviour = new WanderBehaviour();
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param gender      the gender of the dinsosaur
     */
    public Dinosaur(String name, char displayChar, int hitPoints, char gender) {
        super(name, displayChar, hitPoints);
        this.behaviour = new WanderBehaviour();
        this.gender = gender;
    }

    /**
     * getWaterLevels gets the new water levels
     * @return waterLevel
     */
    public int getWaterLevels() {
        return waterLevels;
    }

    /**
     * setWaterLevels sets the new water levels
     * @param waterLevels is the new water levels
     */
    public void setWaterLevels(int waterLevels) {
        this.waterLevels = waterLevels;
    }

    /**
     * getFoodLevels gets the new food levels
     * @return foodLevels
     */
    public int getFoodLevels() {
        return foodLevels;
    }

    /**
     * setFoodLevels set the new food levels
     * @param foodLevels is the new food levels
     */
    public void setFoodLevels(int foodLevels) {
        this.foodLevels = foodLevels;
    }

    /**
     * This returns the gender of the dinosaur
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * This returns the food levels
     * @return food levels
     */
    public int getFoodLevel() {
        return foodLevels;
    }

    /**
     * This retuns the age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * This checks is the dinosaur is conscious
     * @return true or false
     */
    @Override
    public boolean isConscious() {
        if (foodLevels > 0 && hitPoints > 0 && waterLevels > 0) {
            return true;
        }
        return false;
    }

    /**
     * This determines the actions of the dinosaur based on food levels and such
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        System.out.println("Food levels: " + foodLevels);
        System.out.println("Water levels: " + waterLevels);
        Location location = map.locationOf(this);
        int x = location.x();
        int y = location.y();


        // Rain and making sure uncoius becomes consicous again

        rainTick++;
        Probability value = new Probability();
        float randomNumber = value.getProbability();
            if (randomNumber < 0.2 && rainTick == 2) {

                RainAction rain = new RainAction(map);
                rain.rain();
                rainTick = 0;
            }

        Action nextAction = null;

        if (isConscious()) {
            //Reduces food level each turn
            foodLevels--;
            waterLevels--;

            Map<Actor, Location> playerList = new HashMap<>();
            playerList = getAllPlayers(map);

            for (Map.Entry<Actor, Location> spot: playerList.entrySet()) {
                Actor playerLocation = spot.getValue().getActor();
                List<Item> playerItems = spot.getKey().getInventory();
                if (adjacent(this,playerLocation,map) == true) {
                    for (int i = 0; i < playerItems.size(); i++) {
                        if (playerItems.get(i) instanceof Food) {
                            Feed feed = new Feed(this,(Food) playerItems.get(i));
                            feed.execute(playerLocation,map);
                        }
                    }
                }
            }


            // if dinosaur is pregnant and may be ready to produce egg
            if (hasCapability(Breeding.pregnantFemale)) {
                if (this.displayChar == 'S') {
                    if (pregnant == 10) {
                        pregnant = 0;
                        removeCapability(Breeding.pregnantFemale);
                        map.locationOf(this).addItem(this.produceEgg(this));
                        nextAction = wanderBehaviour.getAction(this,map);
                    } else {
                        pregnant++;
                        nextAction = new DoNothingAction();
                    }
                } else if (this.displayChar == 'B') {
                    if (pregnant == 30) {
                        pregnant = 0;
                        removeCapability(Breeding.pregnantFemale);
                        map.locationOf(this).addItem(this.produceEgg(this));
                        nextAction = wanderBehaviour.getAction(this,map);
                    } else {
                        pregnant++;
                        nextAction = new DoNothingAction();
                    }
                } else if (this.displayChar == 'P') {
                    if (pregnant == 10) {
                        pregnant = 0;
                        removeCapability(Breeding.pregnantFemale);
                        map.locationOf(this).addItem(this.produceEgg(this));
                        nextAction = wanderBehaviour.getAction(this,map);
                    } else {
                        pregnant++;
                        nextAction = new DoNothingAction();
                    }
                } else {
                    if (pregnant == 20) {
                        pregnant = 0;
                        removeCapability(Breeding.pregnantFemale);
                        map.locationOf(this).addItem(this.produceEgg(this));
                        nextAction = wanderBehaviour.getAction(this,map);
                    } else {
                        pregnant++;
                        nextAction = new DoNothingAction();
                    }
                }
            }


            // check if dinosaurs are thirsty
            if (this.waterLevels < 40) {
                if (this.getDisplayChar() == 'A' || this.getDisplayChar() == 'a') {
                    System.out.println("Allosaur at (" + x + "," + y + ") is getting thirsty");
                    behaviour = new ThirstyBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'P' || this.getDisplayChar() == 'p') {
                    System.out.println("Pterodactyl at (" + x + "," + y + ") is getting thirsty");
                    behaviour = new ThirstyBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'S' || this.getDisplayChar() == 's') {
                    System.out.println("Stegosaur at (" + x + "," + y + ") is getting thirsty");
                    behaviour = new ThirstyBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'B'|| this.getDisplayChar() == 'b'){
                    System.out.println("Brachiosaur at (" + x + "," + y + ") is getting thirsty");
                    behaviour = new ThirstyBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                }
            }

            // check if dinosaurs are hungry or need to hunt
            if (this.foodLevels < 30) {
                if (this.getDisplayChar() == 'A') {
                    System.out.println("Allosaur at (" + x + "," + y + ") is hunting");
                    behaviour = new HuntBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'P' || this.getDisplayChar() == 'p') {
                    System.out.println("Pterodactyl at (" + x + "," + y + ") is hungry");
                    behaviour = new HungerBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'S' || this.getDisplayChar() == 's') {
                    System.out.println("Stegosaur at (" + x + "," + y + ") is hungry");
                    behaviour = new HungerBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                } else if (this.getDisplayChar() == 'B'|| this.getDisplayChar() == 'b'){
                    System.out.println("Brachiosaur at (" + x + "," + y + ") is hungry");
                    behaviour = new HungerBehaviour(this);
                    nextAction = behaviour.getAction(this, map);
                }
            }

            //This is to check if breeding is eligible
            if (this.getDisplayChar() == 'B') {
                if (this.foodLevels > 70) {
                    if (this.gender == 'M') {
                        behaviour = new BreedBehaviour(this, Breeding.male);
                    } else {
                        behaviour = new BreedBehaviour(this, Breeding.female);

                    }
                    nextAction = behaviour.getAction(this, map);
                }
                if (nextAction == null) {
                    nextAction = wanderBehaviour.getAction(this,map);
                }
            } else {
                if (this.foodLevels > 50) {
                    if (this.gender == 'M') {
                        behaviour = new BreedBehaviour(this, Breeding.male);
                    } else {
                        behaviour = new BreedBehaviour(this, Breeding.female);
                    }
                    nextAction = behaviour.getAction(this, map);
                }
                if (nextAction == null) {
                    nextAction = wanderBehaviour.getAction(this,map);
                }
            }
            if (nextAction == null) {
                nextAction = wanderBehaviour.getAction(this,map);
            }
            return nextAction;

        } else {
            this.tick++;
            //This is to check if dinosaur is dead
            if (this.tick == 20) {
                Death deathAction = new Death();
                nextAction = deathAction;
                this.tick = 0;
                return nextAction;

            }

        }

        return new DoNothingAction();

    }


    /**
     * Egg created by Dinosaurs after breeding
     * @return egg with a baby dinosaur
     */
    public Eggs produceEgg(Dinosaur dinosaur) {
        Eggs egg = new Eggs(dinosaur);
        return egg;
    }

    /**
     * adjacent checks if the dinosaur is next to a food source or not so they can attack/eat
     * @param dinosaur is the dinosaur
     * @param player is the player
     * @param gameMap is the map of the app
     * @return returns true or false depending if the actors are next to each other
     */
    public boolean adjacent(Actor dinosaur, Actor player, GameMap gameMap) {
        Location currentLocation = gameMap.locationOf(dinosaur);

        for (Exit exit: currentLocation.getExits()) {
            if (gameMap.getActorAt(exit.getDestination()) == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * This gets all the players on the map
     * @param gameMap is the map
     * @return list of all the players
     */
    public Map<Actor, Location> getAllPlayers(GameMap gameMap) {

        Map<Actor, Location> playerList = new HashMap<>();
        for (int x: gameMap.getXRange()) {
            for (int y: gameMap.getYRange()) {
                Location location = gameMap.at(x,y);
                Actor actor = gameMap.getActorAt(location);
                if (actor instanceof Player) {
                    playerList.put(actor,location);
                }
            }
        }
        return playerList;
    }
 }





