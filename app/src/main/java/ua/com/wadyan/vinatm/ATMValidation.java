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

public class ATMValidation {
    private int sumCash;
    private int possibilityID = GlobalConstVar.EXCEEDED_BANKNOTE;
    User user = GlobalConstVar.getCurrentUser();

    public int possibilityGetMoney(int userSum){
        resetCashToEject();
        maxUserMoneyReminder(userSum);
        maxATMCashReminderValidation(userSum);
        maxDayLimitValidation(userSum);
        maxCountValidation();
        maxBanknote(userSum);
        return possibilityID;
    }

    void resetCashToEject(){
        FiftyUAH.setToEject(0);
        OneHundredUAH.setToEject(0);
        TwoHundredUAH.setToEject(0);
        FiveHundredUAH.setToEject(0);
    }

    void maxUserMoneyReminder(int userSum){
        if(userSum > user.getMoneyBalance()){
            possibilityID = GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER;
        }
    }

    void maxATMCashReminderValidation(int userSum){
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER) {
            sumCash = FiftyUAH.getReminder() * FiftyUAH.getNominal()
                    + OneHundredUAH.getReminder() * OneHundredUAH.getNominal()
                    + TwoHundredUAH.getReminder() * TwoHundredUAH.getNominal()
                    + FiveHundredUAH.getReminder() * FiveHundredUAH.getNominal();
            Log.d(GlobalConstVar.LOG_TAG, "sumCash = " + sumCash);
            if (userSum > sumCash) possibilityID = GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER;
        }
    }

    void maxDayLimitValidation(int userSum) {
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER) {
            if (userSum > GlobalConstVar.MAX_DAY_LIMIT){
                possibilityID = GlobalConstVar.EXCEEDED_DAILY_LIMIT;
            }
        }
    }

    void maxCountValidation(){
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_DAILY_LIMIT) {
            if (user.getCountGetMoneyDay() > 1) possibilityID = GlobalConstVar.EXCEEDED_MAX_COUNT;
        }
    }

    void maxBanknote(int userSum){
        int tmpSum = userSum;

        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_DAILY_LIMIT
                && possibilityID != GlobalConstVar.EXCEEDED_MAX_COUNT) {
            while (((tmpSum - FiveHundredUAH.getNominal()) >= 0) && (FiveHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - FiveHundredUAH.getNominal();
                FiveHundredUAH.setToEject(FiveHundredUAH.getToEject() + 1);
                FiveHundredUAH.setReminder(FiveHundredUAH.getReminder() - 1);
            }
            while (((tmpSum - TwoHundredUAH.getNominal()) >= 0) && (TwoHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - TwoHundredUAH.getNominal();
                TwoHundredUAH.setToEject(TwoHundredUAH.getToEject() + 1);
                TwoHundredUAH.setReminder(TwoHundredUAH.getReminder() - 1);
            }
            while (((tmpSum - OneHundredUAH.getNominal()) >= 0) && (OneHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - OneHundredUAH.getNominal();
                OneHundredUAH.setToEject(OneHundredUAH.getToEject() + 1);
                OneHundredUAH.setReminder(OneHundredUAH.getReminder() - 1);
            }
            while (((tmpSum - FiftyUAH.getNominal()) >= 0) && (FiftyUAH.getReminder() > 0)) {
                tmpSum = tmpSum - FiftyUAH.getNominal();
                FiftyUAH.setToEject(FiftyUAH.getToEject() + 1);
                FiftyUAH.setReminder(FiftyUAH.getReminder() - 1);
            }
            if (tmpSum == 0) {
                possibilityID = GlobalConstVar.ALLOWED;
                user.setCountGetMoneyDay(user.getCountGetMoneyDay() + 1);
                user.setMoneyBalance(user.getMoneyBalance() - userSum);
            } else possibilityID = GlobalConstVar.EXCEEDED_BANKNOTE;
        }
    }
}
