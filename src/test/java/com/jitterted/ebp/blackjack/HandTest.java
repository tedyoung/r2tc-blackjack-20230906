package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HandTest {

    @Test
    void handWithValueOf22IsBusted() {
        Hand hand = HandFactory.createHand("9", "8", "5");

        assertThat(hand.isBusted())
                .isTrue();
    }

    @Test
    void handWithValueOf21IsNotBusted() {
        Hand hand = HandFactory.createHand("8", "8", "5");

        assertThat(hand.isBusted())
                .isFalse();
    }
}