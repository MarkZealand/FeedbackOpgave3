import java.util.Random;
import java.util.Scanner;

    static void main() {

        //FASTE VARIABLER OG REFERENCER
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        int attempts = 0;

        //VISER INTRODUKTION FØRSTE GANG SPILLET KØRER
        showIntroduction();

        //INDLÆSER SVÆRHEDSGRAD BASERET PÅ BRUGERENS INPUT
        int difficulty = chooseDifficulty(input);
        //BEREGNER ANTALLET AF MULIGE FORSØG
        int maxAttempts = getMaxAttempts(difficulty);
        //BEREGNER ANTALLET AF TILFÆLDIGE TAL DER KAN GÆTTES PÅ
        int maxRange = getMaxRange(difficulty);
        //VÆLGER ET TILFÆLDIGT TAL
        int target = generateRandomNumber(maxRange);

        //DENNE WHILE LØKKE KØRER DEREFTER INDTIL SPILLEREN LØBER TØR FOR FORSØG, ELLER GÆTTER RIGTIGT
        while (!gameOver && (maxAttempts == 0 || attempts < maxAttempts)) {
            int guess = getPlayerGuess(input);
            attempts++;

            String result = processGuess(guess, target);
            System.out.println(result);
            gameOver = isGameOver(result);
        }
    }

    static void showIntroduction() {
        // BYDER SPILLEREN VELKOMMEN OG INTRODUCERER REGLERNE
        System.out.println("========= Velkommen til GÆT ET TAL =========");
        System.out.println("============================================");
        System.out.println("Jeg tænker på et tal, og du skal gætte det.");
        System.out.println("Efter hvert gæt får du at vide, om gættet er");
        System.out.println("for lavt, for højt eller korrekt.");
        System.out.println("============================================");
    }
    static int chooseDifficulty(Scanner input){
        //VISER SVÆRHEDSGRADER OG LADER SPILLEREN VÆLGE MED SWITCH METODE
        int difficulty = -1;

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
        int guess = input.nextInt();
        return guess;
    }
    static String processGuess(int guess, int target){
        String result = "UGYLDIGT GÆT";
        //EVALUERER SPILLERENS SENESTE GÆTTEDE TAL OG FORTÆLLER RESULTATET (LAVT, HØJT eller KORREKT)
        if(guess == target){
            result = "KORREKT!";
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
    static boolean isGameOver(String result){
        switch (result){
            case "KORREKT!":
                return true;
            default:
                return false;
        }
    }

    static void showRestartMessage(){
        //VISER SCOREBOARD, SAMT BESKED DER SPØRGER OM SPILLEREN VIL PRØVE IGEN
    }
