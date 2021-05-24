package game;

/**
 * This is the corpose class which creates a corpse on the map
 */
public class Corpse  extends Food {
    char species;
    /**
     * This is the constructor
     *
     */
    public Corpse(char species) {
        super("Corpse", 'c', false);
        this.species = species;
    }

    public char getSpecies() {
        return species;
    }
}
