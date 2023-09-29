package inf101v22.cards;

import java.util.Arrays;
import java.util.List;

/**
 * Ranks of conventional (French) card deck,
 * such as King, Queen, two, ace, etc.
 */
public enum CardRank {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    /**
     * List of all available ranks.
     */
    public static final List<CardRank> ALL_RANKS = Arrays.asList(CardRank.values());

    /**
     * Integer representing the card's value.
     */
    public final int rankValue;

    private CardRank(int rankValue) {
        this.rankValue = rankValue;
    }
}
