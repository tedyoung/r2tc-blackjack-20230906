package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        Hand hand = HandFactory.createHand("A", "5");

        assertThat(hand.value())
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        Hand hand = HandFactory.createHand("A", "8", "3");

        assertThat(hand.value())
                .isEqualTo(1 + 8 + 3);
    }

    @Test
    void handWithOneAceAndOtherCardsEqualTo10ThenAceIsValuedAt11() {
        Hand hand = HandFactory.createHand("A", "Q");

        assertThat(hand.value())
                .isEqualTo(11 + 10); // EVIDENT DATA
    }

    @Test
    void handWithOneAceAndOtherCardsEqualTo12AceIsValuedAt1() {
        Hand hand = HandFactory.createHand("A", "8", "4");

        assertThat(hand.value())
                .isEqualTo(1 + 8 + 4);
    }
}
