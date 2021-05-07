package game;

/**
 * This is used to update ecoPoints
 */
public class EcoPoints {
    private static Player player;

    /**
     * This sets the player
     * @param player is the player
     */
    public static void setPlayer(Player player){
        EcoPoints.player = player;
    }

    /**
     * This adds points to the player
     * @param ecoPoints
     */
    public static void addPoints(int ecoPoints){
        player.addPoints(ecoPoints);
    }

}
