import java.util.Random;
import java.util.Scanner;

    void main() {

        //FASTE VARIABLER OG REFERENCER
        Scanner input = new Scanner(System.in);
        boolean keepPlaying = true;		

        //VISER INTRODUKTION FØRSTE GANG SPILLET KØRER
        showIntroduction();

		while (keepPlaying) {
			//INDLÆSER STANDARD VÆRDIER SÅ LÆNGE KEEPPLAYING ER KORREKT _OG_ MAN IKKE ER I DET NÆSTE WHILE LOOP
			int difficulty = chooseDifficulty(input);
			int maxAttempts = getMaxAttempts(difficulty);
			int maxRange = getMaxRange(difficulty);
			int target = generateRandomNumber(maxRange);
			int attempts = 0;
			boolean correctGuess = false;

			//DENNE WHILE LØKKE KØRER INDTIL EN MATCH ER FORBI.
			//ALTSÅ FLERE RUNDER I TRÆK FØR KODEN GÅR VIDERE NEDENUNDER (ELLER STARTER FORFRA OVENFOR)
			while (!correctGuess && (maxAttempts == 0 || attempts < maxAttempts)) {
				int guess = getPlayerGuess(input);
				attempts++;
				String result = processGuess(guess, target);
				System.out.println(result);
				correctGuess = isCorrectGuess(result);

                //UDVIDELSE #1 - VIS SPILLEREN HVOR MANGE FORSØG TILBAGE
                System.out.println("FORSØG TILBAGE: " + showAttemptsLeft(maxAttempts, attempts));
			}

			//HVIS KORREKT GÆT, ELLER ANTAL AF FORSØG OVERSKRIDER MAX TILLADTE
			//SÅ HVIS SCOREBRÆTTET OG SPØRG DEREFTER OM SPILLEREN VIL PRØVE IGEN
			showScoreBoard(correctGuess, attempts, target, maxAttempts, maxRange);
			keepPlaying = askForRestart(input);
		}

        //DENNE METODE KALDES FØRST NÅR SPILLEREN ER HELT FÆRDIG
        //ALTSÅ NÅR SPILLER HAR AFSLUTTET ET SPIL OG IKKE ØNSKER AT RESTARTE
        showOutro();
    }

    static void showIntroduction() {
        //BYDER SPILLEREN VELKOMMEN OG INTRODUCERER REGLERNE
        System.out.println("========= Velkommen til GÆT ET TAL =========");
        System.out.println("============================================");
        System.out.println("Jeg tænker på et tal, og du skal gætte det.");
        System.out.println("Efter hvert gæt får du at vide, om gættet er");
        System.out.println("for lavt, for højt eller korrekt.");
        System.out.println("============================================");
    }
    static int chooseDifficulty(Scanner input){
        //VISER SVÆRHEDSGRADER OG LADER SPILLEREN VÆLGE MED SWITCH METODE
        int difficulty;

        System.out.println("Du kan vælge mellem tre sværhedsgrader:");
        System.out.println("  1. Let    - tal fra 1-10, ubegrænsede forsøg");
        System.out.println("  2. Mellem - tal fra 1-50, maks. 10 forsøg");
        System.out.println("  3. Svær   - tal fra 1-100, maks. 7 forsøg");
        System.out.print("Vælg sværhedsgrad: ");
        difficulty = input.nextInt();
        return difficulty;
    }
    static int getMaxAttempts(int difficultyChosen) {
        switch (difficultyChosen) {
            case 2:
                return 10;
            case 3:
                return 7;
            case 1:
            default:
                return 0;    // 0 = ubegrænset
        }
    }
    static int getMaxRange(int difficultyChosen) {
        switch (difficultyChosen) {
            case 2:
                return 50;
            case 3:
                return 100;
            case 1:
            default:
                return 10;
        }
    }
    static int generateRandomNumber(int maxRange){
        Random random = new Random();
        return random.nextInt(maxRange) + 1;
    }
    static int getPlayerGuess(Scanner input){
        //LADER SPILLER INDTASTE ET TAL FOR AT GÆTTE
        System.out.println("Hvilket tal tror du, at jeg har valgt? :");
        return input.nextInt();
    }
    static String processGuess(int guess, int target){
        String result = "";
        //EVALUERER SPILLERENS SENESTE GÆTTEDE TAL OG FORTÆLLER RESULTATET (LAVT, HØJT eller KORREKT)
        if(guess == target){
            result = "KORREKT!";
        }
        //UDVIDELSE 3: TILFØJER LILLE HJÆLPEBESKED HVIS SPILLER ER 2 FRA AT RAMME RIGTIGT.
        //VI BRUGER "Math.abs" FUNKTIONEN TIL NEMT AT OMDANNE NEGATIVE TAL TIL POSITIVE TAL.
        else if(Math.abs(guess - target) <= 2){
            result = "DU ER MEGET TÆT PÅ!";
        }
        else if(guess > target){
            result = "FOR HØJT";
        }
        //KUNNE OGSÅ BARE BRUG ELSE uden en if guess < target
        //men det bør jo give det samme uanset, og så er dette måske mere tydeligt
        else if(guess < target) {
            result = "FOR LAVT!";
        }
        return result;
    }
    static boolean isCorrectGuess(String result){
        switch (result){
            case "KORREKT!":
                return true;
            default:
                return false;
        }
    }	
	static String showAttemptsLeft(int maxAttempts, int attempts){
        //UNDGÅ AT BEREGNE ANTAL FORSØG TILBAGE HVIS MAN ER PÅ LET SVÆRHEDSGRAD
        // OG ALTSÅ I PRINCIPPET HAR 0 MAKS FORSØG
        if(maxAttempts == 0){
            return "UBEGRÆNSET";
        }
        else {
            int attemptsLeft = maxAttempts - attempts;
            //IKKE DEN PÆNESTE MÅDE AT LAVE int OM TIL EN String - MEN DET VIRKER :)
            return "" + attemptsLeft;
        }
    }
	static void showScoreBoard(boolean won, int attempts, int target, int maxAttempts, int maxRange) {
		//VISER SCOREBOARD
		System.out.println("============================================");
		if (won) {
			System.out.println("Du gættede tallet " + target + " på " + attempts + " forsøg!");

            //UDVIDELSE #2 - VIS BEREGNET SCORE I PROCENT UD FRA ANTAL FORSØG KONTRA MAKS MULIGE FORSØG
            if (maxAttempts > 0) {
                double percentageScore = (maxAttempts - attempts) * 100.0 / maxAttempts;
                System.out.println("Din samlede score er: " + percentageScore + "%");
            } else {
                System.out.println("Din samlede score er: ubegrænset antal forsøg – ingen procentberegning.");
            }

		} else {
			System.out.println("Du løb tør for forsøg. Tallet var: " + target + " og du fik " + maxAttempts 
			+ " forsøg til at gætte  tallet der kunne være mellem 1 og " + maxRange);
		}
		System.out.println("============================================");
	}

	static boolean askForRestart(Scanner input) {
		System.out.print("Vil du spille igen? (ja/nej): ");
		String answer = input.next();
		return answer.equalsIgnoreCase("ja");
	}
    static void showOutro(){
        System.out.println("Tak for denne gang. Håber at vi ses!");
    }
