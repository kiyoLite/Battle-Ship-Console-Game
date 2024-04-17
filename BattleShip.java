import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import javax.swing.text.View;

class InformationShips {
    String Name;
    int Length;
    int Quantity;

    public InformationShips(String name, int Long, int Quantity) {
        this.Name = name;
        this.Length = Long;
        this.Quantity = Quantity;
    }

}

// main class
public class BattleShip {

    public static void HelpUser() {

    }

    public static int[][] CreateAreaGame() {
        Scanner Input = new Scanner(System.in);
        System.out.println("Hello, before we start, let's define the characteristics of the game");
        System.out.println("Select a numbers beetwen 5 - 10 for the play area");
        int AreaSpace = Input.nextInt();
        while (AreaSpace < 5 || AreaSpace > 10) {
            System.out.println("you\'ve a mistake , try over put a valid input");
            AreaSpace = Input.nextInt();
        }

        return new int[AreaSpace][AreaSpace];

    }

    public static void AddShiptoGameArea(int[][] Player, String ViewDirection,
            Hashtable<String, InformationShips> ShipsTypes, ArrayList<String> shipslist) {
        Scanner Input = new Scanner(System.in);
        for (String Ship : shipslist) {
            for (int i = 0; i < ShipsTypes.get(Ship).Quantity; i++) {
                System.out.println("select a column to put your " + Ship + " , remember , this ship takes up a : "
                        + ShipsTypes.get(Ship).Length + "units");
                int Column = Input.nextInt();
                System.out.println("select a row to put your " + Ship + " , remember , this ship takes up a :"
                        + ShipsTypes.get(Ship).Length + "units");
                int Row = Input.nextInt();
                if (ViewDirection.equals("right")) {
                    try {
                        for (int j = 0; j < ShipsTypes.get(Ship).Length; j++) {
                            Player[Row][Column + j] = 1;
                        }
                    } catch (Exception e) {
                        System.out.println("the" + ShipsTypes.get(Ship).Name
                                + "is so big and could enter in the area, you lose a ship");
                    }
                } else if (ViewDirection.equals("up")) {
                    try {
                        // there is an error here
                        for (int j = Row; j < ShipsTypes.get(Ship).Length + Row; j--) {
                            Player[Row][Column] = 1;
                        }
                    } catch (Exception e) {
                        System.out.println("the" + ShipsTypes.get(Ship).Name
                                + "is so big and could enter in the area, you lose a ship");
                    }

                }

            }
        }
    }

    public static void AddShip(int[][] player1, int[][] player2) {
        Hashtable<String, InformationShips> ShipsTypes = new Hashtable<>();
        ShipsTypes.put("canoe", new InformationShips("canoe", 2, 2));
        ShipsTypes.put("sailboat", new InformationShips("saileboat", 3, 1));
        ShipsTypes.put("aircraft Carrier", new InformationShips("aircraft Carrier", 5, 1));
        ArrayList<String> shipslist = new ArrayList<String>();
        shipslist.add("canoe");
        shipslist.add("aircraft Carrier");
        shipslist.add("sailboat");

        String ViewDirection = "right";
        System.out.println("both have this ships your store: aircraft Carrier, canoe, sailboat ");
        System.out.println("by default Player 1 start put your ships ");

        AddShiptoGameArea(player1, ViewDirection, ShipsTypes, shipslist);
        PrintGameArea(player1);
        System.out.println("Now player 2 have to put yours ships in the area");
        // AddShiptoGameArea(player2, ViewDirection, ShipsTypes, shipslist);

    }

    public static void TorpedoThrowing(int[][] Player) {
        Scanner Input = new Scanner(System.in);
        System.out.println("Select the  Row where you want throwing a Torpedo  ");
        int TorpedoRow = Input.nextInt();
        System.out.println("Select the  Column where you want throwing a Torpedo  ");
        int TorpedoColumn = Input.nextInt();
        System.out.println("throwing...");
        // simulate a cold down
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("had a error");
        }
        Hashtable<Integer, String> ThrowingTorpedoMensage = new Hashtable<>();
        ThrowingTorpedoMensage.put(0, "you didn't puch any");
        ThrowingTorpedoMensage.put(1, "you punched a ship ");
        ThrowingTorpedoMensage.put(-1, "dont punching the sames ships");
        Player[TorpedoRow][TorpedoColumn] = Player[TorpedoRow][TorpedoColumn] == 1 ? -1 : 0;
        System.out.println(ThrowingTorpedoMensage.get(Player[TorpedoRow][TorpedoColumn]));
    }

    public static void PrintGameArea(int[][] PlayerGameArea) {
        for (int i = 0; i < PlayerGameArea.length; i++) {
            System.out.println("");
            for (int j = 0; j < PlayerGameArea[i].length; j++) {
                System.out.print("|" + PlayerGameArea[i][j] + "|");
            }
        }
    }

    public static boolean AreThereShip(int[][] Player) {
        for (int i = 0; i < Player.length; i++) {
            if (Arrays.asList(Player[i]).contains(1))
                return true;

        }
        return false;
    }

    public static void PlayGame() {
        int[][] Save2Dmatrix = CreateAreaGame();
        int[][] Player1AreaSpace, Player2AreaSpace = Save2Dmatrix;
        Player1AreaSpace = Save2Dmatrix;
        AddShip(Player1AreaSpace, Player2AreaSpace);
        // System.out.println(Arrays.deepToString(Player1AreaSpace));

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
            PlayGame();
        else {

            HelpUser();
        }

    }

}
