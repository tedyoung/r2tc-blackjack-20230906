package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    private static final Suit DUMMY_SUIT = Suit.CLUBS;

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "5"));
        Hand hand = new Hand(cards);

        assertThat(hand.value())
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        Game game = new Game();
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "8"),
                                   new Card(DUMMY_SUIT, "3"));

        assertThat(game.handValueOf(cards))
                .isEqualTo(1 + 8 + 3);
    }

}
