import java.util.Random;

public class Battleship {

    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für ein Schiff der Länge %d ein: ";

    //Merhere static in richtiger und wichtiger reihenfolge
    /*static {
        SIZE = 10;
    }*/

    public static void main(String[] args) {
        while (true) {
            String test = getRandomCoordinate().toString();
            System.out.println(test);
        }
    }

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(((start.column() - end.column()))) + Math.abs((start.row() - end.row()));
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(new Random().nextInt(SIZE), new Random().nextInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return (start.column() == end.column() || start.row() == end.row());

    }

    static void showSeperatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.column(), end.column()) + 1, SIZE - 1);
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.row(), end.row()) + 1, SIZE - 1);
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.column(), end.column()) - 1, SIZE - SIZE);
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.row(), end.row()) - 1, SIZE - SIZE);
    }

    /*
     * static Coordinate toCoordinate(final String input){
     * return new Coordinate(input.toLowerCase().charAt(0) - 'a',
     * Character.getNumericValue(input.charAt(1)));
     * //return new Coordinate(input.toLowerCase().charAt(0) - 97,
     * Integer.parseInt(String.valueOf(input.charAt(1))) -1);
     * }
     */

    static Coordinate toCoordinate(final String input) {
        int column = input.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(column, row);
    }

    static boolean isValidCoordinate(final String input) {
        return input.toLowerCase().matches("[a-j](10|[1-9])");
    }

    static String getStartCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Start", length);
    }

    static String getEndCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "End", length);
    }

    static void isValid() {
        // regex = regular expression
        String doubleChar = "[A-Z]{2}";
        String tor = "To+r!";
        String variableValid = "[A-Z|a-z|_]+[0-9|A-Z|a-z|_]";
        String variableValid2 = "[a-zA-Z_$][a-zA-Z_$0-9]*";
        String age = "[1-9]+[0-9]*[m|w|d]";
        String age2 = "[1-9][0-9]*[mwd]";

        String test = "Toooor!";
        System.out.println(test.matches(doubleChar));
        System.out.println(test.matches(tor));
        System.out.println(test.matches(variableValid));
        System.out.println(test.matches(variableValid2));
        System.out.println(test.matches(age));
        System.out.println(test.matches(age2));
    }
}