package MUPRG1;

public class Sum {
    public static void main(String[] args) {
        int result = 0;

        for(int i = 0; i < args.length; i++){
            int zahl = 0;
            try {
                zahl = Integer.valueOf(args[i]);
            }catch(Exception e){
                System.out.println("\nDie Variable war keine Zahl!  =>  " + args[i] + "\n");
            }

            result += zahl;
        }

        System.out.println("" + result);
    }
}
