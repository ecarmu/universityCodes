package week6;
import java.util.ArrayList;


public class Borrower {
    private String name;
    private String email;
    public ArrayList<Book> borrowedBooks;

    @Override
    public String toString() {
        return name + '|' +
               email + '|' +
               borrowedBooks;
    }

    public Borrower(String name, String email) {
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(ArrayList<Book> borrewdBookList) {
        for(Book book: borrewdBookList){
            borrowedBooks.add(book);
        }
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnCopy();
    }
}

