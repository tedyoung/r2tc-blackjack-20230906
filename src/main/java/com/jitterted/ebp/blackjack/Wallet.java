package com.jitterted.ebp.blackjack;

public class Wallet {

    private boolean isEmpty;
    private int balance;

    public Wallet() {
        isEmpty = true;
        balance = 0;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void addMoney(int amount) {
        isEmpty = false;
        balance += amount;
    }

    public int balance() {
        return balance;
    }
}
