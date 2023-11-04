package doctrina;

public class Text {
    //ramène la couleur en blanc
    public static final String RESET = (char)27 + "[0m";

    public static final String BOLD = (char)27 + "[1m";
    public static String bold(String message) {
        return BOLD + message + RESET;
    }

    public static final String ITALIQUE = (char)27 + "[3m";
    public static String italique(String message) {
        return ITALIQUE + message + RESET;
    }

    public static final String UNDERLINE = (char)27 + "[4m";
    public static String underline(String message) {
        return UNDERLINE + message + RESET;
    }

    public static final String REVERSERD = (char)27 + "[7m";
    public static String reverse(String message) {
        return REVERSERD + message + RESET;
    }

    public static final String STRIPE = (char)27 + "[9m";
    public static String stripe(String message) {
        return STRIPE + message + RESET;
    }

    public static final String UNDERLINE_BOLD = (char)27 + "21m";
    public static String underlineBold(String message) {
        return UNDERLINE_BOLD + message + RESET;
    }

    public static final String BLACK = (char)27 + "[30m";
    public static String black(String message) {
        return BLACK + message + RESET;
    }

    public static final String RED = (char)27 + "[31m";
    public static String red(String message) {
        return RED + message + RESET;
    }

    public static final String GREEN = (char)27 + "[32m";
    public static String green(String message) {
        return GREEN + message + RESET;
    }

    public static final String YELLOW = (char)27 + "[33m";
    public static String yellow(String message) {
        return YELLOW + message + RESET;
    }

    public static final String BLUE = (char)27 + "[34m";
    public static String blue(String message) {
        return BLUE + message + RESET;
    }

    public static final String PURPLE = (char)27 + "[35m";
    public static String purple(String message) {
        return PURPLE + message + RESET;
    }

    public static final String CYAN = (char)27 + "[36m";
    public static String cyan(String message) {
        return CYAN + message + RESET;
    }

    public static final String LIGHT_CYAN = (char)27 + "[36m;1m";
    public static String lightCyan(String message) {
        return LIGHT_CYAN + message + RESET;
    }

    public static final String GREY = (char)27 + "[37m";
    public static String grey(String message) {
        return GREY + message + RESET;
    }
}

