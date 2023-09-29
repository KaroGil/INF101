[Return to README.md](./README.md)
# Deck of cards

In a standard (french) deck of cards, each card has
- one of 4 possible *suits*: clubs, diamonds, hearts or spades; and
- one of 13 possible *ranks*: two, three, four, five, six, seven, eight, nine, ten, jack, queen, king or ace.

There are thus 52 unique cards.
We have provided a class [Card](./src/main/java/inf101v22/cards/Card.java) which models such a card.

A *pile of cards* is simply a collection of such cards.

In this task, you shall implement a pile of cards in two different ways. Let both classes you create be in the package *inf101v22.cards*.

1. Create a class `InheritedArrayCardPile` representing a pile of cards using **inheritance** from `ArrayList`.
2. Create a class `ComposedArrayCardPile` representing a pile of cards using **composition** with `ArrayList`.

For both classes:

- Implement a method `createFullDeck()` which returns a new pile of cards. The method should return a new pile of cards containing all the 52 different possible cards. Consider whether the method should be static or not.

- Implement a method with signature `boolean add(Card)` which adds a card to the pile. The method should throw an `IllegalArgumentException` if someone attempts to add `null`, but should otherwise add the card to the pile and always return `true` (strange, but that's the specification you get). Consider whether the method should be static or not.
