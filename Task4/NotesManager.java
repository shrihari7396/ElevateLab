import java.io.*;
import java.util.Scanner;

public class NotesManager {

    static String fileName = "notes.txt";

    static void addNote(String note) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(note + "\n");
            fw.close();
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("Error while saving note.");
        }
    }

    static void viewNotes() {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            System.out.println("\n--- Your Notes ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No notes found or unable to read.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Write your note: ");
                String note = sc.nextLine();
                addNote(note);
            } else if (choice == 2) {
                viewNotes();
            } else if (choice == 3) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
