package ua.com.wadyan.vinatm.Model;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class User {
    private String name;
    private String password;
    private int moneyBalance;
    private int countGetMoneyDay;

    public User(String userName, String password, int moneyBalance) {
        this.name = userName;
        this.password = password;
        this.moneyBalance = moneyBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public int getCountGetMoneyDay() {
        return countGetMoneyDay;
    }

    public void setCountGetMoneyDay(int countGetMoneyDay) {
        this.countGetMoneyDay = countGetMoneyDay;
    }
}
