[Attende til README.md](./README.md)
# Kortbunke

I ein standard (fransk) kortstokk har kvart kort
 - ein av 4 moglege *"fargar"* (suit): kløver, ruter, hjarter eller spar; og
 - ein av 13 moglege *valørar* (rank): to, tre, fire, fem, seks, sju, åtte, ni, ti, knekt, dronning, konge eller ess.

Det finst dermed totalt 52 ulike kort.
Her finn du ein klasse [Card](./src/main/java/inf101v22/cards/Card.java) som modellerer eit slikt kort.

Ein *kortbunke* er enkelt og greitt ein samling av slike kort.

I denne oppgåva skal du implementera ein kortbunke på to forskjellig måtar. Lat båe klassane du opprettar vera i pakken *inf101v22.cards*.

1. Implementer ein kortbunke-klasse `InheritedArrayCardTile` ved bruk av **arv** frå `ArrayList`.
1. Implementer ein kortbunke-klasse `ComposedArrayCardTile` ved bruk av **komposisjon** av `ArrayList`.

For båe klassane skal du:

- Implementera ein metode `createFullDeck()` som returnerer ein ny kortbunke. Metoden skal returnera eit nytt kortbunke-objekt med alle dei 52 forskjellige korta. Burde denne metoden vera static eller non-static?
- Implementera ein metode med signatur `boolean add(Card)` som legg til eit kort i kortbunka. Metoden skal kasta `IllegalArgumentException` dersom nokon freistar å legga til `null`, men skal elles legga til kortet i bunka og alltid returnera `true` (rart, men slik er spesifikasjonane). Burde denne metoden vera static eller non-static?
