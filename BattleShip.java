import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class BattleShip {
    public static int[][] CreateAreaGame() {
        Scanner Input = new Scanner(System.in);
        System.out.println("Hello, before we start, let's define the characteristics of the game");
        System.out.println("Select a numbers beetwen 5 - 10 for the play area");
        int AreaSpace = Input.nextInt();
        while (AreaSpace < 5 && AreaSpace > 10) {
            System.out.println("you\'ve a mistake , try over put a valid input");
            AreaSpace = Input.nextInt();
        }
        return new int[AreaSpace][AreaSpace];

    }

    public static void AddShip(int[][] Player, Scanner Input) {
    }

    public static void PlayGame(Scanner Input) {
        int[][] Player1AreaSpace, Player2AreaSpace = CreateAreaGame();

    }

    public static void HelpUser() {

    }

    public static void main(String[] arg) {
        String PresentationGameMensage = "welcome to BatleShip Game, if you are new , please write \"help\" , else write \"play\" ";
        System.out.println(PresentationGameMensage);
        Scanner Input = new Scanner(System.in);
        String IntroduccionUserInput = Input.nextLine();
        while (!IntroduccionUserInput.equals("play") &&
                !IntroduccionUserInput.equals("help")) {
            System.out.println("you\'ve a mistake , try over put a valid input");
            IntroduccionUserInput = Input.nextLine();
        }
        if (IntroduccionUserInput.equals("play"))
            PlayGame(Input);
        else {

            HelpUser();
        }

    }

}
