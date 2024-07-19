package week6;

import java.util.ArrayList;

public class Librarian {
    private String name;
    private String email;

    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Author> authors = new ArrayList<>();
    public static ArrayList<Publisher> publishers = new ArrayList<>();
    public static ArrayList<Borrower> borrowers = new ArrayList<>();

    public Librarian(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static void findBorrower(String text, Book book) {
        for(Borrower borrower: borrowers){
            if(borrower.getName() == text){
                 borrower.borrowedBooks.add(book);
            }
        }
    }

    public void addBookToLibrary(Book book){
        books.add(book);
    }

    public void removeBookFromLibrary(Book book){
        books.remove(book);
    }

    public void addAuthorToLibrary(Author author){
        authors.add(author);
    }

    public void removeAuthorFromLibrary(Author author){
        authors.remove(author);
    }

    public void addPublisherToLibrary(Publisher publisher){
        publishers.add(publisher);
    }

    public void removePublisherFromLibrary(Publisher publisher){
        publishers.remove(publisher);
    }

    public void addBorrowerToLibrary(Borrower borrower){
        borrowers.add(borrower);
    }

    public void removeBorrowerFromLibrary(Borrower borrower){
        borrowers.remove(borrower);
    }
}
