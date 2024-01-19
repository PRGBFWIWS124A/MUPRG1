package MUPRG1;

public class Typen {
    public static void main(String args[]){
        int x = 120, y = 2, z = 3;

        //Java macht Punkt vor Strich
        System.out.println(x + y * z);

        // Da int als erstes ist, wird das char in ascii geparst und ist daher 49 
        //-2147483600 weil Zahl zu groß für int, so dass es von Minus anfängt und
        //dann + 48 gerechnet wird
        System.out.println(2147483647 + '1');

        //Castet zu Byte nachdem das Long mit int addiert wurde und zum long wird
        System.out.println((byte) 1 + (2147483648L));

        //Da der erste Typ ein String ist, wird alles an einander gehangen
        //weil Java von links nach rechts vorgeht
        System.out.println("x" + y + z);

        //Behandelt das als (x < y) && (true)
        System.out.println(x < y && true);

        //Double als type da dieser größer ist als float und hochkonvertiert wird
        System.out.println(9f / 3d);

        //Typ Fehler - ist bei String nicht definiert
        //System.out.println(x + "y" - z);

        //Wird ein float
        System.out.println(1 + 3f);
        System.out.println(((Object)(1 + 3f)).getClass().getName());

        //Wenn das vorm ? true ist dann wird das vor dem Doppelpunkt ausgegeben
        //ansonsten das hinter dem Doppelpunkt
        //Da der vergleich false ergibt ('x' ist 120) wird 1 als float ausgegeben
        //weil der sieht das der erste Typ ein float ist
        System.out.println(x != 'x' ? 2f : 1);
        System.out.println(((Object)(x != 'x' ? 2f : 1)).getClass().getName());

        //Boolean true, da vor dem 'oder' das true steht ist das auf der rechten seite egal
        System.out.println(true || 18 < 2 + 3 * 5 && 1 + 3 < 2);

        
    }
    
}
