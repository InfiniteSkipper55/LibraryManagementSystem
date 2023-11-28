package org.skipper.store;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library implements Serializable {
    private List<Book> books;
    private List<User> users;
    private List<Book> reservedBooks;
    private transient Scanner scanner;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addBook() {
        System.out.println("=== Add a Book ===");

        System.out.print("Enter Book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Publication Year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();

        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();

        Book book = new Book(bookID, title, author, genre, publicationYear, ISBN, publisher, null, null, true);
        books.add(book);
        System.out.println("Book added successfully.");

    }

    public void updateBook(Book book) {
        int index = books.indexOf(book);
        if (index != -1) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter updated Publication Year: ");
            int updatedPublicationYear = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter updated ISBN: ");
            String updatedISBN = scanner.nextLine();

            System.out.print("Enter updated Publisher: ");
            String updatedPublisher = scanner.nextLine();


            Book updatedBook = new Book(book.getBookID(), book.getTitle(), book.getAuthor(), book.getGenre(), updatedPublicationYear, updatedISBN, updatedPublisher, book.getDueDate(), book.getBorrower(), book.isAvailable());
            books.set(index, updatedBook);
            System.out.println("Book information updated successfully.");
        } else {
            System.out.println("Book not found. Update failed.");
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
        reservedBooks.remove(book);
    }

    public Book findBookById(int bookID) {
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || book.getISBN().toLowerCase().contains(keyword.toLowerCase())
                    || book.getGenre().toLowerCase().contains(keyword.toLowerCase())
                    || book.getPublisher().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    public List<Book> advancedSearch(String title, String author, String genre) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if ((title == null || book.getTitle().equalsIgnoreCase(title))
                    && (title == null || book.getAuthor().equalsIgnoreCase(title))
                    && (title == null || book.getGenre().equalsIgnoreCase(title))) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    public void addUser() {
        System.out.println("=== Add a User ===");

        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // Create a new User object
        User user = new User(userID, name, email, phoneNumber);

        // Add the user to the users list
        users.add(user);

        System.out.println("User added successfully.");
    }


    public void updateUser(User user) {
        int index = users.indexOf(user);
        if (index != -1) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter updated email: ");
            String updatedEmail = scanner.nextLine();

            System.out.print("Enter updated phone number: ");
            String updatedPhone = scanner.nextLine();

            User updatedUser = new User(user.getUserID(), user.getName(), updatedEmail, updatedPhone);
            users.set(index, updatedUser);
            System.out.println("User information updated successfully.");
        } else {
            System.out.println("User not found. Update failed.");
        }
    }


    public void removeUser(User user) {
        users.remove(user);
    }

    public User findUserById(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public void issueBook(Book book, User user) {
        // Set the book's availability status to false
        book.setAvailability(false);

        // Add the user to the book's list of borrowers
        book.addBorrower(user);

        // Set the due date for the book
        LocalDate dueDate = LocalDate.now().plusDays(3); // Assume maximum 3 books are allowed per user
        book.setDueDate(dueDate);
    }

    public void returnBook(Book book, User user) {
        // Set the book's availability status to true
        book.setAvailability(true);

        // Remove the user from the book's list of borrowers
        book.removeBorrower(user);
    }

    public double calculateFine(Book book) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = book.getDueDate();

        if (currentDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, currentDate);
            double finePerDay = 1.0; // Assuming a fine of ₹1.0 per day
            return finePerDay * daysLate;
        } else {
            return 0.0; // No fine if the book is returned before or on the due date
        }
    }

    public void acceptFinePayment(User user, double amount) {
        double currentFine = user.getFineAmount();
        if (currentFine > 0 && amount >= currentFine) {
            user.setFineAmount(0.0);
            System.out.println("Fine payment accepted. User's fine cleared.");
        } else if (currentFine > 0) {
            user.setFineAmount(currentFine - amount);
            System.out.println("Fine payment accepted. Remaining fine: ₹" + user.getFineAmount());
        } else {
            System.out.println("No fine to be paid.");
        }
    }

    public void serializeLibrary(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Library serialized and saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error occurred while serializing the library: " + e.getMessage());
        }
    }

    public static Library deserializeLibrary(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Library library = (Library) objectIn.readObject();
            System.out.println("Library deserialized from " + filename);
            return library;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while deserializing the library: " + e.getMessage());
        }
        return null;
    }

    public void displayDeserializedData() {
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void renewBook(Book book) {
        if (!book.isAvailable()) {
            User user = book.getBorrower();
            // Implement renewal policies and logic
            System.out.println("Book renewed successfully.");
        } else {
            System.out.println("Book is not currently issued.");
        }
    }
    public void reserveBook(Book book, User user) {
        if (!book.isAvailable()) {
            reservedBooks.add(book);
            // Notify the user when the reserved book becomes available
            System.out.println("Book reserved successfully.");
        } else {
            System.out.println("Book is already available for borrowing.");
        }
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public boolean authenticateUser(int userID, String password) {
        User user = findUserById(userID);
        return user != null && user.getPassword().equals(password);
    }

    public boolean isBookAvailable(Book book) {
        return book.isAvailable();
    }

    @Override
    public String toString() {
        return "Library{ " +
                "Books = " + books +
                ", Users = " + users +
                '}';
    }


}
