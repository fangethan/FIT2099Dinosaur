package game;

import edu.monash.fit2099.engine.*;

/**
 * This is the dinosaur abstract class
 * This extends the actor
 * This foodlevels, age and gender
 */
public abstract class Dinosaur extends Actor {
    public char gender;
    public int foodLevels = 80;
    public int waterLevels = 80;
    public int age = 32;
    private int tick = 0;
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

    public int getWaterLevels() {
        return waterLevels;
    }

    public void setWaterLevels(int waterLevels) {
        this.waterLevels = waterLevels;
    }

    public int getFoodLevels() {
        return foodLevels;
    }

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
        Probability value = new Probability();
        float randomNumber = value.getProbability();
        if (randomNumber< 0.2){
            RainAction rain = new RainAction(map);
            rain.rain();
        }



        Action nextAction = null;

        if (isConscious()) {
            //Reduces food level each turn
            foodLevels--;
            waterLevels--;

//            hatching(map);

//            Actor player = new Player("Player", '@', 100, new QuittingGame());
//            map.at(26, 12).addActor(player);
//            if (adjacent(this,player,map) == true) {
//                for (Item item : player.getInventory()) {
//                    if (item instanceof Food) {
//                        Feed feed = new Feed(this,(Food) item);
//                        feed.execute(player,map);
//                    }
//                }
//            }

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
                    if (pregnant == 1) {
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

//    private void hatching(GameMap map) {
//        for (int x: map.getXRange()) {
//            for (int y: map.getYRange()) {
//                Location location = map.at(x,y);
//                for (int i = 0; i < location.getItems().size(); i++) {
//                    if (location.getItems().get(i).getDisplayChar() == 'e') {
//                        location.removeItem(location.getItems().get(i));
//                        System.out.println(location.getItems().get(i).getClass().getName());
////                        location.addActor();
//                    }
//                }
//            }
//        }
//    }




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

}



