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
    }

    public int balance() {
        return balance;
    }
}
