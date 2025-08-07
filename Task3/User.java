
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String userID;
    private String email;
    private String phoneNumber;
    private List<String> issuedBooks;
    private int maxBooksAllowed;
    
    public User(String name, String userID, String email, String phoneNumber) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.issuedBooks = new ArrayList<>();
        this.maxBooksAllowed = 3;
    }
    
    public String getName() { return name; }
    public String getUserID() { return userID; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<String> getIssuedBooks() { return new ArrayList<>(issuedBooks); }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public int getCurrentBooksCount() { return issuedBooks.size(); }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setMaxBooksAllowed(int maxBooksAllowed) { this.maxBooksAllowed = maxBooksAllowed; }
    
    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < maxBooksAllowed;
    }
    
    public boolean addIssuedBook(String isbn) {
        if (canIssueMoreBooks() && !issuedBooks.contains(isbn)) {
            issuedBooks.add(isbn);
            return true;
        }
        return false;
    }
    
    public boolean removeIssuedBook(String isbn) {
        return issuedBooks.remove(isbn);
    }
    
    public boolean hasBook(String isbn) {
        return issuedBooks.contains(isbn);
    }
    
    public String toString() {
        return "User{ID='" + userID + "', Name='" + name + "', Email='" + email + "', Books Issued=" + issuedBooks.size() + "/" + maxBooksAllowed + "}";
    }
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userID.equals(user.userID);
    }
}
