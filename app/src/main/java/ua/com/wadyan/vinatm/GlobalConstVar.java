package ua.com.wadyan.vinatm;


import android.util.Log;

import ua.com.wadyan.vinatm.Model.FiftyUAH;
import ua.com.wadyan.vinatm.Model.FiveHundredUAH;
import ua.com.wadyan.vinatm.Model.OneHundredUAH;
import ua.com.wadyan.vinatm.Model.TwoHundredUAH;
import ua.com.wadyan.vinatm.Model.User;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class GlobalConstVar {
    public static final String LOG_TAG = "Wad";
    public static final int ALLOWED = 0;
    public static final int EXCEEDED_USER_MONEY_REMINDER = 1;
    public static final int EXCEEDED_ATM_CASH_REMINDER = 2;
    public static final int EXCEEDED_DAILY_LIMIT = 3;
    public static final int EXCEEDED_MAX_DAY_COUNT = 4;
    public static final int EXCEEDED_BANKNOTE = 5;
    public static final int START_ID = 9;
    public static final int MAX_DAY_LIMIT = 550;

    private static User[] userList = new User[5];
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        GlobalConstVar.currentUser = currentUser;
    }

    public static User[] getUserList() {
        return userList;
    }

//    public static void setUserList(User[] userList) {
//        GlobalConstVar.userList = userList;
//    }

    public static void createUsers() {
        for (int i = 0; i < 5; i++) {
            userList[i] = new User("user" + i, "qwerty" + i, i * 250);
            Log.d("Wad", "User" + i + " created");
        }
    }

    public static void fillingATM(){
        if ((FiftyUAH.getReminder() == 0) && (OneHundredUAH.getReminder() == 0)
                && (TwoHundredUAH.getReminder() == 0) && (FiveHundredUAH.getReminder() == 0)) {
            Log.d(LOG_TAG, "Банкомат заповнився готівкою");
            FiftyUAH.setReminder(2);
            OneHundredUAH.setReminder(2);
            TwoHundredUAH.setReminder(2);
            FiveHundredUAH.setReminder(2);
        }
    }
}
