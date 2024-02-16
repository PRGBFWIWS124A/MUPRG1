import java.net.Inet4Address;
import java.net.UnknownHostException;

import basis.*;

public class Battleship_Server extends Server {

    private static final int server_port = 3333;
    private static final int MAIN_WIN_SIZE_X = 600;
    private static final int MAIN_WIN_SIZE_Y = 300;
    private static String indroductionS = "Server with IP: - %s - and Port: - %d -";

    private TextBereich console;

    public static void main(String[] args){
        new Battleship_Server();
    }

    public Battleship_Server(){
        super(server_port);

        String server_introString = "";
        try {
            server_introString = String.format(indroductionS, Inet4Address.getLocalHost().getHostAddress(), server_port);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Fenster main_win = new Fenster(server_introString, MAIN_WIN_SIZE_X, MAIN_WIN_SIZE_Y);
        
        console = new TextBereich(10, 10, MAIN_WIN_SIZE_X - 20, MAIN_WIN_SIZE_Y - 20);
        console(server_introString, 2);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        console("HELLO THERE: " + pClientIP + " " + pClientPort, 1);
        send(pClientIP, pClientPort, "Hello");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {

    }

    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    private void console(String message, int number_line_breaks){
        console.setzeInNeueZeile(message);
        for(int i = 0; i < number_line_breaks; i++){
            console.setzeInNeueZeile("");
        }
    }

    @Override
    public void send(String pClientIP, int pClientPort, String pMessage){
        console("Send: " + pMessage + " to " + pClientIP + " " + pClientPort, 1);
        super.send(pClientIP, pClientPort, pMessage);
    }
}
