[Tilbake til README.md](./README.md)
# Kortbunke

I en standard (fransk) kortstokk har hvert kort
- en av 4 mulige *"farger"* (suit): kløver, ruter, hjerter eller spar; og
- en av 13 mulige *valører* (rank): to, tre, fire, fem, seks, sju, åtte, ni, ti, knekt, dronning, konge eller ess.

Det finnes dermed totalt 52 ulike kort. 
Her finner du en klasse [Card](./src/main/java/inf101v22/cards/Card.java) som modellerer et slikt kort.

En *kortbunke* er enkelt og greit en samling av slike kort.

I denne oppgaven skal du implementere en kortbunke på to forskjellig måter. La begge klassene du oppretter være i pakken *inf101v22.cards*.

1. Implementer en kortbunke-klasse `InheritedArrayCardPile` ved bruk av **arv** fra `ArrayList`.
2. Implementer en kortbunke-klasse `ComposedArrayCardPile` ved bruk av **komposisjon** av `ArrayList`. 

For begge klassene skal du:

- Implementere en metode `createFullDeck()` som returnerer en ny kortbunke. Metoden skal returnere et nytt kortbunke-objekt med alle de 52 forskjellige kortene. Tenk igjennom hvorvidt denne metoden bør være static eller ikke.
- Implementere en metode med signatur `boolean add(Card)` som legger til et kort i kortbunken. Metoden skal kaste `IllegalArgumentException` dersom noen forsøker å legge til `null`, men skal ellers legge til kortet i bunken og alltid returnere `true` (rart, men slik er spesifikasjonene). Tenk igjennom hvorvidt denne metoden bør være static eller ikke.