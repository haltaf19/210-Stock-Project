

// A class representing the users account

package model.account;

import model.stocks.Stocks;

public class Account {

    private double balance;


    // EFFECTS: Constructs an Account
    public Account(Double startingBalance) {
        this.balance = startingBalance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // MODIFIES: This
    // EFFECTS: Buys a stock and adds it to your portfolio
    public void buyStock(Stocks stock, Integer shares) {
        balance -= shares * stock.getPrice();
    }

    //REQUIRES: Shares must be <= number of shares you own
    public void sellStock(Stocks stock, Integer shares) {
        balance += stock.getPrice() * shares;
    }

    public double getBalance() {
        return balance;
    }


}
