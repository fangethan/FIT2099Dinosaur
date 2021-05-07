package game;

/**
 * This class is for eggs which extend food
 */
public class Eggs extends Food{
    /**
     * This is the cosntructor
     * display char is e
     */
    public Eggs() {
        super("Egg", 'e', true);
    }

    /**
     * THis gets the name
     * @return egg
     */
    public String getName(){
        return "Egg";
    }
}
