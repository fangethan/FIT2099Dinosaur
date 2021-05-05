package game;

import edu.monash.fit2099.engine.*;

public class Dinosaur extends Actor {
    private char gender;
    private int foodLevel;
    private int age;

    private Behaviour behaviour;
    private Behaviour wander = new WanderBehaviour();

    public char getGender() {
        return gender;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

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


    @Override
    public boolean isConscious() {
        return foodLevel > 0 && hitPoints > 0;
    }

    public void breedOption() {
        if (hasCapability() && this.foodLevel > 50) {
            behaviour = new BreedBehaviour(this);
        }
    }

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
