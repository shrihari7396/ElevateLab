
import java.util.*;

public class Library {
    private String libraryName;
    private List<Book> books;
    private List<User> users;
    
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public boolean addBook(Book book) {
        if (findBookByISBN(book.getIsbn()) == null) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
            return true;
        }
        System.out.println("Book with ISBN " + book.getIsbn() + " already exists!");
        return false;
    }
    
    public boolean removeBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (book.isAvailable()) {
                books.remove(book);
                System.out.println("Book removed: " + book.getTitle());
                return true;
            } else {
                System.out.println("Cannot remove book. It is currently issued to: " + book.getIssuedTo());
                return false;
            }
        }
        System.out.println("Book not found: " + isbn);
        return false;
    }
    
    public boolean addUser(User user) {
        if (findUserByID(user.getUserID()) == null) {
            users.add(user);
            System.out.println("User registered: " + user.getName());
            return true;
        }
        System.out.println("User with ID " + user.getUserID() + " already exists!");
        return false;
    }
    
    public boolean removeUser(String userID) {
        User user = findUserByID(userID);
        if (user != null) {
            if (user.getCurrentBooksCount() == 0) {
                users.remove(user);
                System.out.println("User removed: " + user.getName());
                return true;
            } else {
                System.out.println("Cannot remove user. They have " + user.getCurrentBooksCount() + " books issued.");
                return false;
            }
        }
        System.out.println("User not found: " + userID);
        return false;
    }
    
    public boolean issueBook(String userID, String isbn) {
        User user = findUserByID(userID);
        Book book = findBookByISBN(isbn);
        
        if (user == null) {
            System.out.println("User not found: " + userID);
            return false;
        }
        
        if (book == null) {
            System.out.println("Book not found: " + isbn);
            return false;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is not available. Currently issued to: " + book.getIssuedTo());
            return false;
        }
        
        if (!user.canIssueMoreBooks()) {
            System.out.println("User has reached maximum book limit (" + user.getMaxBooksAllowed() + ")");
            return false;
        }
        
        book.setAvailable(false);
        book.setIssuedTo(user.getName());
        book.setIssueDate(new Date());
        user.addIssuedBook(isbn);
        
        System.out.println("Book issued successfully!");
        System.out.println("Book: " + book.getTitle() + " issued to " + user.getName());
        return true;
    }
    
    public boolean returnBook(String userID, String isbn) {
        User user = findUserByID(userID);
        Book book = findBookByISBN(isbn);
        
        if (user == null) {
            System.out.println("User not found: " + userID);
            return false;
        }
        
        if (book == null) {
            System.out.println("Book not found: " + isbn);
            return false;
        }
        
        if (book.isAvailable()) {
            System.out.println("Book is not currently issued!");
            return false;
        }
        
        if (!user.hasBook(isbn)) {
            System.out.println("This book is not issued to user: " + user.getName());
            return false;
        }
        
        book.setAvailable(true);
        book.setIssuedTo(null);
        book.setIssueDate(null);
        user.removeIssuedBook(isbn);
        
        System.out.println("Book returned successfully!");
        System.out.println("Book: " + book.getTitle() + " returned by " + user.getName());
        return true;
    }
    
    public Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    public List<Book> findBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
    
    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
    
    public User findUserByID(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
    
    public void showAllBooks() {
        System.out.println("\nALL BOOKS:");
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    public void showAvailableBooks() {
        System.out.println("\nAVAILABLE BOOKS:");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available.");
        }
    }
    
    public void showAllUsers() {
        System.out.println("\nALL USERS:");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        
        for (User user : users) {
            System.out.println(user);
            if (user.getCurrentBooksCount() > 0) {
                System.out.println("  Issued Books: " + user.getIssuedBooks());
            }
        }
    }
    
    public void showStats() {
        System.out.println("\nLIBRARY STATS:");
        System.out.println("Library: " + libraryName);
        System.out.println("Total Books: " + books.size());
        
        int available = 0, issued = 0;
        for (Book book : books) {
            if (book.isAvailable()) available++;
            else issued++;
        }
        
        System.out.println("Available Books: " + available);
        System.out.println("Issued Books: " + issued);
        System.out.println("Total Users: " + users.size());
        
        int activeUsers = 0;
        for (User user : users) {
            if (user.getCurrentBooksCount() > 0) activeUsers++;
        }
        System.out.println("Active Users: " + activeUsers);
    }
}
