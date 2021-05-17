package game;

import edu.monash.fit2099.engine.Menu;
import java.util.Scanner;

public class MenuStartup extends Menu {
    Scanner scanner = new Scanner(System.in);
    public MenuStartup(){

    }
    public void initalMenu(){

        int selection;
        do{
            selection = selectMenuItem();
            switch (selection) {
                //Challenge
                case 1:
                    System.out.print("Number of moves:");
                    String inputMoves = scanner.nextLine();
                    System.out.print("Number of EcoPoints:");
                    int inputEcoPoints = Integer.parseInt(scanner.nextLine());
                    //world = new ChallengeWorld();
                    break;
                case 2:

                    break;

            }

        } while(selection !=3);
    }
    public int selectMenuItem(){
        //This asks for user input to create a buyer or next car
        System.out.println("CHoose a game mode");
        System.out.println("1) Challenge");
        System.out.println("2) Sandbox");
        System.out.println("3) Exit");
        System.out.print("Select your option: ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;

    }
}
