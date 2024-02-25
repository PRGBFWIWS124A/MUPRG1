import java.util.Random;

public class Battleship {

    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für ein Schiff der Länge %d ein: ";

    // Merhere static in richtiger und wichtiger reihenfolge
    /*
     * static {
     * SIZE = 10;
     * }
     */

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

    static void showRowNumber(final int row) {
        if (row + 1 < 10)
            System.out.print(" ");
        System.out.print(row + 1);
    }

    static String gerade(final int points) {
        if (points > 0 || points < 100) {
            return "Ungültige Punktzahl";
        }
        if (points >= 97) {
            return "1,0";
        }
        if (points >= 92) {
            return "1,3";
        }
        if (points >= 89) {
            return "1,7";
        }
        if (points >= 85) {
            return "2,0";
        }
        if (points >= 81) {
            return "2,3";
        }
        if (points >= 77) {
            return "2,7";
        }
        if (points >= 72) {
            return "3,0";
        }
        if (points >= 67) {
            return "3,3";
        }
        if (points >= 59) {
            return "3,7";
        }
        if (points >= 50) {
            return "4,0";
        }

        return "5,0";
    }

    static Coordinate getRandomCoordinate(final Coordinate start, final int distance) {
        int choices = 0;
        if (start.column() - distance >= 0) {
            choices++;
        }
        if (start.column() + distance <= SIZE) {
            choices++;
        }
        if (start.row() - distance >= 0) {
            choices++;
        }
        if (start.row() + distance <= SIZE) {
            choices++;
        }

        int choice = new Random().nextInt(choices);

        if (start.column() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() - distance, start.row());
            }
        }
        if (start.column() + distance <= SIZE) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() + distance, start.row());
            }
        }
        if (start.row() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column(), start.row() - distance);
            }
        }
        return new Coordinate(start.column(), start.row() + distance);
    }

    static void showField(final Field field, final boolean showShips) {

        switch (field) {
            case SHIP:
                System.out.print(showShips == true ? "O" : " ");
                break;

            case FREE:
                System.out.print(" ");
                break;

            case WATER_HIT:
                System.out.print("X");
                break;

            case SHIP_HIT:
                System.out.print("*");
                break;
        }
    }

    static void shot(final Coordinate shot, final Field[][] field) {
        switch (field[shot.row()][shot.column()]) {
            case SHIP:
                field[shot.row()][shot.column()] = Field.SHIP_HIT;
                break;

            case FREE:
                field[shot.row()][shot.column()] = Field.WATER_HIT;
                break;

            case WATER_HIT:
                // NICHTS (ICH WEISS DIESER CASE IST UNNOETIG)
                break;

            case SHIP_HIT:
                // NICHTS (ICH WEISS DIESER CASE IST UNNOETIG)
                break;
        }
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field) {
        for (int i = Math.min(start.row(), end.row()); i < Math.max(start.row(), end.row()); i++) {
            for (int j = Math.min(start.column(), end.column()); i < Math.max(start.column(), end.column()); i++) {
                field[i][j] = Field.SHIP;
            }
        }
    }

    // Uebungen
    static int max(final int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(array[i], max);
        }
        return max;
    }
}