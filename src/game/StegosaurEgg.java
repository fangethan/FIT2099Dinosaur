package game;

public class StegosaurEgg extends Eggs{
    /**
     * This is the cosntructor
     * display char is e
     *
     */
    public StegosaurEgg() {
        super(new Stegosaur("Stegosaur",'M'));
    }

    /**
     * getName gets the Stegosaur egg name
     * @return the string StegosaurEggs
     */
    public String getName(){
        return "StegosaurEggs";
    }

}
