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
        try{Thread.sleep(2000);}catch(Exception e){}

        player_List.add(new Player(pClientIP, pClientPort));
        send(pClientIP, pClientPort, "ARRIVED");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        console("Received: " + pMessage + " from " + pClientIP + " " + pClientPort, 1);
        
        String command = pMessage.substring(0,7);
        String content = pMessage.substring(7);
        
        switch(command){
            case "MY_NAME":
                searchPlayer(pClientIP, pClientPort).setName(content);
                send(pClientIP, pClientPort, "NAME_OK");
            break;
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
        player_List.remove(searchPlayer(pClientIP, pClientPort));
    }

    private void console(String message, int line_breaks){
        console.setzeInNeueZeile(message);
        for(int i = 0; i < line_breaks; i++){
            console.setzeInNeueZeile("");
        }
    }

    private Player searchPlayer(String pClientIP, int pClientPort){
        for(int i = 0; i < player_List.size(); i++){
            if(pClientIP.equals(player_List.get(i).getIP()) && pClientPort == player_List.get(i).getPort()){
                return player_List.get(i);
            }
        }
        return null;
    }

    @Override
    public void send(String pClientIP, int pClientPort, String pMessage){
        console("Send: " + pMessage + " to " + pClientIP + " " + pClientPort, 1);
        super.send(pClientIP, pClientPort, pMessage);
    }
}
