import java.io.*;
import java.util.*;

class Book {
    String title;
    String author;
    boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return title + "," + author + "," + isIssued;
    }

    public static Book fromString(String data) {
        String[] parts = data.split(",");
        Book b = new Book(parts[0], parts[1]);
        b.isIssued = Boolean.parseBoolean(parts[2]);
        return b;
    }
}

public class LibraryManagementSystem {
    static final String FILE_NAME = "books.txt";
    static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        loadBooksFromFile();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook(sc);
                case 2 -> viewBooks();
                case 3 -> searchBook(sc);
                case 4 -> issueBook(sc);
                case 5 -> returnBook(sc);
                case 6 -> saveBooksToFile();
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }

    static void addBook(Scanner sc) {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) {
            System.out.println("Title: " + b.title + ", Author: " + b.author + ", Issued: " + b.isIssued);
        }
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter title to search: ");
        String title = sc.nextLine();
        boolean found = false;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + b.title + " by " + b.author + " | Issued: " + b.isIssued);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Book not found.");
    }

    static void issueBook(Scanner sc) {
        System.out.print("Enter title to issue: ");
        String title = sc.nextLine();
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    System.out.println("Book issued.");
                    return;
                } else {
                    System.out.println("Already issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    static void returnBook(Scanner sc) {
        System.out.print("Enter title to return: ");
        String title = sc.nextLine();
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                if (b.isIssued) {
                    b.isIssued = false;
                    System.out.println("Book returned.");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    static void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.write(b.toString());
                writer.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    static void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }
}
