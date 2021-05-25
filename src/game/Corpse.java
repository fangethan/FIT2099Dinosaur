package game;

/**
 * This is the corpose class which creates a corpse on the map
 */
public class Corpse  extends Food {
    private char species;
    /**
     * This is the constructor
     * @param species which receives the species of dinosaur
     *
     */
    public Corpse(char species) {
        super("Corpse", 'c', false);
        this.species = species;
    }

    /**
     * getSpecies which gets the species of dinosaur
     *
     */
    public char getSpecies() {
        return species;
    }
}
