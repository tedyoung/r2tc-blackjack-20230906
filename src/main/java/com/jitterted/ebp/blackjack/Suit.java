package com.jitterted.ebp.blackjack;

import java.util.List;

public enum Suit {
    HEARTS("♥"),
    CLUBS("♣"),
    DIAMONDS("♦"),
    SPADES("♠")
    ;

    @Deprecated
    static final List<String> SUITS = List.of("♠", "♦", "♥", "♣");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    // Scaffolding
    @Deprecated
    public static Suit from(String symbol) {
        for (Suit suit : values()) {
            if (symbol.equals(suit.symbol())) {
                return suit;
            }
        }
        return null;
    }

    public String symbol() {
        return symbol;
    }
}
