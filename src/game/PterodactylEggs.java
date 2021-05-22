package game;

public class PterodactylEggs extends Eggs{
    /**
     * This is the cosntructor
     * display char is e
     *
     */
    public PterodactylEggs() {
        super(new Pterodactyls("Pterodactyl",'M'));
    }

    /**
     * getName gets the Pterodactyl egg name
     * @return the string PterodactylEggs
     */
    public String getName(){
        return "PterodactylEggs";
    }
}
