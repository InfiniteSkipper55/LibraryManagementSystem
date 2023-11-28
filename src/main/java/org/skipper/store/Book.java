package org.skipper.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book implements java.io.Serializable {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String ISBN;
    private String publisher;
    private LocalDate dueDate;
    private User borrower;
    private boolean availability;
    private List<User> borrowers;

    // Constructor
    public Book(int bookID, String title, String author, String genre, int publicationYear, String ISBN, String publisher, LocalDate dueDate, User borrower, boolean availability) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.dueDate = null;
        this.borrower = null;
        this.borrowers = new ArrayList<>();
        this.availability = true;
    }
    public Book(int bookID, String title, String author, String genre, int publicationYear, String ISBN, String publisher) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.publisher = publisher;
    }

    // Getters and Setters
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isAvailable() {
        return availability;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }


    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<User> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<User> borrowers) {
        this.borrowers = borrowers;
    }

    // Methods for managing borrowers
    public void addBorrower(User user) {
        borrowers.add(user);
    }

    public void removeBorrower(User user) {
        borrowers.remove(user);
    }

    @Override
    public String toString() {
        return "Book{ " +
                "BookID = " + bookID +
                ", Title = '" + title + '\'' +
                ", Author = '" + author + '\'' +
                ", Genre = '" + genre + '\'' +
                ", Publication Year = " + publicationYear +
                ", ISBN = '" + ISBN + '\'' +
                ", Publisher = '" + publisher + '\'' +
                ", Due Date = " + dueDate +
                ", Borrower = " + borrower +
                ", Availability = " + availability +
                '}';
    }
}
