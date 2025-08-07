
import java.util.Date;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private Date issueDate;
    private String issuedTo;
    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.issueDate = null;
        this.issuedTo = null;
    }
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public Date getIssueDate() { return issueDate; }
    public String getIssuedTo() { return issuedTo; }
    
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public void setIssuedTo(String issuedTo) { this.issuedTo = issuedTo; }
    
    public String toString() {
        return "Book{ISBN='" + isbn + "', Title='" + title + "', Author='" + author + "', Available=" + (isAvailable ? "Yes" : "No") + "}";
    }
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }
}
