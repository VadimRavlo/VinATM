package ua.com.wadyan.vinatm.Model;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

abstract class UAH {
    private static int nominal;
    private static int reminder;
    private static int toEject;

    public static int getNominal() {
        return nominal;
    }

    public static int getReminder() {
        return reminder;
    }

    public static void setReminder(int reminder) {
        UAH.reminder = reminder;
    }

    public static int getToEject() {
        return toEject;
    }

    public static void setToEject(int toEject) {
        UAH.toEject = toEject;
    }
}
