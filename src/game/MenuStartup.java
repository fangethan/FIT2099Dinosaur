package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.World;

import java.util.Scanner;

/**
 * This class starts up the program asking if challenge or sandbox
 */
public class MenuStartup {

    private final Display display;
    Scanner scanner = new Scanner(System.in);

    /**
     * THis is the constructor
     * @param display is the display used to create the world
     */
    public MenuStartup(Display display){
        this.display = display;
    }

    /**
     * This asks if the user wants a challenge or sandbox mode
     * Once they choose it returns a new challenge world or sandbox mode
     * @return challenge or sandbox world
     */
    public World initalMenu(){

        int selection;
        do{
            selection = selectMenuItem();
            switch (selection) {
                //Challenge
                case 1:
                    System.out.print("Number of moves:");
                    int inputMoves = Integer.parseInt(scanner.nextLine());
                    System.out.print("Number of EcoPoints:");
                    int inputEcoPoints = Integer.parseInt(scanner.nextLine());
                    return new Challenge(display,inputMoves,inputEcoPoints);
                case 2:
                    return new Sandbox(display);


            }

        } while(selection !=3);
        return null;
    }

    /**
     * This asks which game mode to play
     * @return  choice of the selection
     */
    public int selectMenuItem(){
        //This asks for user input to create a buyer or next car
        System.out.println("Choose a game mode");
        System.out.println("1) Challenge");
        System.out.println("2) Sandbox");
        System.out.println("3) Exit");
        System.out.print("Select your option: ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;

    }
}
