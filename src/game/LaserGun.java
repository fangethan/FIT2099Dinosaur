package game;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {
    public LaserGun() {
        super("Laser Gun", 'L', 90, "shoots");
    }
    public String getName(){
        return "Laser Gun";
    }
}
