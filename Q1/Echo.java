package MUPRG1;

public class Echo {
    public static void main(String[] args) {
        String ausgabe = "";
        for(int i = 0; i < args.length; i++){
            ausgabe+= args[i] + " ";
        }
        System.out.println(ausgabe);
    }
}
