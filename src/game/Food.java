package game;

import edu.monash.fit2099.engine.Item;

public abstract class Food extends Item {
    /**
     * The constructor of food
     *
     * @param name of the food
     * @param displayChar the display character on the game of the food
     * @param portable is if the food can be carried around
     */
    public Food(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
