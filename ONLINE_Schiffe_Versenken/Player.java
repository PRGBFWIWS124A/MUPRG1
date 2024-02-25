public class Player {
    
    private String ip, name;
    private int port, score;

    public Player(String ip, int port, String name){
        this.ip = ip;
        this.port = port;
        this.name = name;
        score = 0;
    }

    public String getIP(){return ip;}

    public int getPort(){return port;}

    public String getName(){return name;}

    public int getScore(){return score;}


    public void setIP(String ip){this.ip = ip;}

    public void setPort(int port){this.port = port;}

    public void setName(String name){this.name = name;}

    public void setScore(int score){this.score = score;}

}
