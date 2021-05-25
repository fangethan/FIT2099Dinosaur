package game.Items;

import game.Dinosaur.Allosaur;

/**
 * This is a class for an allosaurEgg which extends the egg item
 */
public class AllosaurEgg extends Eggs{

    /**
     * This is the cosntructor
     * display char is e
     *
     */
    public AllosaurEgg() {
        super(new Allosaur("Allosaur",'M'));
    }

    /**
     * THis is a method getting the name of the class
     * @return String'allosaur Egg'
     */
    public String getName(){
        return "Allosaur Egg";
    }
}
