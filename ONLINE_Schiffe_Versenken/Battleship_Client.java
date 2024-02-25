import java.net.Inet4Address;
import java.net.UnknownHostException;

import basis.*;

public class Battleship_Client extends Client{

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT= 3333;
    private static final int MAIN_WIN_SIZE_X = 600;
    private static final int MAIN_WIN_SIZE_Y = 300;
    private static final int MAIN_WIN_POS_X = 20;
    private static final int MAIN_WIN_POS_Y = 20;

    private static String introductionS = "Client with IP: - %s -";

    private Fenster main_win;
    private String client_introString;

    public static void main(String[] args) {
        new Battleship_Client();
    }

    public Battleship_Client(){
        //Start Client
        super(SERVER_IP, SERVER_PORT);

        //Get informations for client_introString
        try {
            client_introString = String.format(introductionS, Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            // Auto-generated catch block
            client_introString = "";
            e.printStackTrace();
        }

        //Create GUI
        String heading = client_introString + " connecting to IP: - " + SERVER_IP + " - and Port: - " + SERVER_PORT + " -";
        main_win = new Fenster(heading, MAIN_WIN_SIZE_X, MAIN_WIN_SIZE_Y);
        main_win.setzePosition(MAIN_WIN_POS_X, MAIN_WIN_POS_Y);
    }

    public void processMessage(String pMessage){
        System.out.println("Received: " + pMessage);

        String command = pMessage.substring(0,7);
        String content = pMessage.substring(7);
        
        switch(command){
            case "ARRIVED":
                String heading = client_introString + " connected to IP: - " + SERVER_IP + " - and Port: - " + SERVER_PORT + " -";
                main_win.setzeTitel(heading);

                
            break;
        }
    }





    @Override
    public void send(String pMessage){
        System.out.println("Send: " + pMessage + "\n");
        super.send(pMessage);
    }
}
