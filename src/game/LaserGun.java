package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * LaserGun is an item that can be used by the player to shoot at other actors
 */
public class LaserGun extends WeaponItem {
    /**
     * constructor LaserGun
     */
    public LaserGun() {
        super("Laser Gun", 'L', 90, "shoots");
    }

    /**
     * getName gets the laser gun name
     * @return the string Laser Gun
     */
    public String getName(){
        return "Laser Gun";
    }
}
