package ua.com.wadyan.vinatm.Model;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class TwoHundredUAH extends UAH{
    final static private int nominal = 200;
    private static int reminder;
    private static int toEject;

    public static int getNominal() {
        return nominal;
    }

    public static int getReminder() {
        return reminder;
    }

    public static void setReminder(int reminder) {
        TwoHundredUAH.reminder = reminder;
    }

    public static int getToEject() {
        return toEject;
    }

    public static void setToEject(int toEject) {
        TwoHundredUAH.toEject = toEject;
    }
}
