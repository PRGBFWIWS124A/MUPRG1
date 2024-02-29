import java.util.UUID;

public class Player {
    private final UUID uuid = UUID.randomUUID();
    private String ip;
    private String name = "";
    private int port;
    private int score = 0;

    public Player(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public String getIP(){return ip;}

    public int getPort(){return port;}

    public String getName(){return name;}

    public int getScore(){return score;}

    public String getUUID(){return uuid.toString();}


    public void setIP(String ip){this.ip = ip;}

    public void setPort(int port){this.port = port;}

    public void setName(String name){this.name = name;}

    public void setScore(int score){this.score = score;}

}
