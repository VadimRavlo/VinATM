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
    private int possibilityID = GlobalConstVar.START_ID;
    private User user = GlobalConstVar.getCurrentUser();

    public int possibilityGetMoney(int userSum){
        resetCashToEject();
        maxUserMoneyReminder(userSum);
        maxATMCashReminderValidation(userSum);
        maxDayLimitValidation(userSum);
        maxCountValidation();
        maxBanknote(userSum);
        return possibilityID;
    }

    private void resetCashToEject(){
        FiftyUAH.setToEject(0);
        OneHundredUAH.setToEject(0);
        TwoHundredUAH.setToEject(0);
        FiveHundredUAH.setToEject(0);
    }

    private void maxUserMoneyReminder(int userSum){
        if(userSum > user.getMoneyBalance()){
            possibilityID = GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER;
        }
    }

    private void maxATMCashReminderValidation(int userSum){
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER) {
            sumCash = FiftyUAH.getReminder() * FiftyUAH.getNominal()
                    + OneHundredUAH.getReminder() * OneHundredUAH.getNominal()
                    + TwoHundredUAH.getReminder() * TwoHundredUAH.getNominal()
                    + FiveHundredUAH.getReminder() * FiveHundredUAH.getNominal();
            Log.d(GlobalConstVar.LOG_TAG, "sumCash = " + sumCash);
            if (userSum > sumCash) possibilityID = GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER;
        }
    }

    private void maxDayLimitValidation(int userSum) {
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER) {
            if (userSum > GlobalConstVar.MAX_DAY_LIMIT){
                possibilityID = GlobalConstVar.EXCEEDED_DAILY_LIMIT;
            }
        }
    }

    private void maxCountValidation(){
        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_DAILY_LIMIT) {
            if (user.getCountGetMoneyDay() > 1) possibilityID = GlobalConstVar.EXCEEDED_MAX_DAY_COUNT;
        }
    }

    private void maxBanknote(int userSum){
        int tmpSum = userSum;
        int tmp50Count = 0, tmp100Count = 0, tmp200Count = 0,  tmp500Count = 0;

        if (possibilityID != GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER
                && possibilityID != GlobalConstVar.EXCEEDED_DAILY_LIMIT
                && possibilityID != GlobalConstVar.EXCEEDED_MAX_DAY_COUNT) {
            while (((tmpSum - FiveHundredUAH.getNominal()) >= 0) && (FiveHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - FiveHundredUAH.getNominal();
                tmp500Count++;
            }
            while (((tmpSum - TwoHundredUAH.getNominal()) >= 0) && (TwoHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - TwoHundredUAH.getNominal();
                tmp200Count++;
            }
            while (((tmpSum - OneHundredUAH.getNominal()) >= 0) && (OneHundredUAH.getReminder() > 0)) {
                tmpSum = tmpSum - OneHundredUAH.getNominal();
                tmp100Count++;
            }
            while (((tmpSum - FiftyUAH.getNominal()) >= 0) && (FiftyUAH.getReminder() > 0)) {
                tmpSum = tmpSum - FiftyUAH.getNominal();
                tmp50Count++;
            }
            if (tmpSum == 0) {
                possibilityID = GlobalConstVar.ALLOWED;
                user.setCountGetMoneyDay(user.getCountGetMoneyDay() + 1);
                user.setMoneyBalance(user.getMoneyBalance() - userSum);

                FiveHundredUAH.setToEject(FiveHundredUAH.getToEject() + tmp500Count);
                FiveHundredUAH.setReminder(FiveHundredUAH.getReminder() - tmp500Count);
                TwoHundredUAH.setToEject(TwoHundredUAH.getToEject() + tmp200Count);
                TwoHundredUAH.setReminder(TwoHundredUAH.getReminder() - tmp200Count);
                OneHundredUAH.setToEject(OneHundredUAH.getToEject() + tmp100Count);
                OneHundredUAH.setReminder(OneHundredUAH.getReminder() - tmp100Count);
                FiftyUAH.setToEject(FiftyUAH.getToEject() + tmp50Count);
                FiftyUAH.setReminder(FiftyUAH.getReminder() - tmp50Count);
            } else possibilityID = GlobalConstVar.EXCEEDED_BANKNOTE;
        }
    }
}
