import java.net.Inet4Address;
import java.net.UnknownHostException;

import basis.*;

public class Battleship_Client extends Client {

    private static final String SERVER_IP = "localhost";
    private static final int NAME_MIN_LENGHT = 3;
    private static final int NAME_MAX_LENGHT = 10;
    private static final String NAME_REGEX_STRING = String.format("[a-zA-Z0-9-]{%d,%d}", NAME_MIN_LENGHT,
            NAME_MAX_LENGHT);
    private static final int SERVER_PORT = 3333;

    private static final int MAIN_WIN_SIZE_X = 220;
    private static final int MAIN_WIN_SIZE_Y = 85;
    private static final int MAIN_WIN_POS_X = 20;
    private static final int MAIN_WIN_POS_Y = 20;

    private static final int GAME_WIN_SIZE_X = 600;
    private static final int GAME_WIN_SIZE_Y = 300;
    private static final int GAME_WIN_POS_X = 20;
    private static final int GAME_WIN_POS_Y = 20;

    private static String introductionS = "Client with IP: - %s -";
    private static String demand_name = "Please enter your name:";
    private static String name_too_short = "The name is to short:";
    private static String name_too_long = "The name is to long:";
    private static String name_is_invalid = "The name has invalid characters:";

    private Fenster main_win, game_win;
    private String client_introString, guiHeading;
    private TextFeld name_TextFeld;
    private BeschriftungsFeld name_BeschriftungsFeld;

    public static void main(String[] args) {
        new Battleship_Client();
    }

    public Battleship_Client() {
        // Start Client
        super(SERVER_IP, SERVER_PORT);

        // Get informations for client_introString
        try {
            client_introString = String.format(introductionS, Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            // Auto-generated catch block
            client_introString = "";
            e.printStackTrace();
        }

        // Create GUI
        guiHeading = client_introString + " connecting to IP: - " + SERVER_IP + " - and Port: - " + SERVER_PORT + " -";
        main_win = new Fenster(guiHeading, MAIN_WIN_SIZE_X, MAIN_WIN_SIZE_Y);
        main_win.setzePosition(MAIN_WIN_POS_X, MAIN_WIN_POS_Y);

        name_BeschriftungsFeld = new BeschriftungsFeld(demand_name, 10, 0, 200, 20);
        name_TextFeld = new TextFeld(10, 20, 200, 20);

        Knopf enter_Knopf = new Knopf("Enter", 10, 45, 200, 30);

        enter_Knopf.setzeKnopfLauscher(new KnopfLauscher() {
            @Override
            public void bearbeiteKnopfDruck(Knopf arg0) {
                if (!name_TextFeld.text().matches(NAME_REGEX_STRING)) {
                    if (name_TextFeld.text().length() < NAME_MIN_LENGHT) {
                        name_BeschriftungsFeld.setzeText(name_too_short);
                    } else if (name_TextFeld.text().length() > NAME_MAX_LENGHT) {
                        name_BeschriftungsFeld.setzeText(name_too_long);
                    } else {
                        name_BeschriftungsFeld.setzeText(name_is_invalid);
                    }
                } else {
                    send("MY_NAME" + name_TextFeld.text());

                }
            }
        });
    }

    public void processMessage(String pMessage) {
        System.out.println("Received: " + pMessage);

        String command = pMessage.substring(0, 7);
        String   = pMessage.substring(7);

        switch (command) {
            case "ARRIVED":
                guiHeading = client_introString + " connected to IP: - " + SERVER_IP + " - and Port: - " + SERVER_PORT
                        + " -";
                main_win.setzeTitel(guiHeading);
                break;

            case "NAME_OK":
                gameMain();
                main_win.setzeSichtbar(false);
                break;
        }
    }

    private void gameMain() {
        gameGUI();
    }

    private void gameGUI() {
        game_win = new Fenster(guiHeading, GAME_WIN_SIZE_X, GAME_WIN_SIZE_Y);
        game_win.setzePosition(GAME_WIN_POS_X, GAME_WIN_POS_Y);
        game_win.setzeFokus();
    }

    @Override
    public void send(String pMessage) {
        System.out.println("Send: " + pMessage + "\n");
        super.send(pMessage);
    }
}
