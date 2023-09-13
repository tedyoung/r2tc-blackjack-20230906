package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    public static void main(String[] args) {
        displayWelcomeScreen();

        playGame();

        resetDisplay();
    }

    private static void resetDisplay() {
        System.out.println(ansi().reset());
    }

    private static void playGame() {
        Game game = new Game();
        game.initialDeal();
        game.play();
    }

    private static void displayWelcomeScreen() {
        AnsiConsole.systemInstall();
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" JitterTed's")
                                   .fgBlack().a(" BlackJack game"));
        System.out.println(ansi()
                                   .cursor(3, 1)
                                   .fgBrightBlack().a("Hit [ENTER] to start..."));

        System.console().readLine();
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() { // domain concept: "initial deal" -> exactly two cards to everyone
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // players get the first card due to the rules of Blackjack
        playerHand.drawCardFrom(deck);
        dealerHand.drawCardFrom(deck);
    }

    public void play() {
        boolean playerBusted = playerTurn();

        dealerTurn(playerBusted);

        displayFinalGameState();

        displayOutcome(playerBusted);
    }

    private void displayOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (handValueOf(dealerHand.getCards()) > 21) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (handValueOf(dealerHand.getCards()) < handValueOf(playerHand.getCards())) {
            System.out.println("You beat the Dealer! 💵");
        } else if (handValueOf(dealerHand.getCards()) == handValueOf(playerHand.getCards())) {
            System.out.println("Push: You tie with the Dealer. 💸");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private void dealerTurn(boolean playerBusted) {
        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>=stand)
        if (!playerBusted) {
            while (handValueOf(dealerHand.getCards()) <= 16) {
                dealerHand.drawCardFrom(deck);
            }
        }
    }

    private boolean playerTurn() {
        // get Player's decision: hit until they stand, then they're done (or they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHand.drawCardFrom(deck);
                if (handValueOf(playerHand.getCards()) > 21) {
                    playerBusted = true;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
        return playerBusted;
    }

    public int handValueOf(List<Card> hand) {
        int handValue = rawHandValue(hand);

        handValue = adjustForAce(hand, handValue);

        return handValue;
    }

    private int adjustForAce(List<Card> hand, int handValue) {
        boolean hasAce = hasAce(hand);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }
        return handValue;
    }

    private boolean hasAce(List<Card> hand) {
        return hand
                .stream()
                .anyMatch(card -> card.rankValue() == 1);
    }

    private int rawHandValue(List<Card> hand) {
        return hand
                .stream()
                .mapToInt(Card::rankValue)
                .sum();
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        eraseScreen();
        displayDealerHandGameInProgress();
        displayPlayerHand();
    }

    private void displayPlayerHand() {
        System.out.println();
        System.out.println("Player has: ");
        displayHand(playerHand.getCards());
        System.out.println(" (" + handValueOf(playerHand.getCards()) + ")");
    }

    private void displayDealerHandGameInProgress() {
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.getCards().get(0).display()); // first card is Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();
    }

    private void eraseScreen() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayHand(List<Card> hand) {
        System.out.println(hand.stream()
                               .map(Card::display)
                               .collect(Collectors.joining(
                                       ansi().cursorUp(6).cursorRight(1).toString())));
    }

    private void displayFinalGameState() {
        eraseScreen();
        displayDealerHandAtEndOfGame();
        displayPlayerHand();
    }

    private void displayDealerHandAtEndOfGame() {
        System.out.println("Dealer has: ");
        displayHand(dealerHand.getCards());
        System.out.println(" (" + handValueOf(dealerHand.getCards()) + ")");
    }
}
