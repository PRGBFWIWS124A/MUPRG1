public class Battleship_Client extends Client{
    
    public static void main(String[] args) {
        new Battleship_Client();
    }

    public Battleship_Client(){
        super("localhost",3333);
    }

    public void processMessage(String pMessage){
        
    }
}
