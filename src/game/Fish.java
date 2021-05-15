package game;

public class Fish extends Food{

    /**
     * The constructor of food
     *
     * @param name        of the food
     * @param displayChar the display character on the game of the food
     * @param portable    is if the food can be carried around
     */
    public Fish() {
        super("Fish", 'F',false);
    }
}
