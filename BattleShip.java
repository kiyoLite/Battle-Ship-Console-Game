import java.util.Hashtable;
import java.util.Scanner;

public class BattleShip {
    public static void PlayGame() {

    }

    public static void HelpUser() {

    }

    public static void main(String[] arg) {
        String PresentationGameMensage = "welcome to BatleShip Game, if you are new , please write \"help\" , else write \"play\" ";
        System.out.println(PresentationGameMensage);
        Scanner Input = new Scanner(System.in);
        String IntroduccionUserInput = Input.nextLine();
        while (!IntroduccionUserInput.equals("play") ||
                !IntroduccionUserInput.equals("help")) {
            System.out.println("you\'ve a mistake , try over put a valid input");
            IntroduccionUserInput = Input.nextLine();
        }
        if (IntroduccionUserInput.equals("play"))
            PlayGame();
        else {

            HelpUser();
        }

    }

}