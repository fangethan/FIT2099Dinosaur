package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.World;

import java.util.Scanner;

public class MenuStartup {

    private final Display display;
    Scanner scanner = new Scanner(System.in);
    public MenuStartup(Display display){
        this.display = display;
    }
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
