import basis.*;
public class Battleship_Server extends Server{

    public static void main(String[] args) {
       new Battleship_Server();
    }

    public Battleship_Server()
    {
        super(3333);
    }
    
    public void processNewConnection(String pClientIP, int pClientPort){
        send(pClientIP, pClientPort, "HELLO " + pClientIP + " " + pClientPort);
        new Fenster("Hello World!", 300, 300);
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage){
        
    }
    
    public void processClosingConnection(String pClientIP, int pClientPort){
        
    }
}
