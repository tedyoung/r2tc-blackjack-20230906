package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletTest {

    @Test
    void newWalletIsEmpty() {
        Wallet wallet = new Wallet();

        assertThat(wallet.isEmpty())
                .isTrue();
    }

    @Test
    void newWalletAddMoneyIsNotEmpty() {
        Wallet wallet = new Wallet();

        wallet.addMoney(1);

        assertThat(wallet.isEmpty())
                .isFalse();
    }

    @Test
    void newWalletHasZeroBalance() {
        Wallet wallet = new Wallet();

        assertThat(wallet.balance())
                .isZero();
    }

    @Test
    void addMoneyHasBalanceOfAmountAdded() {
        Wallet wallet = new Wallet();

        wallet.addMoney(10);

        assertThat(wallet.balance())
                .isEqualTo(10);
    }

    @Test
    void newWalletAdd7And8ThenBalanceIs15() {
        Wallet wallet = new Wallet();

        wallet.addMoney(7);
        wallet.addMoney(8);

        assertThat(wallet.balance())
                .isEqualTo(7 + 8);

    }
}
