import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

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

    public static int InputUser(int MinHopeValue, int MaxHopeValue, String Message) {
        System.out.println(Message);
        Scanner Sc = new Scanner(System.in);
        int Input = Sc.nextInt();
        while (Input < MinHopeValue || Input > MaxHopeValue) {
            System.out.println("you have to enter a valild input");
            Input = Sc.nextInt();
        }
        return Input;
    }

    public static void HelpUser() {

    }

    public static int CreateAreaGame() {

        System.out.println("Hello, before we start, let's define the characteristics of the game");

        int AreaSpace = InputUser(5, 10, "Select a numbers beetwen 5 - 10 for the play area");

        return AreaSpace;

    }

    public static void AddShiptoGameArea(int[][] Player, String ViewDirection,
            Hashtable<String, InformationShips> ShipsTypes, ArrayList<String> shipslist) {
        Scanner Input = new Scanner(System.in);
        for (String Ship : shipslist) {
            for (int i = 0; i < ShipsTypes.get(Ship).Quantity; i++) {
                int Column = InputUser(0, Player[0].length,
                        "select a column to put your " + Ship + " , remember , this ship takes up a : "
                                + ShipsTypes.get(Ship).Length + " units");

                int Row = InputUser(0, Player[0].length,
                        "select a Row to put your " + Ship + " , remember , this ship takes up a : "
                                + ShipsTypes.get(Ship).Length + " units");
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
                        for (int j = 0; j < ShipsTypes.get(Ship).Length; j++) {
                            Player[Row - j][Column] = 1;
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

        System.out.println("Now player 2 have to put yours ships in the area");
        AddShiptoGameArea(player2, ViewDirection, ShipsTypes, shipslist);

    }

    public static String TorpedoThrowing(int[][] SelectPlayer, int Counter, int[][] Player1, int[][] Player2) {
        System.out.println("------Player" + Counter + "-----");
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
        System.out.println(ThrowingTorpedoMensage.get(SelectPlayer[TorpedoRow][TorpedoColumn]));
        SelectPlayer[TorpedoRow][TorpedoColumn] = SelectPlayer[TorpedoRow][TorpedoColumn] == 1 ? -1 : 0;
        if (AreThereShip(SelectPlayer) == false)
            return "player" + Counter + "win";
        Counter = Counter > 1 ? --Counter : ++Counter;
        if (Counter > 1)
            return TorpedoThrowing(Player1, Counter, Player1, Player2);
        return TorpedoThrowing(Player2, Counter, Player1, Player2);
    }

    public static void PrintGameArea(int[][] PlayerGameArea) {
        for (int i = 0; i < PlayerGameArea.length; i++) {
            System.out.println("");
            for (int j = 0; j < PlayerGameArea[i].length; j++) {
                System.out.print("|" + PlayerGameArea[i][j] + "|");
            }
        }
        System.out.println("");
    }

    public static boolean AreThereShip(int[][] Player) {
        for (int[] i : Player) {
            for (int j : i) {
                if (j == 1)
                    return true;
            }
        }
        return false;
    }

    public static void PlayGame() {
        int CreateAreaGame = CreateAreaGame();
        int[][] Player1AreaSpace = new int[CreateAreaGame][CreateAreaGame];
        int[][] Player2AreaSpace = new int[CreateAreaGame][CreateAreaGame];
        AddShip(Player1AreaSpace, Player2AreaSpace);
        String WinMensage = TorpedoThrowing(Player2AreaSpace, 1, Player1AreaSpace, Player2AreaSpace);
        System.out.println(WinMensage);
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
