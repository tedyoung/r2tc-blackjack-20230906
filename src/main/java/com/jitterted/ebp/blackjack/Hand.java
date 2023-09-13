package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private final List<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    private Hand(List<Card> cards) {
        this.cards.addAll(cards);
    }

    static Hand createHandForTest(List<Card> cards) {
        return new Hand(cards);
    }

    void drawCardFrom(Deck deck) {
        cards.add(deck.draw());
    }

    int value() {
        int handValue = cards
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        boolean hasAce = cards
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    void display() {
        System.out.println(cards.stream()
                                .map(Card::display)
                                .collect(Collectors.joining(
                                       ansi().cursorUp(6).cursorRight(1).toString())));
    }

    Card faceUpCard() {
        return cards.get(0);
    }

    boolean isBusted() {
        return value() > 21;
    }

    boolean pushes(Hand otherHand) {
        return value() == otherHand.value();
    }

    boolean beats(Hand otherHand) {
        return otherHand.value() < value();
    }

    boolean shouldDealerHit() {
        return value() <= 16;
    }
}