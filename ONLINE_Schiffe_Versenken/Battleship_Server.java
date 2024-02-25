import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;

import basis.*;

public class Battleship_Server extends Server {

    private static final int SERVER_PORT = 3333;
    private static final int MAIN_WIN_SIZE_X = 600;
    private static final int MAIN_WIN_SIZE_Y = 300;
    private static final int MAIN_WIN_POS_X = 10;
    private static final int MAIN_WIN_POS_Y = 10;
    private static String introductionS = "Server with IP: - %s - and Port: - %d -";

    private ArrayList<Player> player_List = new ArrayList<Player>();

    private TextBereich console;

    public static void main(String[] args){
        new Battleship_Server();
    }

    public Battleship_Server(){
        //Start Server
        super(SERVER_PORT);
        
        //Get informations for server_introString
        String server_introString = "";
        try {
            server_introString = String.format(introductionS, Inet4Address.getLocalHost().getHostAddress(), SERVER_PORT);
        } catch (UnknownHostException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }

        //Create GUI
        Fenster main_win = new Fenster(server_introString, MAIN_WIN_SIZE_X, MAIN_WIN_SIZE_Y);
        main_win.setzePosition(MAIN_WIN_POS_X, MAIN_WIN_POS_Y);
        
        console = new TextBereich(10, 10, MAIN_WIN_SIZE_X - 20, MAIN_WIN_SIZE_Y - 20);
        console(server_introString, 2);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        player_List.add(new Player(pClientIP, pClientPort));
        send(pClientIP, pClientPort, "ARRIVED");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
    }

    private void console(String message, int line_breaks){
        console.setzeInNeueZeile(message);
        for(int i = 0; i < line_breaks; i++){
            console.setzeInNeueZeile("");
        }
    }

    @Override
    public void send(String pClientIP, int pClientPort, String pMessage){
        console("Send: " + pMessage + " to " + pClientIP + " " + pClientPort, 1);
        super.send(pClientIP, pClientPort, pMessage);
    }
}
