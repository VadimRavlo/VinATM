package ua.com.wadyan.vinatm.Model;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

abstract class UAH {
    private int nominal;
    private int reminder;
    private int toEject;

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getToEject() {
        return toEject;
    }

    public void setToEject(int toEject) {
        this.toEject = toEject;
    }
}
