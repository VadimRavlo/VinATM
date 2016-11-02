package ua.com.wadyan.vinatm.Model;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class OneHundredUAH extends UAH{
    final static private int nominal = 100;
    private static int reminder;
    private static int toEject;

    public static int getNominal() {
        return nominal;
    }

    public static int getReminder() {
        return reminder;
    }

    public static void setReminder(int reminder) {
        OneHundredUAH.reminder = reminder;
    }

    public static int getToEject() {
        return toEject;
    }

    public static void setToEject(int toEject) {
        OneHundredUAH.toEject = toEject;
    }
}
