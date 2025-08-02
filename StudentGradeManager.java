import java.util.Scanner;
class Student {
    String name;
    int[] marks = new int[3];
    double average;
    char grade;
    void calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        average = sum / 3.0;
    }
    void assignGrade() {
        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 60) grade = 'D';
        else grade = 'F';
    }
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks[0] + ", " + marks[1] + ", " + marks[2]);
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
        System.out.println("------------------------");
    }
}
public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Student[] students = new Student[num];

        for (int i = 0; i < num; i++) {
            students[i] = new Student();

            System.out.print("Enter name of student " + (i + 1) + ": ");
            students[i].name = scanner.nextLine();

            System.out.println("Enter 3 subject marks:");
            for (int j = 0; j < 3; j++) {
                System.out.print("Mark " + (j + 1) + ": ");
                students[i].marks[j] = scanner.nextInt();
            }
            scanner.nextLine(); // consume newline

            students[i].calculateAverage();
            students[i].assignGrade();
        }

        System.out.println("\n--- Student Report ---");
        for (Student s : students) {
            s.displayInfo();
        }

        scanner.close();
    }
}
