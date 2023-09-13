package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    @Deprecated // this is scaffolding
    public List<Card> getCards() {
        return cards;
    }

    void drawCardFrom(Deck deck) {
        getCards().add(deck.draw());
    }

    int value() {
        List<Card> hand1 = getCards();
        int handValue = hand1
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        boolean hasAce = hand1
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }
}