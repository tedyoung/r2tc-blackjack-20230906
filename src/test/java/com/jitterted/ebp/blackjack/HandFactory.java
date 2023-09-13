package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;

public class HandFactory {
    private static final Suit DUMMY_SUIT = Suit.CLUBS;

    static Hand createHand(String... ranks) {
        List<Card> cards = new ArrayList<>();
        for (String rank : ranks) {
            cards.add(new Card(DUMMY_SUIT, rank));
        }
        return Hand.createHandForTest(cards);
    }
}
