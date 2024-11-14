import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Subjects> subjects = new ArrayList<>();
        Random rn = new Random();
        while (true) {
            System.out.print("\nWELCOME TO GRADE BOOK MENU" +
                    "\n1. Add Subjects"+
                    "\n2. View Grade Book"+
                    "\n3. Exit"+
                    "\n99. Test Grades"+
                    "\nSelect an Option: ");
            int option = sc.nextInt();
            switch (option){
                case 1 -> {
                    System.out.print("ADD SUBJECTS"+
                            "\nEnter Number Of Subjects: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < num; i++) {
                        System.out.print("Enter Subject "+ (i+1) +" Name: ");
                        String subject = sc.nextLine();
                        System.out.print("Enter Subject "+ (i+1) +" Marks: ");
                        int marks = sc.nextInt();
                        sc.nextLine();
                        double percentage = (marks/100.0)*100;
                        String grade = calculateGrade(percentage);
                        subjects.add(new Subjects(subject,marks,percentage,grade));
                    }
                }
                case 2 -> {
                    if (subjects.isEmpty()){
                        System.out.println("\nNo subjects found");
                        break;
                    }
                    System.out.println("VIEW GRADES"+
                            "\n----------------------------------"+
                            "\nSubject\tMarks\tPercentage\tGrade"+
                            "\n----------------------------------");
                    for (Subjects subject : subjects) {
                        System.out.println(subject.subject+"\t  "+subject.marks+"\t  "+subject.percentage+"\t\t  "+subject.grade);
                    }
                    System.out.println("----------------------------------");
                    int totalMarks = subjects.stream().mapToInt(subject -> subject.marks).sum();
                    double totalPercentage = (totalMarks/subjects.size())/100.0*100;
                    String totalGrade = calculateGrade(totalPercentage);
                    System.out.println("Total\t  "+totalMarks+"\t  "+totalPercentage+"\t\t  "+totalGrade);
                }
                case 3 -> {
                    System.out.println("Exiting..."+
                            "\nPress any key to close: ");
                    sc.next();
                    System.exit(0);
                }
                case 99 -> {
                    for (int i = 0; i < 5; i++) {
                        int marks = rn.nextInt(30,101);
                        double percentage = (marks/100.0)*100;
                        String grade = calculateGrade(percentage);
                        subjects.add(new Subjects("Sub"+(i+1),marks,percentage,grade));
                    }
                    System.out.println("\nAdded 5 Subjects to Test Grade Book");
                }
                default -> System.out.println("Invalid Input");
            }
        }

    }
    static String calculateGrade(double percentage){
        String grade = "";
        if (percentage >= 90) {
            grade = "A";
        }
        else if (percentage >= 80) {
            grade = "B";
        }
        else if (percentage >= 70) {
            grade = "C";
        }
        else if (percentage >= 60) {
            grade = "D";
        }
        else if (percentage >= 50) {
            grade = "E";
        }
        else {
            grade = "F";
        }
        return grade;
    }
}

class Subjects{
    String subject;
    int marks;
    double percentage;
    String grade;

    public Subjects(String subject,int marks,double percentage,String grade){
        this.subject = subject;
        this.marks = marks;
        this.percentage = percentage;
        this.grade = grade;
    }
}