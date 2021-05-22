package game;

/**
 * This class is for eggs which extend food
 */
public class Eggs extends Food{
    private char species;
    /**
     * This is the cosntructor
     * display char is e
     */
    public Eggs() {
        super("Egg", 'e', true);
    }

    public char getSpecies() {
        return species;
    }

    public void setSpecies(char species) {
        this.species = species;
    }

    /**
     * THis gets the name
     * @return egg
     */
    public String getName(){
        return "Egg";
    }
}
