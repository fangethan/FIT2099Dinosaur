package game.Items;

import game.Dinosaur.Brachiosaur;

/**
 * This is a class for an BrachiosaurEgg which extends the egg item
        */
public class BrachiosaurEgg extends Eggs{
    /**
     * This is the cosntructor
     * display char is e
     *
     */
    public BrachiosaurEgg() {
        super(new Brachiosaur("Brachiosaur",'M'));
    }

    /**
     * This retruns the name of the class
     * @return String BrachiosaurEgg
     */
    public String getName(){
        return "Brachiosaur Egg";
    }
}
