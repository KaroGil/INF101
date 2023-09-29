package inf101v22.cards;

/**
 * Single card in a conventional (French) card deck.
 * A card has a rank and suite.
 * For instance, rank: Queen, suite: Diamonds.
 */
public class Card implements Comparable<Card> {

    public final CardRank rank;
    public final CardSuit suit;

    /**
     * Creates a new Card with of the provided suit and rank.
     * 
     * @param rank
     * @param suit
     */
    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card other) {
        int rankComparison = this.rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        }
        return this.suit.compareTo(other.suit);
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
