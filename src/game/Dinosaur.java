package game;

import edu.monash.fit2099.engine.*;

public abstract class Dinosaur extends Actor {
    public char gender;
    public int foodLevels = 52;
    public int age = 32;
    private int tick = 0;

    private Behaviour behaviour;
    private Behaviour wander = new WanderBehaviour();

    public char getGender() {
        return gender;
    }

    public int getFoodLevel() {
        return foodLevels;
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
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints, char gender) {
        super(name, displayChar, hitPoints);
        this.behaviour = new WanderBehaviour();
        this.gender = gender;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        System.out.println("Food levels: " + foodLevels);
        Location location = map.locationOf(this);
        int x = location.x();
        int y = location.y();

//        this.foodLevels -= 1;
        //System.out.println(this.foodLevels);
        Action wander = behaviour.getAction(this, map);
        //Reduces food level each turn

		/*if (wander != null)
			return wander;

		return new DoNothingAction();
		*/


        Action nextAction = null;

        if (isConscious()) {
            foodLevels--;
            if (this.foodLevels > 50) {
                behaviour = new BreedBehaviour(this, Breeding.male);
                nextAction = behaviour.getAction(this, map);
                return nextAction;
            }

        } else {
            this.tick++;
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
