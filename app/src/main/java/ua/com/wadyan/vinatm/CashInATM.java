package ua.com.wadyan.vinatm;

import ua.com.wadyan.vinatm.Model.FiftyUAH;
import ua.com.wadyan.vinatm.Model.FiveHundredUAH;
import ua.com.wadyan.vinatm.Model.OneHundredUAH;
import ua.com.wadyan.vinatm.Model.TwoHundredUAH;
import ua.com.wadyan.vinatm.Model.User;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class CashInATM {
    private int sumCash;
    private int possibilityID = 0;
    User user = GlobalConstVar.getCurrentUser();

    private FiftyUAH fiftyUAH = new FiftyUAH();
    private OneHundredUAH oneHundredUAH = new OneHundredUAH();
    private TwoHundredUAH twoHundredUAH = new TwoHundredUAH();
    private FiveHundredUAH fiveHundredUAH = new FiveHundredUAH();

    public CashInATM() {
        fiftyUAH.setReminder(2);
        oneHundredUAH.setReminder(2);
        twoHundredUAH.setReminder(2);
        fiveHundredUAH.setReminder(2);
    }

    public int possibilityGetMoney(int userSum){
        maxDayLimitValidation(userSum);
        maxATMCashReminderValidation(userSum);
        maxUserMoneyReminder(userSum);
        maxBanknote(userSum);
        maxCountValidation(userSum);
        return possibilityID;
    }

    void maxDayLimitValidation(int userSum){
        if(userSum > GlobalConstVar.MAX_DAY_LIMIT) possibilityID = GlobalConstVar.EXCEEDED_DAILY_LIMIT;
    }

    void maxATMCashReminderValidation(int userSum){
        sumCash = fiftyUAH.getReminder()*fiftyUAH.getNominal()
                + oneHundredUAH.getReminder()*oneHundredUAH.getNominal()
                + twoHundredUAH.getReminder()*twoHundredUAH.getNominal()
                + fiveHundredUAH.getReminder()*fiveHundredUAH.getNominal();
        if(userSum > sumCash) possibilityID = GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER;
    }

    void maxUserMoneyReminder(int userSum){
        if(userSum > user.getMoneyBalance()) possibilityID = GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER;
    }

    void maxBanknote(int userSum){
        while (((userSum - fiveHundredUAH.getNominal()) > 0) && (fiveHundredUAH.getReminder() > 0)) {
            userSum = userSum - fiveHundredUAH.getNominal();
            fiveHundredUAH.setToEject(fiveHundredUAH.getToEject() + 1);
        }
        while (((userSum - twoHundredUAH.getNominal()) > 0) && (twoHundredUAH.getReminder() > 0)) {
            userSum = userSum - twoHundredUAH.getNominal();
            twoHundredUAH.setToEject(twoHundredUAH.getToEject() + 1);
        }
        while (((userSum - oneHundredUAH.getNominal()) > 0) && (oneHundredUAH.getReminder() > 0)) {
            userSum = userSum - oneHundredUAH.getNominal();
            oneHundredUAH.setToEject(oneHundredUAH.getToEject() + 1);
        }
        while (((userSum - fiftyUAH.getNominal()) > 0) && (fiftyUAH.getReminder() > 0)) {
            userSum = userSum - fiftyUAH.getNominal();
            fiftyUAH.setToEject(fiftyUAH.getToEject() + 1);
        }
        if (userSum == 0) {
            possibilityID = GlobalConstVar.ALLOWED;
            user.setCountGetMoneyDay(user.getCountGetMoneyDay() + 1);
        }
        else possibilityID = GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER;
    }

    void maxCountValidation(int userSum){
        if(user.getCountGetMoneyDay() > 0) possibilityID = GlobalConstVar.EXCEEDED_MAX_COUNT;
    }
}
