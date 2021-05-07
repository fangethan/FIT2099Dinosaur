package game;

public class EcoPoints {
    private static Player player;
    public static void setPlayer(Player player){
        EcoPoints.player = player;
    }
    public static void addPoints(int ecoPoints){
        player.addPoints(ecoPoints);
    }

}
