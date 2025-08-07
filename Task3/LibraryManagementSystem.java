
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library;
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Library Management System");
        System.out.print("Enter library name: ");
        String name = input.nextLine();
        library = new Library(name);
        
        addSampleData();
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    removeUser();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    searchBooks();
                    break;
                case 8:
                    library.showAllBooks();
                    break;
                case 9:
                    library.showAvailableBooks();
                    break;
                case 10:
                    library.showAllUsers();
                    break;
                case 11:
                    library.showStats();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void addSampleData() {
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4"));
        library.addBook(new Book("1984", "George Orwell", "978-0-452-28423-4"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8"));
        
        library.addUser(new User("John Doe", "U001", "john@email.com", "123-456-7890"));
        library.addUser(new User("Jane Smith", "U002", "jane@email.com", "098-765-4321"));
        library.addUser(new User("Bob Johnson", "U003", "bob@email.com", "555-123-4567"));
        
        System.out.println("Sample data loaded!\n");
    }
    
    private static void showMenu() {
        System.out.println("\n==========================================");
        System.out.println("         LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1.  Add Book");
        System.out.println("2.  Remove Book");
        System.out.println("3.  Add User");
        System.out.println("4.  Remove User");
        System.out.println("5.  Issue Book");
        System.out.println("6.  Return Book");
        System.out.println("7.  Search Books");
        System.out.println("8.  Show All Books");
        System.out.println("9.  Show Available Books");
        System.out.println("10. Show All Users");
        System.out.println("11. Show Library Stats");
        System.out.println("0.  Exit");
        System.out.println("==========================================");
        System.out.print("Choose option: ");
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addBook() {
        System.out.println("\nADD BOOK:");
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.print("Author: ");
        String author = input.nextLine();
        System.out.print("ISBN: ");
        String isbn = input.nextLine();
        
        Book book = new Book(title, author, isbn);
        library.addBook(book);
    }
    
    private static void removeBook() {
        System.out.println("\nREMOVE BOOK:");
        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine();
        library.removeBook(isbn);
    }
    
    private static void addUser() {
        System.out.println("\nADD USER:");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("User ID: ");
        String userID = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Phone: ");
        String phone = input.nextLine();
        
        User user = new User(name, userID, email, phone);
        library.addUser(user);
    }
    
    private static void removeUser() {
        System.out.println("\nREMOVE USER:");
        System.out.print("Enter User ID: ");
        String userID = input.nextLine();
        library.removeUser(userID);
    }
    
    private static void issueBook() {
        System.out.println("\nISSUE BOOK:");
        System.out.print("User ID: ");
        String userID = input.nextLine();
        System.out.print("Book ISBN: ");
        String isbn = input.nextLine();
        library.issueBook(userID, isbn);
    }
    
    private static void returnBook() {
        System.out.println("\nRETURN BOOK:");
        System.out.print("User ID: ");
        String userID = input.nextLine();
        System.out.print("Book ISBN: ");
        String isbn = input.nextLine();
        library.returnBook(userID, isbn);
    }
    
    private static void searchBooks() {
        System.out.println("\nSEARCH BOOKS:");
        System.out.println("1. By Title");
        System.out.println("2. By Author");
        System.out.println("3. By ISBN");
        System.out.print("Choose: ");
        
        int option = getChoice();
        switch (option) {
            case 1:
                System.out.print("Enter title: ");
                String title = input.nextLine();
                var booksByTitle = library.findBooksByTitle(title);
                showSearchResults(booksByTitle, "title '" + title + "'");
                break;
            case 2:
                System.out.print("Enter author: ");
                String author = input.nextLine();
                var booksByAuthor = library.findBooksByAuthor(author);
                showSearchResults(booksByAuthor, "author '" + author + "'");
                break;
            case 3:
                System.out.print("Enter ISBN: ");
                String isbn = input.nextLine();
                Book book = library.findBookByISBN(isbn);
                if (book != null) {
                    System.out.println("Found: " + book);
                } else {
                    System.out.println("No book found with ISBN: " + isbn);
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
    
    private static void showSearchResults(java.util.List<Book> books, String criteria) {
        if (books.isEmpty()) {
            System.out.println("No books found with " + criteria);
        } else {
            System.out.println("Found " + books.size() + " book(s) with " + criteria + ":");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
