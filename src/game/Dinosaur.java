package game;

import edu.monash.fit2099.engine.*;

/**
 * This is the dinosaur abstract class
 * This extends the actor
 * This foodlevels, age and gender
 */
public abstract class Dinosaur extends Actor {
    public char gender;
    public int foodLevels = 52;
    public int age = 32;
    private int tick = 0;

    private Behaviour behaviour;
    private Behaviour wander = new WanderBehaviour();
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

//    public void setFoodLevel(int foodLevel) {
//        this.foodLevel = foodLevel;
//    }
//
//    public boolean isAdult(int limitAge) {
//        if (getAge() > limitAge) {
//            return true;
//        }
//        return false;
//    }

    /**
     * This checks is the dinosaur is conscious
     * @return true or false
     */
    @Override
    public boolean isConscious() {
        if (foodLevels > 0 || hitPoints > 0) {
            return true;
        }
        return false;
    }

    public void breedOption() {
        /*if (hasCapability() && this.foodLevel > 50) {
            behaviour = new BreedBehaviour(this);
        }*/
    }

    /**
     * This determines the actions of the dinsaour based on food levels and such
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        System.out.println("Food levels: " + foodLevels);
        Location location = map.locationOf(this);
        int x = location.x();
        int y = location.y();


        Action wander = behaviour.getAction(this, map);

        Action nextAction = null;

        if (isConscious()) {
            //Reduces food level each turn
            foodLevels--;
            //This is to check if breeding is elibible
            if (this.foodLevels > 50) {
                behaviour = new BreedBehaviour(this, Breeding.male);
                nextAction = behaviour.getAction(this, map);
                return nextAction;
            }

        } else {
            this.tick++;
            //This is to check if dinsour is dead
            if (this.tick == 20) {
                Death deathAction = new Death();
                nextAction = deathAction;
                this.tick = 0;
                return nextAction;
            }

        }
        if (nextAction == null) {
            return wander;
        }
        return new DoNothingAction();

    }
}
