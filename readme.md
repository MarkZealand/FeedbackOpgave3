# Gæt et tal
- Mark
- Sarah
- Niklas

## Hvad spillet går ud på
Spillet lader spilleren gætte på tal mellem en nedre og øvre grænse af tal. Computeren vælger et tilfældigt tal i hver runde.
Efter hvert gæt får spilleren at vide, om gættet var for lavt, for højt, meget tæt på eller korrekt.

Spilleren vælger selv en sværhedsgrad, der bestemmer grænsen for tal, samt antal mulige forsøg. 
Spillet fortsætter, indtil spilleren gætter rigtigt eller løber tør for forsøg. Derefter kan spilleren vælge at spille igen.

## Hvordan programmet køres

1. Programmet kører vi gennem IntelliJ i console, med Shift+F10 eller ved at trykke på "RUN" knappen.
2. Herefter vises der klare instruktioner i terminalen for at starte spillet.
3. Spilleren kan her indtaste deres valg og gæt direkte.
4. Spillet viser løbende hvad der sker undervejs.

## Hvordan programmet er opdelt i metoder

Programmet er opdelt i følgende metoder:

- `showIntroduction()` – viser en kort introduktion til spillet.
- `chooseDifficulty(Scanner input)` – viser sværhedsgrader og behandler spillerens valg.
- `getMaxAttempts(int difficultyChosen)` – returnerer maks antal forsøg baseret på sværhedsgrad.
- `getMaxRange(int difficultyChosen)` – returnerer tallets øvre grænse baseret på sværhedsgrad.
- `generateRandomNumber(int maxRange)` – genererer det tilfældige tal.
- `getPlayerGuess(Scanner input)` – behandler input for spillerens gæt.
- `processGuess(int guess, int target)` – vurderer gættet og returnerer en tekstbesked.
- `isGameOver(String result)` – afgør om spillet er slut.
- `showAttemptsLeft(int maxAttempts, int attempts)` – viser antal forsøg tilbage som tekst.
- `showScoreBoard(boolean won, int attempts, int target, int maxAttempts, int maxRange)` – viser resultatet og en procentscore.
- `askForRestart(Scanner input)` – spørger om spilleren vil spille igen.
- `showOutro()` – viser en afsluttende besked.

## Hvorfor denne opdeling

Vi har valgt denne opdeling for at give hver metode én enkelt opgave, så vidt muligt, 
eller i det mindste adskille forskellige typer logik og kode fra hinanden, der hvor vi mener at det passer bedst.

Nogle metoder, som eksempelvis `showOutro()` er meget korte og faktisk unødvendige i vores øjne, så nogle metoder 
er også oprettet for at øve os i selve disciplinen om at skabe metoder og vurdere hvornår de er nødvendige, baseret på feedback fra underviser.

## Eksempel på en metode med parameter

Som eksempel har vi valgt metoden `processGuess(int guess, int target)`. 
Her skal vi bruge to parametre, nemlig spillerens gæt (`guess`) og det rigtige tal (`target`).
Disse parametre bliver brugt til at behandle gættet og finde frem til om gættet er korrekt, tæt på svaret, eller helt forkert.

## Eksempel på en metode med returværdi

I metoden `getMaxAttempts(int difficultyChosen)`, returnerer vi en `int` der fortæller os antallet af tilladte forsøg, 
baseret på hvilken sværhedsgrad spilleren valgte i en given runde.

## Hvilken løkke bruges i spillet, og hvorfor

Vi besluttede os for at bruge to WHILE løkker til at håndtere selve spillet.

1. Den første WHILE løkke styres af en boolean `keepPlaying`, som kontrollerer om spilleren vil spille endnu en runde. 
Denne WHILE løkke blev valgt for at holde spillet kørende uden at håndtere specifikke runder.

2. Den anden WHILE løkke kører gentagne gange mens spilleren endnu ikke har gættet korrekt  `(correctGuess)`,
ligesom vi også sikrer os at spilleren enten har ubegrænset antal forsøg `(maxAttempts == 0)`,
eller at spilleren endnu ikke har brugt alle deres forsøg i runden `(attempts < maxAttempts)`. 
WHILE løkkenen stopper, når spilleren gætter rigtigt, eller når antallet af forsøg er brugt op. 
Vi valgte WHILE løkken, fordi vi ikke ved hvor mange forsøg det vil tage spilleren at gætte korrekt, eller om spilleren bruger alle forsøg uden at ramme rigtigt.


## Ting vi har testet
| Testscenarie | Forventet resultat | Faktisk resultat |
|---|---|---|
| Gæt der er for lavt | "FOR LAVT!" | "FOR LAVT!" vises korrekt |
| Gæt der er for højt | "FOR HØJT" | "FOR HØJT" vises korrekt |
| Korrekt gæt | "KORREKT!" og score vises | "KORREKT!" vises, og procentscore beregnes og udskrives |
| Sidste mulige forsøg | Spillet stopper, og tallet vises | Løkken stopper, og "Du løb tør for forsøg..." vises med tallet |
| Let sværhedsgrad | Ubegrænsede forsøg, "UBEGRÆNSET" vises | "FORSØG TILBAGE: UBEGRÆNSET" vises hver gang, og score-beregning springes over |
| Svær sværhedsgrad | Maks 7 forsøg, score beregnes | Løkken stopper efter 7 forsøg, og score vises ved korrekt gæt |
| Spil igen | Nyt spil starter | Ny runde starter med valg af sværhedsgrad igen |
| Afslut spillet | Outro vises | "Tak for denne gang. Håber at vi ses!" vises |
| Gæt 1-2 fra target | "DU ER MEGET TÆT PÅ!" | "DU ER MEGET TÆT PÅ!" vises korrekt |

## En fejl eller udfordring vi stødte på

1. Det var til tider svært at vurdere hvornår koden krævede en ny metode eller ej. Vi tænker at det ofte kan være ret subjektivt. Dog var der også ret klare dele af koden der var oplagt til en metode.
2. Det var nemt at vise antal forsøg spilleren havde tilbage med mellem og svært niveau. Men ved nemt niveau hvor `maxAttempts = 0` fik vi fejl i koden, fordi vi antog at værdien ikke var 0, så derfor skulle vi tage tjekke for dette, og håndtere det på en anden måde end de to andre sværhedsgrader.

## Hvad vi har lært om metoder og programstruktur

Hvis vi husker at anvende gode navne til metoderne, er det en hjælp når man skal læse koden igennem og se hvad de forskellige dele gør.
På nogle måder kan det gøre den samlede kode længere når man bruger metoder, fordi det fylder mere i antal linjer, men det kan omvendt også spare tid og besvær, især hvis der skal gentages kode, rettes ét sted og lignende.

## Changelog

1. Opdateret readme med WHILE løkke forklaring
2. Omdøbt isGamOver -> isCorrectGuess
3. Noteret (I skal ikke nødvendigvis lave en avanceret løsning på det nu, men I skal kunne se problemet og forklare, hvorfor det gør programmet mere skrøbeligt.)
4. Noteret (Inputvalidering var en mulig udvidelse og ikke et krav til grundversionen, men I skal kunne forklare programmets faktiske opførsel.)
5. Noteret (0 BETYDER IKKE RIGTIGT MAKSIMUM 0 - Det er ikke nødvendigvis forkert, men betydningen ligger skjult i koden.)
6. Udregning af score ændret til double i stedet for int. Selve udregningens formel også opdateret.
7. Noteret. Fjernet rudiment af kode ("UGYLDIGT GÆT") da vi ikke validerer input 
8. showScore har mere end 1 metode, korrekt. (ændres senere...)
9. Opdateret kommentar. 