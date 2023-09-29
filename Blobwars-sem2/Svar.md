# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

   
## Oppgave 1
###  <b>Analyse av deisgnet</b>
### <u>Objektorienterte prinsipper som er brukt:</u>
- Objekter
- Abstraksjon (abstrakte klasser og grensesnitt)
- Innkapsling (private)
- Polymorfi (metoder fra foreldreklasse, override)
- Arv (extend)

### Nevn noen viktige klasser og hvorfor vi trenger disse klassene? 
Noen viktige klasser i den eksisterende koden er **Grid** klassen fordi denne repsreenterer brettet med et grid der brikkene har sine designerte plasser. Dette gjør det enkelt å holde kontrol på brettet. En annen viktig klasse innenfor programmet er klassen **Player** siden denne representerer spilleren som spiller spillet. Dette gjør det mulig for oss å holde kontrol på spillerne i spillet. Den aller viktigste klassen er nok **Game** klassen siden det er denne som representerer oppsettet til et spill, og har metodene som er nødvendige for å lage et spill. Den er som *Skjelett* av spillet. **Input** klassen er også viktig fordi det er denne som tar inn trekket spilleren har lyst å utføre og dermed er det ved denne klassen vi greier å utføre et trekk i spillet vi spiller. Det er andre klasser som også vil være viktige for å for eksempel vise frem spillet men i min mening er det disse som er de viktigste for å få et fungerende spill, selv om du kanskje ikke vil ha muligheten til å se spillet.

### Hvor brukes abstraction, encapsulation, inheritance, composition og polymorphism?
**Abstraction** prinsippet handler om å vise kun den nødendige informasjonen og gjemme den andre. Dette prinsippet brukes i GUI pakken i input klassen, som er en abstrakt klasse. Det blir også brukt i player pakken i AbstractPlayer klassen.
**Encapsulation** prinsippet handler også om å gjemme data, det brukes private variabler og get- og set- metoder for å oppnå dette. Dette brukes i grid klassen der det blir definert private feltvariabler og det finnes set og get metoder for å hente ut verdiene fra disse istedenfor at de er public. 
**Inheritance** prinsippet gjør det mulig å arve metoder og ting fra andre klasser. Dette gjøres gjennom "extends" kommandoen, og ved å gjøre dette vil den nye klassen arve egenskapene til den klassen som blir extenda. Dette konseptet brukes mange plasser i koden for eksempel i ConsoolePlayerInput, som extender Input klassen, GameEndedException som extender RuntimeException, GamePanel som extender JPanel.
**Composition** er prinsippet som referrer til en klasse som er avhengig av en annen klasse. Det er en klasse som blir brukt inn i en annen klasse. Dette brukes for eksempel i GridLocationIterator klassen ved å lage et nytt location objekt, her blir location en klasse som kan gjenbrukes der den trengs. Her blir Location nødvendig for at klassen skal fungere slik den er ment til, den er avhengig av Location klassen.
**Polymorphism** er et prinsipp som handler om å bruke grensesnitt og override for å skifte noen metoder men beholde andre. Dette prinsippet brukes i hvert av spillene der metodene fra Game klassen blir brukt. Noen av disse vil bli overridet av det spesifikke spillet men resten forblir det samme.
Klassen ConsolePlayerInput som extender Input, og getLocation() vil også være et eksempel på polymorphism.                 

### Hvilke andre spill (eller utvidelser) vil det være enkelt å legge til i dette prosjektet, og hvilke spill vil by på utfordringer?

- **Enkelt**: 
    Det vil være enkelt å lage spill som utnytter de samme konseptene som i tictac, connect4, blobwars og othello. Spill som fungerer som et grid vil være enkle å lage, men ikke alle. Spill som er grid spill men ikke utnytter de samme konseptene for bevegelse eller gameplay vil for eksempel ikke være like gunstige å lage i dette miljøet.
    <u>Eksempeler på spill</u>: 
    - battelships

- **Utfordring**: 
    
    Utfordrende spill å lage her kan for eksempel være tastetrykk spill. Dersom vi skulle ha laget et spill hvor vi gikk med spilleren ved hjelp av piltastene (for eksempel) måtte vi ha etablert et heilt system på hvordan dette skulle skjer og hvordan datamaskinen reagerte på trykkene. Altså laget en controller klasse og deretter etablert disse kontrollene inn i spillet. 

    Det vil i tillegg være vannskelig å lage spill som ikke spilles i følge av de samme prinsippene som de allerede lagede spillene. Altså spill som ikke er en spiller mot en annen på et lite brett men større spill eller rett og slett spill som ikke bruker grid. 

    <u>Eksempeler på spill</u>: 
    - RPG-spill
    - 3D spill


### Hvor er SOLID prinsippene brukt/ikke brukt.
- **SOLID** prinsippene er:
    - **S - "Single Responsibility Principle"** : Første bokstaven av SOLID er om at hver klasse skal bare ha et ansvar. Et eksempel på dette i koden er AbstractPlayer klassen, som overse alt om player.
    - **O - "Open-Closed Principle"** : Andre bokstav av SOLID er prinsippet om at koden skal være åpen for utvidelse men ikke endring. Dette brukes i Game klassen når vi lager de forskjellige spillene.
    - **L - "Liskov Substitution Principle"** : Den tredje nbokstaven av SOLID handler om at dersom vi arver en klasse skal den inneholde alle nødvendige egenskaper fra forelderklassen. Det skal dermed være mulig å "bytte ut" subklassen med forelderklassen uten at programmet krasjer. Dette blir brukt i GameBoard klassen der den bruker grid sine metoder. 
    - **I - "Interface Segregation Principle"** : Den fjerde bokstaven i SOLID er Interface Segregation Principle som er at grensesnitt ikke skal være for store men det er bedre å ha flere små og dermed mer spesifikke grensesnitt for å passe til koden. Et ekesmpel på dette vil være graphics grensesnittet.
    - **D - "Dependency Inversion Principle"** : Den siste bokstaven i SOLID er Dependency Inversion Principle og handler om at vi må bruke abstraksjon istedenfor å bruke konkrete ekesmpeler. Dette blir brukt i Game klassen som er en abstrakt klasse som deretter blir brukt i de forskjellige spillene. 

## Oppgave 2
### <u>Plan</u>: 
#### Klasser:
De klassene som er nødvendige å lage for dette prosjektet er:
- <u>BlobWars.java</u> :  en blobWars klasse hvor spillet blir laget som i de andre spillene sitt tilfelle, f.eks. Othello.java.
- <u>Move.java</u> : en move klasse for å representere hvordan brikken beveger seg i spillet, ta inn to locations. OldLocation, for den posisjonen brikken har, NewLocation, den nye posisjonen.

#### Hvordan kan denne informasjonen gis? (flytte informasjonen)
- I blob wars tillfelle kan dette bli gjennomført ved å lagre en location ved flytt og dersom flytt er 2 plasser vil brikken bli plasert på den nye plassen og fjernet fra den gamle. Dersom flytt er 1 plass vil en ny brikke bli plassert på den location og den gamle vil forbli. NewLocation blir til OldLocation ved mourseClickPressed, altså når spilleren klikker på en av brikkene. 

#### Hvordan er det gjort i Game med de eksisterende spillene?
- Dette blir gjennomført ved å hente ut trekket til spilleren og sjekke om det er et lovelig trekk, deretter gjennomføre trekket om mulig. Hvis trekket ikke er mulig vil det komme opp en tilbakemelding til spilleren om dette. Dette er slik jeg skal gjennomføre det i blobwars også, jeg må bare bytt ut trekk typen til et obejkt av den nye klassen Move. 
    
#### Hva trengs til å lage Blobwars?
- 2 spillere (players)
- 4 brikker plassert på brettet fra begynnelsen av, 2 for hver spiller.
- 2 locations (gammel og ny)
- brett (8x8)
- rules, winner

### Funksjonalitet:
- når en brikke blir trykket på:
    - flytte fra location blir lagt til
- når felt å flytte til blir trykket på:
    - Flytt 1 plass: brikke duplisert, brikken forblir på OldLocation og en duplikat blir plassert på NewLocation.
    - Flytt 2 plass: brikken blir fjernet fra OldLocation og lagt på NewLocation.

- Hva skjer med brikkene rundt newLocation?
    - brikkene rundt den brikken på NewLocation blir om til brikkens farge og blir en del av brikkene til spilleren som beveget brikkene. 
    - score: (spiller + like mange som ble forvandlet), (andre spiller - like mange brikker som ble forvandlet).
####  Mål?: / how to win
- få alle brikkene i din farge
eller
- få flest brikker av din farge på brettet når det ikke er flere mulige moves.

####   Vanskelighetsgrad
- ai levels
    1. DumbPlayer
    2. RandomPlayer
    3. AlphaBetaPlayer (flere levels i AlphaBetaPlayer) 


## Oppgave 3
**<u>Manuelle tester</u>**: 
- test at Blob Wars spillet kjører ved å trykke på Blob Wars knappen i GUI-en.
- test at du kan velge forskjellige vannskelighetsgrader for AI spilleren ved å velge Singlerplayer også skal en til prompt med ulike nivåer av ai droppe og du skal kunne velge en av disse.
- test at brikkene beveger seg riktig ved å trykke på en av dine brikker og prøve å bevege den først 1 brikke også neste tur 2 brikker.
- test at du kan overta brikkene (innenfor 1 brikke) fra motstander ved å sette din brikke ved siden av motstanderen sin brikke, da skal denne brikken også bli din.
- test ved å spille spillet i GUI at spillet møter alle forventingene til reglene.



### Feil i fått kode?
 - cancle funker ikke når du skal oppgi navn ved trykk på gui game valg.