package inf101v22.cards;

import java.util.Arrays;
import java.util.List;

/**
 * Suit of conventional (French) card deck:
 * Clubs, Diamonds, Hearts and Spades.
 */
public enum CardSuit {

    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    /**
     * List of all available suits.
     */
    public static final List<CardSuit> ALL_SUITS = Arrays.asList(CardSuit.values());
}
