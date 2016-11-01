package ua.com.wadyan.vinatm;


import ua.com.wadyan.vinatm.Model.User;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class GlobalConstVar {
    public static final int ALLOWED = 0;
    public static final int EXCEEDED_DAILY_LIMIT = 1;
    public static final int EXCEEDED_ATM_CASH_REMINDER = 2;
    public static final int EXCEEDED_USER_MONEY_REMINDER = 3;
    public static final int EXCEEDED_BANKNOTE = 4;
    public static final int EXCEEDED_MAX_COUNT = 5;

    public static final int MAX_DAY_LIMIT = 550;

    private static User currentUser;


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        GlobalConstVar.currentUser = currentUser;
    }
}
