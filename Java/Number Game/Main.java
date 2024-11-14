import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<GameScore> scores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int round = 0;

        while (true) {
            System.out.print("" +
                    "\nWELCOME" +
                    "\n1. Start Game" +
                    "\n2. Check Scores" +
                    "\n3. Exit" +
                    "\n99. (Dev Btn) Automated Test" +
                    "\nSelect an Option: ");

            int welcmenu = -1;
            try {
                welcmenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer.");
                sc.nextLine();
                continue;
            }

            switch (welcmenu) {
                case 1 -> {
                    int rand = rn.nextInt(1, 100);
                    System.out.println("\nWe have picked a number, You have 8 attempts to guess it.");
                    for (int i = 0; i < 8; i++) {
                        System.out.print("\nEnter your guess: ");
                        int choice;
                        try {
                            choice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\nInvalid input. Please enter an integer.");
                            sc.nextLine();
                            i--;
                            continue;
                        }

                        if (choice == rand) {
                            System.out.println("\nYou guessed it right!");
                            round += 1;
                            scores.add(new GameScore(round, "W", 8 - i));
                            break;
                        } else if (choice > 100 || choice < 1) {
                            System.out.println("\nPlease pick a valid number between 1 and 100.");
                            i -= 1;
                        } else if (i == 7 && choice != rand) {
                            System.out.println("\nGame Over. You Lose");
                            round += 1;
                            scores.add(new GameScore(round, "L", 0));
                        } else {
                            String highLow = choice > rand ? "High" : "Low";
                            System.out.println("\nWrong Number. Your Number Was Too " + highLow + "." +
                                    "\n" + (7 - i) + " Attempts Remaining");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("\nRound\t Win\tScore");
                    for (GameScore score : scores) {
                        if (score.round <= 9) {
                            System.out.println("  " + score.round + "\t\t  " + score.winLoss + "\t\t  " + score.score);
                        } else if (score.round < 100) {
                            System.out.println(" " + score.round + "\t\t  " + score.winLoss + "\t\t  " + score.score);
                        } else {
                            System.out.println(" " + score.round + "\t  " + score.winLoss + "\t\t  " + score.score);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("\nExiting...");
                    System.exit(0);
                }
                case 99 -> {
                    System.out.println("\nAutomated Test Running 150 Games...");
                    for (int test = 0; test < 150; test++) {
                        System.out.println("\nStarting Game " + (test + 1));
                        int rand = rn.nextInt(1, 100);
                        round += 1;
                        boolean won = false;

                        for (int i = 0; i < 8; i++) {
                            int choice = rn.nextInt(1, 101);
                            System.out.println("Automated guess " + (i + 1) + ": " + choice);

                            if (choice == rand) {
                                System.out.println("Automated test guessed it right!");
                                scores.add(new GameScore(round, "W", 8 - i));
                                won = true;
                                break;
                            } else if (i == 7) {
                                System.out.println("Automated test did not guess the number. Game Over.");
                                scores.add(new GameScore(round, "L", 0));
                            }
                        }
                        if (!won) {
                            System.out.println("Automated test lost the game.");
                        }
                    }
                    System.out.println("\nAutomated Test Complete. Total Rounds Played: " + round);
                }
                default -> System.out.println("\nInvalid option. Please select 1, 2, 3, or 4.");
            }
        }
    }
}

class GameScore {
    int round;
    String winLoss;
    int score;

    public GameScore(int round, String winLoss, int score) {
        this.round = round;
        this.winLoss = winLoss;
        this.score = score;
    }
}
