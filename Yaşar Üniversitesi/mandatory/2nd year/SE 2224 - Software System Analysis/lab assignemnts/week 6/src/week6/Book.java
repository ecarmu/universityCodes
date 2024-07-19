package week6;

public class Book {
    private String title;
    private Author author;
    private String isbn;

    @Override
    public String toString() {
        return  title + '|' +
                author + '|' +
                 isbn + '|' +
                publisher + '|'
                +  publicationDate+
                '|' + availableCopies;
    }

    private String publicationDate;
    private Publisher publisher;
    private int availableCopies;

    public Book(String title, Author author, String isbn, String publicationDate, Publisher publisher, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("No copies available for borrowing");
        }
    }

    public void returnCopy() {
        availableCopies++;
    }
}
