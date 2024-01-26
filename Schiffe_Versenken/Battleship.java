import java.util.Random;

public class Battleship {

    static int size = 10;

    public static void main(String[] args) {
        while (true) {
            System.out.println(getRandomCoordinate().toString());
        }
    }

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(((start.column() - end.column()) + (start.row() - end.row())));
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(new Random().nextInt(size), new Random().nextInt(size));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return (start.column() == end.column() || start.row() == end.row());

    }

    static void showSeperatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+          +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.column(), end.column()) + 1, size - 1);
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.row(), end.row()) + 1, size - 1);
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.column(), end.column()) - 1, size - size);
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.row(), end.row()) - 1, size - size);
    }

    static Coordinate toCoordinate(final String input){
        return new Coordinate(input.toLowerCase().charAt(0) - 97, Character.getNumericValue(input.charAt(1)));
    }

    static boolean isValidCoordinate(final String input){
        return input.matches("[A-J][1-9]0*");
    }




    static void isValid(){
        String doubleChar = "[A-Z]{2}";
        String tor = "To+r!";
        String variableValid = "[A-Z|a-z|_]+[0-9|A-Z|a-z|_]";
        String age = "[1-9]+[0-9]*[m|w|d]";

        String test = "Toooor!";
        System.out.println(test.matches(tor));
    }
}