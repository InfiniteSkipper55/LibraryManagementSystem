package org.skipper.store;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int userID;
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<Book> borrowedBooks;
    private double fineAmount;

    // Constructor
    public User(int userID, String name, String email, String phone) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(int userID, String password, String name, String email, String phone) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Additional methods to borrow / return book
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }


    @Override
    public String toString() {
        return "User{ " +
                "userID = " + userID +
                ", Name = '" + name + '\'' +
                ", Email = '" + email + '\'' +
                ", Phone Number = " + phone + '\'' +
                ", Borrowed Books = '" + borrowedBooks + '\'' +
                ", Fine Amount = '" + fineAmount +
                '}';
    }


}
